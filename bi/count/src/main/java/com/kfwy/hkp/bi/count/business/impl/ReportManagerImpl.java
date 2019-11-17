package com.kfwy.hkp.bi.count.business.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.bi.count.business.IReportManager;
import com.kfwy.hkp.bi.count.dao.IReportDbDao;
import com.kfwy.hkp.bi.count.dao.IReportRedisDbDao;
import com.kfwy.hkp.bi.count.dto.ReportDto;
import com.kfwy.hkp.bi.count.entity.ReportEntity;
import com.kfwy.hkp.bi.count.entity.TableHead;
import com.kfwy.hkp.bi.count.entity.ThreadQuery;
import com.kfwy.hkp.bi.count.enums.ReportType;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.crm.customer.dao.ICustomerDbDao;
import com.kfwy.hkp.crm.customer.dao.ICustomerFollowupDbDao;
import com.kfwy.hkp.crm.customer.dao.ICustomerPushDbDao;
import com.kfwy.hkp.crm.customer.enums.CustomerFollowupType;
import com.kfwy.hkp.crm.houseowner.dao.IHouseownerFollowupDbDao;
import com.kfwy.hkp.hos.house.dao.IHouseDbDao;
import com.kfwy.hkp.hos.house.dao.IHouseFollowupDbDao;
import com.kfwy.hkp.hrm.org.dept.business.IDeptManager;
import com.kfwy.hkp.hrm.org.dept.entity.DeptEntity;
import com.kfwy.hkp.sys.auth.dao.IReportAccessDbDao;
import com.kfwy.hkp.sys.parttime.dao.IParttimeDbDao;
import com.kfwy.hkp.sys.user.dao.IUserDbDao;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import com.kfwy.hkp.trade.order.dao.IOrderPercentageDbDao;
import com.kfwy.hkp.trade.order.enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Create By hjh on 2018/10/24
 */
@Service
public class ReportManagerImpl extends AbstractManager<ReportEntity> implements IReportManager {

    @Autowired
    private IReportDbDao reportDbDao;
    @Autowired
    private IDeptManager deptManager;
    @Autowired
    private IReportAccessDbDao reportAccessDbDao;
    @Autowired
    private IUserDbDao userDbDao;
    @Autowired
    private IHouseDbDao houseDbDao;
    @Autowired
    private IHouseFollowupDbDao houseFollowupDbDao;
    @Autowired
    private IHouseownerFollowupDbDao ownerFollowupDbDao;
    @Autowired
    private ICustomerDbDao customerDbDao;
    @Autowired
    private ICustomerPushDbDao customerPushDbDao;
    @Autowired
    private ICustomerFollowupDbDao cusFollowupDbDao;
    @Autowired
    private IOrderPercentageDbDao orderPercentageDbDao;
    @Autowired
    private IParttimeDbDao parttimeDbDao;
    @Autowired
    private IReportRedisDbDao reportRedisDbDao;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public final static String KEY = "ky:hkp:report:";

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected IMyBatisBaseDao<ReportEntity, Long> getMyBatisDao() {
        return null;
    }

    @Override
    public ReportDto countDto(Date startTime, Date endTime, String code, boolean dynamic) {
        //开始时间
        long starts = System.currentTimeMillis();

        // 根据权限查询报表
        if (code == null) {
            List<String> deptCodes = reportAccessDbDao.selectAccess(CurrentContext.getUserCode());
            if (CollectionUtil.isNotEmpty(deptCodes)) {
                code = deptCodes.get(0);
            } else {
                UserEntity u = (UserEntity) CurrentContext.getUserInfo();
                code = u.getOwnerDeptCode() == null ? u.getUserCode() : u.getOwnerDeptCode();
            }
        }

        // 日期格式化
        String start = sdf.format(startTime);
        String end = sdf.format(endTime);
        // 唯一键
        String auth = KEY + code + ":" + start + ":" + end;
        // 获取缓存
        if (!dynamic) {
            try {
                ReportDto reportDto = reportRedisDbDao.parseEntity(auth);
                if (null != reportDto) {
                    return reportDto;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException n) {
                n.printStackTrace();
            }
        }

        // 拦截相同参数请求
        String selectAuth = redisTemplate.boundValueOps("select:" + auth).get();
        if (null != selectAuth) {
            throw new RemoveStackBusinessException("正在统计中······,请您8秒后查看!");
        }
        // 设置查询key,并在8秒后自动失效
        redisTemplate.opsForValue().set("select:" + auth, "carryOut", 8, TimeUnit.SECONDS);

        // 设置线程池的数量大小
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "15");

        // 返回dto
        ReportDto reportDto = new ReportDto();
        // 报表type
        String type = null;
        // 数据集合
        List<ReportEntity> reportEntities = Collections.synchronizedList(new ArrayList<>());
        // 参数
        Map<String, Object> map = new HashMap<String, Object>();
        // 查询部门
        DeptEntity dept = deptManager.findOne("deptCode", code);
        // 查询参数
        List<ThreadQuery> threadQuerys = new ArrayList<>();

        //region 个人报表
        if (null == dept) {
            // 查询人员
            map.put("userCode", code);
            map.put("isDeleted", Boolean.FALSE);
            List<UserEntity> userEntities = userDbDao.selectSimpleUserByMap(map);
            // 获取人员报表信息
            getUserReports(startTime, endTime, reportEntities, userEntities);
            type = ReportType.EMP;
        } else {
            // 查询下级部门
            map.put("deleteTimeStart", startTime);
            map.put("createTimeEnd", endTime);
            map.put("parentCode", code);
            List<DeptEntity> depts = deptManager.findByMap(map);
            map.clear();
            if (depts == null || depts.isEmpty()) {
                // 查询人员
                map.put("ownerDeptCode", code);
                map.put("quitTimeStart", startTime);
                map.put("entryTimeEnd", endTime);
                List<UserEntity> userEntities = userDbDao.selectSimpleUserByMap(map);
                // 获取人员报表信息
                if (CollectionUtil.isEmpty(userEntities)) {
                    return reportDto;
                }
                // 获取人员报表信息
                getUserReports(startTime, endTime, reportEntities, userEntities);
                type = ReportType.EMP;
                // 求和
                getReportSum(reportEntities, type);
            } else {
                type = ReportType.DEPT;
                if (dept.getDeptLevel() == 2) {
                    type = ReportType.REGIONAL;
                }
                for (DeptEntity d : depts) {
                    // 获取所有下级部门编码包括自身
                    List<String> deptCodes = deptManager.getValidDownDeptCode(d.getDeptCode(), startTime, endTime);
                    ThreadQuery query = new ThreadQuery();
                    query.setShowName(d.getDeptName());
                    query.setParams(deptCodes);
                    threadQuerys.add(query);
                }
                getDeptReports(threadQuerys, reportEntities, startTime, endTime);
                getReportSum(reportEntities, type);
            }
        }
        //endregion
        long ends = System.currentTimeMillis();
        System.out.println("查询数据用时:" + (ends - starts) + "ms");

        // 获取table
        getTableHead(reportDto, type);

        // 存入redis
        reportDto.setReportEntities(reportEntities);
        // 删除相同查询
        redisTemplate.delete("select:" + auth);
        try {
            reportRedisDbDao.saveEntity(auth, reportDto);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            reportRedisDbDao.setTimeout(auth, calendar.getTime());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return reportDto;
    }

    private void getTableHead(ReportDto reportDto, String type) {
        List<TableHead> tableHead = new ArrayList<>();

        if (type.equals(ReportType.REGIONAL)) {
            tableHead.add(new TableHead("showName", "大区"));
            tableHead.add(new TableHead("deptSum", "分部数"));
            tableHead.add(new TableHead("manNumber", "大区人数"));
            tableHead.add(new TableHead("perIncome", "人均收入"));
        } else if (type.equals(ReportType.DEPT)) {
            tableHead.add(new TableHead("showName", "部门名称"));
            tableHead.add(new TableHead("manNumber", "团队人数"));
            tableHead.add(new TableHead("perIncome", "人均收入"));
        } else {
            tableHead.add(new TableHead("showName", "服务专员"));
            tableHead.add(new TableHead("showTime", "入职时间"));
        }

        tableHead.add(new TableHead("orderCount", "签约"));
        tableHead.add(new TableHead("totalMoney", "开单金额"));
        tableHead.add(new TableHead("revenue", "营收"));
        tableHead.add(new TableHead("totalPercentage", "分成业绩"));
        tableHead.add(new TableHead("houseDevelop", "房源数"));
        tableHead.add(new TableHead("houseDepute", "房源委托"));
        tableHead.add(new TableHead("houseFollowup", "房源跟进"));
        tableHead.add(new TableHead("ownerVisit", "业主拜访"));
        tableHead.add(new TableHead("cusDevelop", "客户数"));
        tableHead.add(new TableHead("focusCount", "集中获客"));
        tableHead.add(new TableHead("cusPushCount", "客户推送"));
        tableHead.add(new TableHead("validCus", "有效客户数"));
        tableHead.add(new TableHead("followupCount", "客户跟进数"));
        tableHead.add(new TableHead("seeCount", "带看数"));
        tableHead.add(new TableHead("partTimeDevelop", "兼职开发"));
        tableHead.add(new TableHead("cusUpCount", "客户上架数"));
        tableHead.add(new TableHead("hosUpCount", "房源上架数"));
        if (!type.equals(ReportType.EMP)) {
            tableHead.add(new TableHead("managerFollowupCount", "经理跟进"));
            tableHead.add(new TableHead("entryPerson", "入职人数"));
            tableHead.add(new TableHead("quitPerson", "离职人数"));
        }

        reportDto.setTableHeads(tableHead);
    }

    public void getReportSum(List<ReportEntity> reportEntities, String type) {
        int houseDevelop = 0;
        int houseDepute = 0;
        int houseFollowup = 0;
        int ownerVisit = 0;
        int cusDevelop = 0;
        int focusCount = 0;
        int cusPushCount = 0;
        int validCus = 0;
        int partTimeDevelop = 0;
        int followupCount = 0;
        int managerFollowupCount = 0;
        int seeCount = 0;
        int orderCount = 0;
        int cusUpCount = 0;
        int hosUpCount = 0;
        BigDecimal totalMoney = new BigDecimal(0);
        BigDecimal revenue = new BigDecimal(0);
        BigDecimal totalPercentage = new BigDecimal(0);
        BigDecimal perIncome = new BigDecimal(0);
        int manNumber = 0;
        int entryPerson = 0;
        int quitPerson = 0;
        int deptSum = 0;
        for (ReportEntity report : reportEntities) {
            houseDevelop += report.getHouseDevelop();
            houseDepute += report.getHouseDepute();
            houseFollowup += report.getHouseFollowup();
            ownerVisit += report.getOwnerVisit();
            cusDevelop += report.getCusDevelop();
            focusCount += report.getFocusCount();
            cusPushCount += report.getCusPushCount();
            validCus += report.getValidCus();
            partTimeDevelop += report.getPartTimeDevelop();
            followupCount += report.getFollowupCount();
            seeCount += report.getSeeCount();
            orderCount += report.getOrderCount();
            totalMoney = totalMoney.add(report.getTotalMoney());
            revenue = revenue.add(report.getRevenue());
            totalPercentage = totalPercentage.add(report.getTotalPercentage());
            cusUpCount += report.getCusUpCount();
            hosUpCount += report.getHosUpCount();
            if (type.equals(ReportType.REGIONAL)) {
                manNumber += report.getManNumber();
                entryPerson += report.getEntryPerson();
                quitPerson += report.getQuitPerson();
                deptSum += report.getDeptSum();
                managerFollowupCount += report.getManagerFollowupCount();
            } else if (type.equals(ReportType.DEPT)) {
                manNumber += report.getManNumber();
                entryPerson += report.getEntryPerson();
                quitPerson += report.getQuitPerson();
                managerFollowupCount += report.getManagerFollowupCount();
            }
        }
        ReportEntity reportSum = new ReportEntity();
        reportSum.setReportType(type);

        reportSum.setHouseDevelop(houseDevelop);
        reportSum.setHouseDepute(houseDepute);
        reportSum.setHouseFollowup(houseFollowup);
        reportSum.setOwnerVisit(ownerVisit);
        reportSum.setCusDevelop(cusDevelop);
        reportSum.setFocusCount(focusCount);
        reportSum.setCusPushCount(cusPushCount);
        reportSum.setValidCus(validCus);
        reportSum.setPartTimeDevelop(partTimeDevelop);
        reportSum.setFollowupCount(followupCount);
        reportSum.setManagerFollowupCount(managerFollowupCount);
        reportSum.setSeeCount(seeCount);
        reportSum.setOrderCount(orderCount);
        reportSum.setTotalMoney(totalMoney);
        reportSum.setRevenue(revenue);
        reportSum.setTotalPercentage(totalPercentage);
        reportSum.setShowName("总计");
        if (type.equals(ReportType.REGIONAL)) {
            BigDecimal manNumbers = new BigDecimal(Double.valueOf(manNumber));
            perIncome = totalPercentage.divide(manNumbers, 2, BigDecimal.ROUND_CEILING);
            reportSum.setPerIncome(perIncome);
            reportSum.setDeptSum(deptSum);
            reportSum.setManNumber(manNumber);
        } else if (type.equals(ReportType.DEPT)) {
            BigDecimal manNumbers = new BigDecimal(Double.valueOf(manNumber));
            perIncome = totalPercentage.divide(manNumbers, 2, BigDecimal.ROUND_CEILING);
            reportSum.setPerIncome(perIncome);
            reportSum.setManNumber(manNumber);
        }
        reportSum.setEntryPerson(entryPerson);
        reportSum.setQuitPerson(quitPerson);
        reportSum.setCusUpCount(cusUpCount);
        reportSum.setHosUpCount(hosUpCount);
        reportEntities.add(0, reportSum);
    }

    public void getDeptReports(List<ThreadQuery> ts, List<ReportEntity> reportEntities, Date startTime, Date endTime) {
        ts.parallelStream().forEach(e -> {
            try {
                Map<String, Object> map = new HashMap<>();
                // 查询人员编码
                map.put("ownerDeptCodes", e.getParams());
                map.put("quitTimeStart", startTime);
                map.put("entryTimeEnd", endTime);
                List<String> userCodes = userDbDao.selectUserCodeByMap(map);

                map.clear();
                // 下级部门的数据
                ReportEntity r = queryReportEntity(startTime, endTime, e.getParams(), ReportType.DEPT);
                // 部门信息
                r.setShowName(e.getShowName());
                // 部门人数
                r.setManNumber(userCodes.size());
                // 人均收入
                if (r.getManNumber() > 0) {
                    BigDecimal empCount = new BigDecimal(Double.valueOf(r.getManNumber()));
                    BigDecimal divide = r.getTotalPercentage().divide(empCount, 2, BigDecimal.ROUND_CEILING);
                    r.setPerIncome(divide);
                } else {
                    r.setPerIncome(BigDecimal.ZERO);
                }
                // 部门数
                r.setDeptSum(e.getParams().size() - 1);
                // 入职人数
                map.put("ownerDeptCodes", e.getParams());
                map.put("entryTimeStart", startTime);
                map.put("entryTimeEnd", endTime);
                map.put("isDeleted", Boolean.FALSE);
                int entryPerson = userDbDao.countByMap(map);
                r.setEntryPerson(entryPerson);
                map.clear();
                // 离职人数
                map.put("ownerDeptCodes", e.getParams());
                map.put("quitTimeStart", startTime);
                map.put("quitTimeEnd", endTime);
                map.put("isDeleted", Boolean.TRUE);
                int quitPerson = userDbDao.countByMap(map);
                map.clear();
                r.setQuitPerson(quitPerson);
                reportEntities.add(r);
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("并行流异常");
            }
        });
    }

    public void getUserReports(Date startTime, Date endTime, List<ReportEntity> reportEntities, List<UserEntity> userEntities) {
        userEntities.parallelStream().forEach(e -> {
            // 所部人员的数据
            ReportEntity r = queryReportEntity(startTime, endTime, e.getUserCode(), ReportType.EMP);
            // 入职时间
            r.setShowTime(DateUtil.format(e.getEntryTime(), DatePattern.NORM_DATE_PATTERN));
            // 离职时间
            if (e.getQuitTime() != null) {
                r.setQuitTime(DateUtil.format(e.getQuitTime(), DatePattern.NORM_DATE_PATTERN));
            }
            // 人员信息
            r.setShowName(e.getUserName());

            reportEntities.add(r);
        });
    }

    private ReportEntity queryReportEntity(Date startTime, Date endTime, Object code, String reportType) {
        Map<String, Object> map = new HashMap<>();
        ReportEntity reportEntity = new ReportEntity();
        // 报表类型
        reportEntity.setReportType(reportType);

        // 公共参数
        if (reportType.equals(ReportType.DEPT)) {
            map.put("createDeptCodes", code);
        } else {
            map.put("createCode", code);
        }
        map.put("createTimeStart", startTime);
        map.put("createTimeEnd", endTime);
        map.put("isDeleted", Boolean.FALSE);

        // 房源开发
        int houseCount = houseDbDao.selectCount("selectCountByMap", map);
        reportEntity.setHouseDevelop(houseCount);

        // 房源委托
        int houseDepute = reportDbDao.selectHouseDepute(map);
        reportEntity.setHouseDepute(houseDepute);

        // 业主拜访
        int ownerFollowupCount = ownerFollowupDbDao.selectCount("selectByMapCount", map);
        reportEntity.setOwnerVisit(ownerFollowupCount);

        // 客户开发
        int cusCount = customerDbDao.selectCount("selectCountByMap", map);
        reportEntity.setCusDevelop(cusCount);

        // 集中获客
        int focusCount = 0;
        if (reportType.equals(ReportType.DEPT)) {
            focusCount = reportDbDao.selectByDeptFocusCount(map);
        } else {
            focusCount = reportDbDao.selectByUserFocusCount(map);
        }
        reportEntity.setFocusCount(focusCount);

        // 客户推送
        int cusPushCount = customerPushDbDao.selectCount("countByMap", map);
        reportEntity.setCusPushCount(cusPushCount);

        // 有效客户
        int validCus = reportDbDao.selectValidCus(map);
        reportEntity.setValidCus(validCus);

        // 兼职开发
        int partTimeDevelop = parttimeDbDao.selectCount("selectByMapCount", map);
        reportEntity.setPartTimeDevelop(partTimeDevelop);

        // 客户上架数
        int cusUpCount = reportDbDao.selectCusUpCount(map);
        reportEntity.setCusUpCount(cusUpCount);

        // 房源上架数
        int hosUpCount = reportDbDao.selectHosUpCount(map);
        reportEntity.setHosUpCount(hosUpCount);

        // 房源跟进
        map.put("groupBy", "create_code,house_code,date_trunc('day',create_time)");
        int houseFollowup = houseFollowupDbDao.selectFollowupCount(map);
        reportEntity.setHouseFollowup(houseFollowup);

        if (reportType.equals(ReportType.DEPT)) {
            // 经理意向客户跟进
            map.put("managerFollowup", Boolean.TRUE);
            map.put("groupBy", "create_code,cus_code,date_trunc('day',create_time)");
            int managerFollowupCount = cusFollowupDbDao.selectFollowupCount(map);
            reportEntity.setManagerFollowupCount(managerFollowupCount);
            map.remove("managerFollowup");
        }

        // 客户跟进
        List<String> followupTypes = new ArrayList<>();
        followupTypes.add(CustomerFollowupType.VisitDoor);
        followupTypes.add(CustomerFollowupType.VisitPhone);
        map.put("followupTypes", followupTypes);
        map.put("groupBy", "create_code,cus_code,date_trunc('day',create_time)");
        int followupCount = cusFollowupDbDao.selectFollowupCount(map);
        reportEntity.setFollowupCount(followupCount);


        // 客户带看
        followupTypes.clear();
        followupTypes.add(CustomerFollowupType.SeeHouse);
        map.put("followupTypes", followupTypes);
        int seeCount = cusFollowupDbDao.selectFollowupCount(map);
        reportEntity.setSeeCount(seeCount);

        map.clear();

        // 排除,毁单,坏账,取消
        List<String> noOrderStatuses = new ArrayList<>();
        noOrderStatuses.add(OrderStatus.Destroy);
        noOrderStatuses.add(OrderStatus.BadDebt);
        noOrderStatuses.add(OrderStatus.HasCancel);

        if (reportType.equals(ReportType.DEPT)) {
            map.put("deptCodes", code);
        } else {
            map.put("empCode", code);
        }
        map.put("noOrderStatuses", noOrderStatuses);
        map.put("orderTimeStart", startTime);
        map.put("orderTimeEnd", endTime);
        map.put("isDeleted", Boolean.FALSE);

        List<BigDecimal> orderEntities = reportDbDao.selectOrderTotalMoney(map);

        // 订单金额
        BigDecimal totalMoney = new BigDecimal(0);
        for (BigDecimal ord : orderEntities) {
            totalMoney = totalMoney.add(ord != null ? ord : BigDecimal.ZERO);
        }
        // 签约数量
        reportEntity.setOrderCount(orderEntities.size());
        // 订单金额
        reportEntity.setTotalMoney(totalMoney);

        // 营收
        BigDecimal revenue = reportDbDao.selectOrderActualMoney(map);
        reportEntity.setRevenue(revenue != null ? revenue : BigDecimal.ZERO);
        map.clear();

        // 获取部门分成利润
        if (reportType.equals(ReportType.DEPT)) {
            map.put("peDeptCodes", code);
        } else {
            map.put("peEmpCode", code);
        }
        map.put("noOrderStatuses", noOrderStatuses);
        map.put("orderTimeStart", startTime);
        map.put("orderTimeEnd", endTime);
        map.put("isDeleted", Boolean.FALSE);
        BigDecimal profits = orderPercentageDbDao.selectByMapAndStatus(map);
        map.clear();
        // 分成业绩
        reportEntity.setTotalPercentage(profits != null ? profits : BigDecimal.ZERO);

        return reportEntity;
    }
}
