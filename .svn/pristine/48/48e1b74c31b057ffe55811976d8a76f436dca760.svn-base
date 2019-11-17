package com.kfwy.hkp.bi.count.business.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.bi.count.business.IRecordManager;
import com.kfwy.hkp.bi.count.entity.RecordEntity;
import com.kfwy.hkp.bi.count.enums.ReportType;
import com.kfwy.hkp.crm.customer.dao.ICustomerDbDao;
import com.kfwy.hkp.crm.customer.enums.CustomerStatus;
import com.kfwy.hkp.hos.house.dao.IHouseDbDao;
import com.kfwy.hkp.hos.house.enums.HouseStatus;
import com.kfwy.hkp.hrm.org.dept.business.IDeptManager;
import com.kfwy.hkp.hrm.org.dept.entity.DeptEntity;
import com.kfwy.hkp.sys.auth.dao.IReportAccessDbDao;
import com.kfwy.hkp.sys.user.dao.IUserDbDao;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Create By hjh on 2019/2/22
 */
@Service
public class RecordManagerImpl extends AbstractManager<RecordEntity> implements IRecordManager {

    @Autowired
    private IDeptManager deptManager;
    @Autowired
    private IUserDbDao userDbDao;
    @Autowired
    private IHouseDbDao houseDbDao;
    @Autowired
    private ICustomerDbDao customerDbDao;
    @Autowired
    private IReportAccessDbDao reportAccessDbDao;

    @Override
    protected IMyBatisBaseDao<RecordEntity, Long> getMyBatisDao() {
        return null;
    }

    @Override
    public List<RecordEntity> count(String code, Date startTime, Date endTime) {

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

        List<RecordEntity> result = new ArrayList<>();

        DeptEntity dept = deptManager.findOne("deptCode", code);

        List<String> userCodes = new ArrayList<>();

        String reportType = "";

        Map<String, Object> map = new HashMap<>();

        if (dept == null) { // 个人
            reportType = ReportType.EMP;
            userCodes.add(code);
            UserEntity u = userDbDao.selectUniqueByProp("userCode", code);
            List<RecordEntity> r = this.selectRecord(u.getUserName(), u.getEntryTime(), 1, userCodes, startTime, endTime, reportType);
            if (r != null && !r.isEmpty()) {
                result.addAll(r);
            }
        } else { // 部门
            map.put("isDeleted", false);
            map.put("parentCode", code);
            List<DeptEntity> depts = deptManager.findByMap(map);
            reportType = ReportType.EMP;
            map.clear();
            if (depts.isEmpty()) {
                // 查询人员
                map.put("ownerDeptCode", code);
                map.put("quitTimeStart", startTime);
                //map.put("quitTimeEnd", endTime);
                map.put("entryTimeEnd", endTime);
                List<UserEntity> us = userDbDao.selectSimpleUserByMap(map);
                if (us != null && !us.isEmpty()) {
                    for (UserEntity u : us) {
                        userCodes.add(u.getUserCode());
                        List<RecordEntity> r = this.selectRecord(u.getUserName(), u.getEntryTime(), 1, userCodes, startTime, endTime, reportType);
                        userCodes.clear();
                        if (r != null && !r.isEmpty()) {
                            result.addAll(r);
                        }
                    }
                }
            } else {
                for (DeptEntity d : depts) {
                    List<String> deptCodes = deptManager.getDownDeptCode(d.getDeptCode());
                    // 查询人员
                    if (deptCodes.isEmpty()) {
                        reportType = ReportType.DEPT;
                        map.put("ownerDeptCode", d.getDeptCode());
                    } else {
                        reportType = ReportType.REGIONAL;
                        map.put("ownerDeptCodes", deptCodes);
                    }
                    map.put("quitTimeStart", startTime);
                    //map.put("quitTimeEnd", endTime);
                    map.put("entryTimeEnd", endTime);
                    List<UserEntity> us = userDbDao.selectSimpleUserByMap(map);
                    map.clear();
                    if (us != null && !us.isEmpty()) {
                        int lowerCount = us.size();
                        if (d.getDeptLevel() < 4) { // 不是分部则显示部门数
                            lowerCount = deptCodes.size() <= 0 ? 0 : deptCodes.size() - 1;
                        }
                        for (UserEntity u : us) {
                            userCodes.add(u.getUserCode());
                        }
                        List<RecordEntity> r = this.selectRecord(d.getDeptName(), d.getCreateTime(), lowerCount, userCodes, startTime, endTime, reportType);
                        userCodes.clear();
                        if (r != null && !r.isEmpty()) {
                            result.addAll(r);
                        }
                    }
                }
            }
        }
        return result;
    }

    private List<RecordEntity> selectRecord(String showName, Date showTime, int lowerCount, List<String> userCodes, Date startTime, Date endTime, String reportType) {
        List<RecordEntity> result = new ArrayList<>();
        RecordEntity re = new RecordEntity();
        re.setReportType(reportType);
        re.setShowName(showName);
        re.setShowTime(DateUtil.format(showTime, DatePattern.NORM_DATE_PATTERN));
        re.setLowerCount(lowerCount);
        // 查询房源指定条件数量
        this.selectHouse(userCodes, startTime, endTime, re);
        // 查询客户指定条件数量
        this.selectCustomer(userCodes, startTime, endTime, re);
        result.add(re);
        return result;
    }

    private void selectHouse(List<String> userCodes, Date startTime, Date endTime, RecordEntity re) {
        Map<String, Object> map = new HashMap<>();
        map.put("empCodes", userCodes);
        map.put("createTimeStart", startTime);
        map.put("createTimeEnd", endTime);

        // 查询指定时间内已出租房源数
        map.put("houseStatus", HouseStatus.HasLease);
        int houseRent = houseDbDao.countByMap(map);
        // 查询指定时间内热租中的房源信息
        map.put("houseStatus", HouseStatus.hotRenting);
        int houseNow = houseDbDao.countByMap(map);
        // 查询指定天数未跟进的房源数
        DateTime dateTime = DateUtil.offsetDay(new Date(), -10);
        map.put("endLastFollowupTime", DateUtil.beginOfDay(dateTime));
        map.put("houseStatus", HouseStatus.hotRenting);
        int noHouseFollow = houseDbDao.countByMap(map);

        re.setHouseRent(houseRent);
        re.setHouseNow(houseNow);
        re.setNoHouseFollow(noHouseFollow);
    }

    private void selectCustomer(List<String> userCodes, Date startTime, Date endTime, RecordEntity re) {
        Map<String, Object> map = new HashMap<>();
        map.put("empCodes", userCodes);
        map.put("createTimeStart", startTime);
        map.put("createTimeEnd", endTime);

        // 查询指定时间内已组好客户数
        map.put("cusStatus", CustomerStatus.HASDEAL);
        int cusRent = customerDbDao.countByMap(map);
        // 查询指定时间内跟进中客户数
        map.put("cusStatus", CustomerStatus.FOLLOWING);
        int cusNow = customerDbDao.countByMap(map);
        // 查询指定天数未跟进的客户数
        DateTime dateTime = DateUtil.offsetDay(new Date(), -3);
        map.put("lastFollowupTime", DateUtil.beginOfDay(dateTime));
        map.put("cusStatus", HouseStatus.hotRenting);
        int noCusFollow = customerDbDao.countByMap(map);

        re.setCustomerRent(cusRent);
        re.setCustomerNow(cusNow);
        re.setNoCustomerFollow(noCusFollow);
    }
}
