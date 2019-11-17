package com.kfwy.hkp.bi.count.business.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.NumberUtil;
import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.bi.count.business.IConvertRateManager;
import com.kfwy.hkp.bi.count.dto.CommonReportDto;
import com.kfwy.hkp.bi.count.entity.ConvertRateEntity;
import com.kfwy.hkp.crm.customer.dao.ICustomerDbDao;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.hrm.org.dept.business.IDeptManager;
import com.kfwy.hkp.hrm.org.dept.entity.DeptEntity;
import com.kfwy.hkp.sys.auth.dao.IReportAccessDbDao;
import com.kfwy.hkp.sys.user.dao.IUserDbDao;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import com.kfwy.hkp.trade.order.enums.OrderStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Create By hjh on 2019/1/14
 */
@Service
public class ConvertRateManagerImpl extends AbstractManager<ConvertRateEntity> implements IConvertRateManager {

    @Autowired
    private IDeptManager deptManager;
    @Autowired
    private IUserDbDao userDbDao;
    @Autowired
    private ICustomerDbDao customerDbDao;
    @Autowired
    private IReportAccessDbDao reportAccessDbDao;

    @Override
    protected IMyBatisBaseDao<ConvertRateEntity, Long> getMyBatisDao() {
        return null;
    }

    @Override
    public CommonReportDto<ConvertRateEntity> count(Date startTime, Date endTime, String code) {

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

        CommonReportDto<ConvertRateEntity> crd = new CommonReportDto<>();

        DeptEntity deptEntity = deptManager.findOne("deptCode", code);

        List<String> userCodes = new ArrayList<>();
        List<String> deptCodes = new ArrayList<>();

        Map<String, Object> map = new HashMap<>();

        if (deptEntity == null) { // 个人
            userCodes.add(code);
        } else { // 部门
            map.put("isDeleted", false);
            map.put("parentCode", code);
            List<DeptEntity> deptEntities = deptManager.findByMap(map);
            map.clear();
            if (deptEntities.isEmpty()) {
                // 查询人员
                map.put("ownerDeptCode", code);
                map.put("quitTimeStart", startTime);
                //map.put("quitTimeEnd", endTime);
                map.put("entryTimeEnd", endTime);
                userCodes = userDbDao.selectUserCodeByMap(map);
                map.clear();
                if (userCodes.isEmpty()) {
                    return crd;
                }

            } else {
                // 获取当前部门下所有部门编码
                deptCodes = deptManager.getValidDownDeptCode(code, startTime, endTime);
            }
        }

        if (CollectionUtil.isEmpty(deptCodes)) {
            deptCodes = null;
        }

        // 增加部门条件
        List<ConvertRateEntity> crs = this.selectConvertRate(startTime, endTime, userCodes, deptCodes);
        crd.setReportEntities(crs);

        this.getTableHead(crd);

        return crd;
    }

    private void getTableHead(CommonReportDto<ConvertRateEntity> reportDto) {
        Map<String, String> tableHead = new LinkedHashMap<>();

        tableHead.put("convertType", "类型");
        tableHead.put("wbSource", "58付费");
        tableHead.put("gjSource", "赶集付费");
        tableHead.put("frontSource", "官网预约");
        tableHead.put("partSource", "兼职推荐");
        tableHead.put("outdoorsSource", "户外广告");
        tableHead.put("sweepSource", "扫街");
        tableHead.put("cusService", "400客服");
        tableHead.put("mdbdSource", "名单拨打");
        tableHead.put("otherSource", "其他");
        tableHead.put("countSource", "总计");

        reportDto.setTableHeads(tableHead);
    }

    private List<ConvertRateEntity> selectConvertRate(Date startTime, Date endTime, List<String> userCodes, List<String> deptCodes) {
        List<ConvertRateEntity> result = new ArrayList<>();
        // 获取获客数
        ConvertRateEntity cus = selectCustomer(startTime, endTime, userCodes, deptCodes);
        // 有效客户数
        ConvertRateEntity validCus = selectValidCus(startTime, endTime, userCodes, deptCodes);
        // 成交客户数量
        ConvertRateEntity orderCus = selectOrderCus(startTime, endTime, userCodes, deptCodes);

        result.add(cus);
        result.add(validCus);
        result.add(orderCus);

        //获客到有效占比
        ConvertRateEntity cC = this.getPercent("获客到有效百分比", validCus, cus);
        //有效到成交占比
        ConvertRateEntity cV = this.getPercent("有效到成交百分比", orderCus, validCus);
        //获客到成交占比
        ConvertRateEntity cO = this.getPercent("获客到成交百分比", orderCus, cus);

        result.add(cC);
        result.add(cV);
        result.add(cO);

        return result;
    }

    private ConvertRateEntity selectCustomer(Date startTime, Date endTime, List<String> userCodes, List<String> deptCodes) {
        //获取获客数量
        Map<String, Object> map = new HashMap<>();
        if (CollectionUtil.isEmpty(deptCodes)) {
            map.put("createCodes", userCodes);// 业绩统计报表并没有使用create_code
        } else {
            map.put("createDeptCodes", deptCodes);
        }
        map.put("createTimeStart", startTime);
        map.put("createTimeEnd", endTime);
        map.put("isDeleted", Boolean.FALSE);

        List<CustomerEntity> c = customerDbDao.selectListByMap(map);
        ConvertRateEntity cre = this.convertCustomer(c);
        cre.setConvertType("获客数");
        return cre;
    }

    private ConvertRateEntity selectValidCus(Date startTime, Date endTime, List<String> userCodes, List<String> deptCodes) {
        //获取获客数量
        Map<String, Object> map = new HashMap<>();
        if (CollectionUtil.isEmpty(deptCodes)) {
            map.put("createCodes", userCodes);// 业绩统计报表并没有使用create_code
        } else {
            map.put("createDeptCodes", deptCodes);
        }
        map.put("createTimeStart", startTime);
        map.put("createTimeEnd", endTime);
        map.put("isDeleted", Boolean.FALSE);

        List<CustomerEntity> c = customerDbDao.selectValidCusByMap(map);
        ConvertRateEntity cre = this.convertCustomer(c);
        cre.setConvertType("有效客户");
        return cre;
    }

    private ConvertRateEntity selectOrderCus(Date startTime, Date endTime, List<String> userCodes, List<String> deptCodes) {

        // 排除已坏账
        List<String> noOrderStatuses = new ArrayList<>();
        noOrderStatuses.add(OrderStatus.BadDebt);

        //获取获客数量
        Map<String, Object> map = new HashMap<>();
        if (CollectionUtil.isEmpty(deptCodes)) {
            map.put("empCodes", userCodes);
        } else {
            map.put("createDeptCodes", deptCodes);
        }
        map.put("noOrderStatuses", noOrderStatuses);
        map.put("orderTimeStart", startTime);
        map.put("orderTimeEnd", endTime);
        map.put("isDeleted", Boolean.FALSE);

        List<CustomerEntity> c = customerDbDao.selectOrderCusByMap(map);
        ConvertRateEntity cre = this.convertCustomer(c);
        cre.setConvertType("成交客户");
        return cre;
    }

    private ConvertRateEntity convertCustomer(List<CustomerEntity> ces) {
        ConvertRateEntity c = new ConvertRateEntity();
        if (ces != null && !ces.isEmpty()) {
            Integer wbSource = 0;
            Integer gjSource = 0;
            Integer frontSource = 0;
            Integer partSource = 0;
            Integer outdoorsSource = 0;
            Integer sweepSource = 0;
            Integer f400 = 0;
            Integer mdbdSource = 0;
            Integer otherSource = 0;
            for (CustomerEntity ce : ces) {
                String cusForm = ce.getCusFrom();
                if ("58付费".equals(cusForm)) {
                    wbSource++;
                } else if ("赶集付费".equals(cusForm)) {
                    gjSource++;
                } else if ("官网预约".equals(cusForm)) {
                    frontSource++;
                } else if ("兼职推荐".equals(cusForm)) {
                    partSource++;
                } else if ("户外广告".equals(cusForm)) {
                    outdoorsSource++;
                } else if ("线下扫街".equals(cusForm)) {
                    sweepSource++;
                } else if ("400客服".equals(cusForm)) {
                    f400++;
                } else if ("名单拨打".equals(cusForm)) {
                    mdbdSource++;
                } else {
                    otherSource++;
                }
            }
            c.setWbSource(wbSource.toString());
            c.setGjSource(gjSource.toString());
            c.setFrontSource(frontSource.toString());
            c.setPartSource(partSource.toString());
            c.setOutdoorsSource(outdoorsSource.toString());
            c.setSweepSource(sweepSource.toString());
            c.setOtherSource(otherSource.toString());
            c.setMdbdSource(mdbdSource.toString());
            c.setCusService(f400.toString());

            c.setCountSource(ces.size() + "");
        }
        return c;
    }

    private ConvertRateEntity getPercent(String convertType, ConvertRateEntity molecule, ConvertRateEntity denominator) {
        ConvertRateEntity c = new ConvertRateEntity();
        c.setConvertType(convertType);
        c.setWbSource(this.getNitemd(molecule.getWbSource(), denominator.getWbSource()));
        c.setGjSource(this.getNitemd(molecule.getGjSource(), denominator.getGjSource()));
        c.setFrontSource(this.getNitemd(molecule.getFrontSource(), denominator.getFrontSource()));
        c.setPartSource(this.getNitemd(molecule.getPartSource(), denominator.getPartSource()));
        c.setOutdoorsSource(this.getNitemd(molecule.getOutdoorsSource(), denominator.getOutdoorsSource()));
        c.setOtherSource(this.getNitemd(molecule.getOtherSource(), denominator.getOtherSource()));
        c.setCountSource(this.getNitemd(molecule.getCountSource(), denominator.getCountSource()));
        c.setSweepSource(this.getNitemd(molecule.getSweepSource(), denominator.getSweepSource()));
        c.setCusService(this.getNitemd(molecule.getCusService(), denominator.getCusService()));
        c.setMdbdSource(this.getNitemd(molecule.getMdbdSource(), denominator.getMdbdSource()));
        return c;
    }

    protected String getNitemd(String a, String b) {
        if (StringUtils.isNotEmpty(a) && StringUtils.isNotEmpty(b)) {
            Double as = Double.valueOf(a);
            Double bs = Double.valueOf(b);
            if (as != 0 && bs != 0) {
                double v = (as / bs) * 100;
                // 保留两位小数
                return NumberUtil.decimalFormat("0.00", v) + "%";
            }
        }
        return "0%";
    }

}
