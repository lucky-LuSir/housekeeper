package com.kfwy.hkp.bi.count.business.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.bi.count.business.INotOrderPersonManager;
import com.kfwy.hkp.bi.count.dto.CommonReportDto;
import com.kfwy.hkp.bi.count.entity.NotOrderPersonEntity;
import com.kfwy.hkp.bi.count.entity.ThreadQuery;
import com.kfwy.hkp.hrm.org.dept.business.IDeptManager;
import com.kfwy.hkp.hrm.org.dept.entity.DeptEntity;
import com.kfwy.hkp.sys.user.dao.IUserDbDao;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import com.kfwy.hkp.trade.order.dao.IOrderDbDao;
import com.kfwy.hkp.trade.order.enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Auther: HJH
 * @Date: 2019/6/13
 * @Description: TODO
 */
@Service
public class NotOrderPersonManagerImpl extends AbstractManager<NotOrderPersonEntity> implements INotOrderPersonManager {

    @Autowired
    private IDeptManager deptManager;
    @Autowired
    private IUserDbDao userDbDao;
    @Autowired
    private IOrderDbDao orderDbDao;

    @Override
    protected IMyBatisBaseDao<NotOrderPersonEntity, Long> getMyBatisDao() {
        return null;
    }

    @Override
    public CommonReportDto<NotOrderPersonEntity> select(Date startTime, Date endTime, String code) {
        // 设置线程池的数量大小
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "15");
        // 结果集
        CommonReportDto<NotOrderPersonEntity> reportDto = new CommonReportDto<>();
        // 数据结果
        List<NotOrderPersonEntity> results = Collections.synchronizedList(new ArrayList<>());
        // 参数
        Map<String, Object> map = new HashMap<>();
        // 查询部门
        DeptEntity dept = deptManager.findOne("deptCode", code);
        // 判断组织架构
        if (null == dept) {
            // 无关个人查询
            return reportDto;
        } else {
            // 查询下级部门
            map.put("isDeleted", false);
            map.put("parentCode", code);
            List<DeptEntity> depts = deptManager.findByMap(map);
            map.clear();
            if (depts == null || depts.isEmpty()) {
                // 查询人员
                map.put("ownerDeptCode", code);
                map.put("quitTimeStart", startTime);
                map.put("entryTimeEnd", endTime);
                List<UserEntity> userEntities = userDbDao.selectSimpleUserByMap(map);
                if (CollectionUtil.isEmpty(userEntities)) {
                    return reportDto;
                }
                this.getReportValue(results, startTime, endTime, userEntities);
            } else {
                List<ThreadQuery> ts = new ArrayList<>();
                // 获取所有下级部门编码包括自身
                for (DeptEntity d : depts) {
                    List<String> deptCodes = deptManager.getDownDeptCode(d.getDeptCode());
                    ThreadQuery query = new ThreadQuery();
                    query.setShowName(d.getDeptName());
                    query.setParams(deptCodes);
                    ts.add(query);
                }
                // 获取数据
                this.getDeptReports(ts, results, startTime, endTime);
            }
        }
        reportDto.setReportEntities(results);
        return reportDto;
    }

    public void getDeptReports(List<ThreadQuery> ts, List<NotOrderPersonEntity> results, Date startTime, Date endTime) {
        ts.parallelStream().forEach(e -> {
            try {

                Map<String, Object> map = new HashMap<>();
                // 查询人员编码
                map.put("ownerDeptCodes", e.getParams());
                map.put("quitTimeStart", startTime);
                map.put("entryTimeEnd", endTime);
                List<UserEntity> userEntities = userDbDao.selectSimpleUserByMap(map);
                map.clear();
                // 跳出本次循环
                if (userEntities.size() == 0) {
                    return;
                }
                this.getReportValue(results, startTime, endTime, userEntities);

            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("并行流异常");
            }
        });
    }

    private void getReportValue(List<NotOrderPersonEntity> results, Date startTime, Date endTime, List<UserEntity> userEntities) {
        for (UserEntity u : userEntities) {

            Map<String, Object> map = new HashMap<>();

            // 剔除总经理,大区经理和分部经理,不包括组长
            if (u.getEmpPostCode().equals("PT2018042800018b7f") || u.getEmpPostCode().equals("PT2017110100022121")
                    || u.getEmpPostCode().equals("PT201603310001")) {
                continue;
            }

            // 排除,毁单,坏账,取消
            List<String> noOrderStatuses = new ArrayList<>();
            noOrderStatuses.add(OrderStatus.Destroy);
            noOrderStatuses.add(OrderStatus.BadDebt);
            noOrderStatuses.add(OrderStatus.HasCancel);

            map.put("noOrderStatuses", noOrderStatuses);
            map.put("orderTimeStart", startTime);
            map.put("orderTimeEnd", endTime);
            map.put("isDeleted", Boolean.FALSE);

            // 剔除开单人员
            map.put("empCode", u.getUserCode());
            if (orderDbDao.countByMap(map) > 0) {
                continue;
            }

            NotOrderPersonEntity result = new NotOrderPersonEntity();
            // 名称
            result.setUserName(u.getUserName());
            // 部门
            result.setDeptName(u.getOwnerDeptName());
            // 入职时间
            String entryDate = DateUtil.formatDate(u.getEntryTime());
            result.setEntryTime(entryDate);
            // 离职时间
            String quitDate = DateUtil.formatDate(u.getQuitTime());
            result.setQuitTime(quitDate);
            // 入职时长
            Date today = new Date();
            Date entryTime = u.getEntryTime();
            long betweenDay = DateUtil.between(today, entryTime, DateUnit.DAY);
            result.setEntryDuration(betweenDay + "");
            results.add(result);

        }
    }
}
