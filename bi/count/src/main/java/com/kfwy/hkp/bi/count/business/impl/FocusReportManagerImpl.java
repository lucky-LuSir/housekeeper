package com.kfwy.hkp.bi.count.business.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.bi.count.business.IFocusReportManager;
import com.kfwy.hkp.bi.count.dao.IFocusReportDbDao;
import com.kfwy.hkp.bi.count.dto.CommonReportDto;
import com.kfwy.hkp.bi.count.entity.FocusReportEntity;
import com.kfwy.hkp.bi.count.entity.ThreadQuery;
import com.kfwy.hkp.bi.count.enums.ReportType;
import com.kfwy.hkp.crm.customer.dao.ICustomerDbDao;
import com.kfwy.hkp.crm.customer.enums.CustomerType;
import com.kfwy.hkp.hrm.org.dept.business.IDeptManager;
import com.kfwy.hkp.hrm.org.dept.entity.DeptEntity;
import com.kfwy.hkp.sys.auth.dao.IReportAccessDbDao;
import com.kfwy.hkp.sys.user.dao.IUserDbDao;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Auther: HJH
 * @Date: 2019/8/27
 * @Description: TODO
 */
@Service
public class FocusReportManagerImpl extends AbstractManager<FocusReportEntity> implements IFocusReportManager {

    @Autowired
    private IFocusReportDbDao focusReportDbDao;

    @Autowired
    private IDeptManager deptManager;

    @Autowired
    private IUserDbDao userDbDao;

    @Autowired
    private ICustomerDbDao customerDbDao;

    @Autowired
    private IReportAccessDbDao reportAccessDbDao;

    @Override
    protected IMyBatisBaseDao<FocusReportEntity, Long> getMyBatisDao() {
        return this.focusReportDbDao;
    }


    @Override
    public CommonReportDto<FocusReportEntity> count(String code, Date startTime, Date endTime) {

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

        CommonReportDto<FocusReportEntity> reportDto = new CommonReportDto<>();

        List<FocusReportEntity> reports = Collections.synchronizedList(new ArrayList<>());

        // 参数
        Map<String, Object> map = new HashMap<String, Object>();

        // 查询部门
        DeptEntity dept = deptManager.findOne("deptCode", code);
        // 查询参数
        List<ThreadQuery> threadQuerys = new ArrayList<>();

        // 设置线程池的数量大小
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "6");

        // 报表type
        String type = null;

        //region 个人报表
        if (null == dept) {
            // 查询人员
            map.put("userCode", code);
            map.put("isDeleted", Boolean.FALSE);
            List<UserEntity> userEntities = userDbDao.selectSimpleUserByMap(map);
            // 获取人员报表信息
            this.userSelect(startTime, endTime, reports, userEntities);
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
                this.userSelect(startTime, endTime, reports, userEntities);
                this.sumDeptSelect(startTime, endTime, reports, dept);
            } else {
                for (DeptEntity d : depts) {
                    // 获取所有下级部门编码包括自身
                    List<String> deptCodes = deptManager.getValidDownDeptCode(d.getDeptCode(), startTime, endTime);
                    ThreadQuery query = new ThreadQuery();
                    query.setShowName(d.getDeptName());
                    query.setParams(deptCodes);
                    threadQuerys.add(query);
                }
                this.deptSelect(startTime, endTime, reports, threadQuerys);
                // 总计
                this.getReportSum(reports);
            }
        }

        // 表头
        this.getTableHead(reportDto);
        reportDto.setReportEntities(reports);
        return reportDto;
    }

    public void userSelect(Date startTime, Date endTime, List<FocusReportEntity> reports, List<UserEntity> userEntities) {
        userEntities.parallelStream().forEach(e -> {
            FocusReportEntity fe = selectEntity(e.getUserCode(), startTime, endTime, ReportType.EMP);
            fe.setShowName(e.getUserName());
            reports.add(fe);
        });
    }

    public void deptSelect(Date startTime, Date endTime, List<FocusReportEntity> reports, List<ThreadQuery> threadQuerys) {
        threadQuerys.parallelStream().forEach(e -> {
            FocusReportEntity fe = selectEntity(e.getParams(), startTime, endTime, ReportType.DEPT);
            fe.setShowName(e.getShowName());
            reports.add(fe);
        });
    }

    public void sumDeptSelect(Date startTime, Date endTime, List<FocusReportEntity> reports, DeptEntity dept) {
        List<String> deptCodes = new ArrayList<>();
        deptCodes.add(dept.getDeptCode());
        FocusReportEntity fe = selectEntity(deptCodes, startTime, endTime, ReportType.DEPT);
        fe.setShowName(dept.getDeptName());
        reports.add(0, fe);
    }


    public FocusReportEntity selectEntity(Object param, Date startTime, Date endTime, String reportType) {

        FocusReportEntity f = new FocusReportEntity();

        // 公共参数
        Map<String, Object> map = new HashMap<>();
        if (reportType.equals(ReportType.DEPT)) {
            map.put("userDeptCodes", param);
        } else {
            map.put("userCode", param);
        }
        map.put("createTimeStart", startTime);
        map.put("createTimeEnd", endTime);

        // 端口平台新客户
        map.put("cusFrom", "58付费");
        map.put("focusType", "new");
        int portPlatformNewCus = focusReportDbDao.selectByMapFocusCount(map);
        f.setPortPlatformNewCus(portPlatformNewCus);

        // 新客户预约
        map.put("cusFrom", "官网预约");
        int newCusDepute = focusReportDbDao.selectByMapFocusCount(map);
        f.setNewCusDepute(newCusDepute);

        // 端口平台老客户
        map.put("cusFrom", "58付费");
        map.put("focusType", "old");
        int portPlatformUpCus = focusReportDbDao.selectByMapFocusCount(map);
        f.setPortPlatformUpCus(portPlatformUpCus);

        // 老客户预约
        map.put("cusFrom", "官网预约");
        int upCusDepute = focusReportDbDao.selectByMapFocusCount(map);
        f.setUpCusDepute(upCusDepute);
        map.clear();

        // 公共参数
        if (reportType.equals(ReportType.DEPT)) {
            map.put("createDeptCodes", param);
        } else {
            map.put("createCode", param);
        }
        map.put("createTimeStart", startTime);
        map.put("createTimeEnd", endTime);

        // 私人新客户
        map.put("cusType", CustomerType.AGENT);
        int privateNewCus = focusReportDbDao.selectCusCountByMap(map);
        f.setPrivateNewCus(privateNewCus);

        // 私人老客户
        map.put("cusStatus", true);
        map.put("cusType", CustomerType.AGENT);
        int privateUpCus = focusReportDbDao.selectCusCountByMap(map);
        f.setPrivateUpCus(privateUpCus);

        // 平台老客户
        map.put("cusStatus", true);
        map.put("notEmpPost", "PT201712210005ce7f");
        map.put("cusType", CustomerType.PLATFORM);
        int platformUpCus = focusReportDbDao.selectCusCountByMap(map);
        f.setPlatformUpCus(platformUpCus);

        // 总和 所有新增 上架 客户的总和
        f.setCusSum(portPlatformNewCus + newCusDepute + portPlatformUpCus + upCusDepute + privateNewCus + privateUpCus + platformUpCus);

        return f;
    }


    private void getTableHead(CommonReportDto<FocusReportEntity> reportDto) {
        Map<String, String> tableHead = new LinkedHashMap<>();
        tableHead.put("showName", "名称");
        tableHead.put("cusSum", "获客总量");
        tableHead.put("portPlatformNewCus", "端口新客户");
        tableHead.put("portPlatformUpCus", "端口老客户");
        tableHead.put("newCusDepute", "新客户官网预约");
        tableHead.put("upCusDepute", "老客户官网预约");
        tableHead.put("privateNewCus", "私人新客户");
        tableHead.put("privateUpCus", "私人老客户");
        tableHead.put("platformUpCus", "平台老客户");
        reportDto.setTableHeads(tableHead);
    }

    public void getReportSum(List<FocusReportEntity> reports) {

        int cusSum = 0;
        int portPlatformNewCus = 0;
        int portPlatformUpCus = 0;
        int newCusDepute = 0;
        int upCusDepute = 0;
        int privateNewCus = 0;
        int privateUpCus = 0;
        int platformUpCus = 0;

        for (FocusReportEntity f : reports) {
            cusSum += f.getCusSum();
            portPlatformNewCus += f.getPortPlatformNewCus();
            portPlatformUpCus += f.getPortPlatformUpCus();
            newCusDepute += f.getNewCusDepute();
            upCusDepute += f.getUpCusDepute();
            privateNewCus += f.getPrivateNewCus();
            privateUpCus += f.getPrivateUpCus();
            platformUpCus += f.getPlatformUpCus();
        }

        FocusReportEntity fe = new FocusReportEntity();
        fe.setShowName("总计");
        fe.setCusSum(cusSum);
        fe.setPortPlatformNewCus(portPlatformNewCus);
        fe.setPortPlatformUpCus(portPlatformUpCus);
        fe.setNewCusDepute(newCusDepute);
        fe.setUpCusDepute(upCusDepute);
        fe.setPrivateNewCus(privateNewCus);
        fe.setPrivateUpCus(privateUpCus);
        fe.setPlatformUpCus(platformUpCus);

        reports.add(0, fe);

    }
}
