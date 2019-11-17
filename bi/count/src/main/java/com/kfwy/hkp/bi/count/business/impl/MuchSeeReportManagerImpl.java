package com.kfwy.hkp.bi.count.business.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.bi.count.business.IMuchSeeReportManager;
import com.kfwy.hkp.bi.count.dao.IMuchSeeReportDbDao;
import com.kfwy.hkp.bi.count.dto.CommonReportDto;
import com.kfwy.hkp.bi.count.entity.MuchSeeEntity;
import com.kfwy.hkp.bi.count.entity.ThreadQuery;
import com.kfwy.hkp.bi.count.enums.ReportType;
import com.kfwy.hkp.hrm.org.dept.business.IDeptManager;
import com.kfwy.hkp.hrm.org.dept.entity.DeptEntity;
import com.kfwy.hkp.sys.auth.dao.IReportAccessDbDao;
import com.kfwy.hkp.sys.user.dao.IUserDbDao;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Create By hjh on 2018/11/28
 */
@Service
public class MuchSeeReportManagerImpl extends AbstractManager<MuchSeeEntity> implements IMuchSeeReportManager {

    @Autowired
    private IMuchSeeReportDbDao muchSeeReportDbDao;
    @Autowired
    private IDeptManager deptManager;
    @Autowired
    private IUserDbDao userDbDao;
    @Autowired
    private IReportAccessDbDao reportAccessDbDao;

    @Override
    protected IMyBatisBaseDao<MuchSeeEntity, Long> getMyBatisDao() {
        return muchSeeReportDbDao;
    }

    @Override
    public CommonReportDto<MuchSeeEntity> count(Date startTime, Date endTime, String code) {

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

        CommonReportDto<MuchSeeEntity> reportDto = new CommonReportDto();
        List<MuchSeeEntity> reports = Collections.synchronizedList(new ArrayList<>());
        // 查询参数
        List<ThreadQuery> threadQuerys = new ArrayList<>();
        Map<String, Object> param = new HashMap<>();
        String type = null;
        // 设置线程池的数量大小
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "15");
        // 查询部门
        DeptEntity dept = deptManager.findOne("deptCode", code);

        if (null == dept) {
            // 个人报表
            param.put("userCode", code);
            param.put("isDeleted", Boolean.FALSE);
            List<UserEntity> userEntities = userDbDao.selectSimpleUserByMap(param);
            if (CollectionUtil.isNotEmpty(userEntities)) {
                return reportDto;
            }
            getUserReport(startTime, endTime, reports, userEntities);
            type = ReportType.EMP;
        } else {
            // 查询下级部门
            param.put("deleteTimeStart", startTime);
            param.put("createTimeEnd", endTime);
            param.put("parentCode", code);
            List<DeptEntity> deptEntities = deptManager.findByMap(param);
            param.clear();
            if (CollectionUtil.isEmpty(deptEntities)) {
                // 查询部门下所有人员
                param.put("ownerDeptCode", code);
                param.put("quitTimeStart", startTime);
                param.put("entryTimeEnd", endTime);
                List<UserEntity> userEntities = userDbDao.selectSimpleUserByMap(param);

                if (CollectionUtil.isEmpty(userEntities)) {
                    return reportDto;
                }
                // 获取人员报表信息
                getUserReport(startTime, endTime, reports, userEntities);
                type = ReportType.EMP;
            } else {
                type = ReportType.DEPT;
                if (dept.getDeptLevel() == 2) {
                    type = ReportType.REGIONAL;
                }
                for (DeptEntity d : deptEntities) {
                    // 获取所有下级部门编码包括自身
                    List<String> deptCodes = deptManager.getValidDownDeptCode(d.getDeptCode(), startTime, endTime);
                    ThreadQuery query = new ThreadQuery();
                    query.setShowName(d.getDeptName());
                    query.setParams(deptCodes);
                    threadQuerys.add(query);
                }
                gerDeptReport(startTime, endTime, reports, threadQuerys);
            }
        }

        getTableHead(reportDto, type);
        reportDto.setReportEntities(reports);
        return reportDto;
    }

    private void getTableHead(CommonReportDto<MuchSeeEntity> reportDto, String type) {
        Map<String, String> tableHead = new LinkedHashMap<>();
        if (type.equals(ReportType.REGIONAL)) {
            tableHead.put("showName", "大区");
            tableHead.put("deptNumber", "部门数");
        } else if (type.equals(ReportType.DEPT)) {
            tableHead.put("showName", "部门名称");
            tableHead.put("manNumber", "团队人数");
        } else {
            tableHead.put("showName", "服务专员");
            tableHead.put("entryTime", "入职时间");
        }

        tableHead.put("oneFollowup", "一次带看客户数");
        tableHead.put("twoFollowup", "两次带看客户数");
        tableHead.put("threeFollowup", "三次带看客户数");
        tableHead.put("fourFollowup", "四次带看客户数");
        tableHead.put("fiveFollowup", "五次以上带看客户数");

        reportDto.setTableHeads(tableHead);
    }

    private void gerDeptReport(Date startTime, Date endTime, List<MuchSeeEntity> reports, List<ThreadQuery> threadQuerys) {
        threadQuerys.parallelStream().forEach(e -> {
            try {
                Map<String, Object> param = new HashMap<>();
                // 查询部门下所有人员
                param.put("ownerDeptCodes", e.getParams());
                param.put("quitTimeStart", startTime);
                param.put("entryTimeEnd", endTime);
                List<String> userCodes = userDbDao.selectUserCodeByMap(param);

                MuchSeeEntity ms = getDepReportValue(startTime, endTime, e.getParams());
                // 名称
                ms.setShowName(e.getShowName());
                // 部门数
                ms.setDeptNumber(e.getParams().size() - 1);
                // 设置团队人数
                ms.setManNumber(userCodes.size());
                reports.add(ms);
            } catch (Exception ex) {
                ex.printStackTrace();
                System.err.println("并行流异常");
            }
        });
    }

    private MuchSeeEntity getDepReportValue(Date startTime, Date endTime, List<String> deptCodes) {
        Map<String, Object> param = new HashMap<>();
        MuchSeeEntity ms = new MuchSeeEntity();
        param.put("createDeptCodes", deptCodes);
        param.put("createTimeStart", startTime);
        param.put("createTimeEnd", endTime);
        param.put("isDeleted", Boolean.FALSE);
        param.put("groupBy", "cus_code,create_code");
        setValues(param, ms);
        return ms;
    }

    private void getUserReport(Date startTime, Date endTime, List<MuchSeeEntity> reports, List<UserEntity> userEntities) {
        userEntities.parallelStream().forEach(e -> {
            MuchSeeEntity ms = getUserReportValue(startTime, endTime, e);
            reports.add(ms);
        });
    }

    private MuchSeeEntity getUserReportValue(Date startTime, Date endTime, UserEntity u) {
        Map<String, Object> param = new HashMap<>();
        MuchSeeEntity ms = new MuchSeeEntity();
        ms.setShowName(u.getUserName());
        ms.setEntryTime(DateUtil.format(u.getEntryTime(), DatePattern.NORM_DATE_PATTERN));

        param.put("createCode", u.getUserCode());
        param.put("createTimeStart", startTime);
        param.put("createTimeEnd", endTime);
        param.put("isDeleted", Boolean.FALSE);
        param.put("groupBy", "cus_code,create_code");

        setValues(param, ms);
        return ms;
    }

    private void setValues(Map<String, Object> param, MuchSeeEntity ms) {
        param.put("followupCount", 1);
        int one = muchSeeReportDbDao.manyTimesFollowup(param);
        ms.setOneFollowup(one);

        param.put("followupCount", 2);
        int two = muchSeeReportDbDao.manyTimesFollowup(param);
        ms.setTwoFollowup(two);

        param.put("followupCount", 3);
        int three = muchSeeReportDbDao.manyTimesFollowup(param);
        ms.setThreeFollowup(three);

        param.put("followupCount", 4);
        int four = muchSeeReportDbDao.manyTimesFollowup(param);
        ms.setFourFollowup(four);

        param.put("followupCount", 5);
        int five = muchSeeReportDbDao.manyTimesFollowup(param);
        ms.setFiveFollowup(five);
    }
}
