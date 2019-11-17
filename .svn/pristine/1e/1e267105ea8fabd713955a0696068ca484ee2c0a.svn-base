package com.kfwy.hkp.bi.count.business.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.bi.count.business.INewPersonOrderManager;
import com.kfwy.hkp.bi.count.dto.CommonReportDto;
import com.kfwy.hkp.bi.count.entity.NewPersonOrderEntity;
import com.kfwy.hkp.bi.count.entity.ThreadQuery;
import com.kfwy.hkp.hrm.org.dept.business.IDeptManager;
import com.kfwy.hkp.hrm.org.dept.entity.DeptEntity;
import com.kfwy.hkp.sys.user.dao.IUserDbDao;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import com.kfwy.hkp.trade.order.dao.IOrderDbDao;
import com.kfwy.hkp.trade.order.dao.IOrderPercentageDbDao;
import com.kfwy.hkp.trade.order.entity.OrderEntity;
import com.kfwy.hkp.trade.order.enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * @Auther: HJH
 * @Date: 2019/6/15
 * @Description: TODO
 */
@Service
public class NewPersonOrderManagerImpl extends AbstractManager<NewPersonOrderEntity> implements INewPersonOrderManager {

    @Autowired
    private IDeptManager deptManager;
    @Autowired
    private IUserDbDao userDbDao;
    @Autowired
    private IOrderDbDao orderDbDao;
    @Autowired
    private IOrderPercentageDbDao orderPercentageDbDao;

    @Override
    protected IMyBatisBaseDao<NewPersonOrderEntity, Long> getMyBatisDao() {
        return null;
    }

    @Override
    public CommonReportDto<NewPersonOrderEntity> select(Date startTime, Date endTime, String code) {
        // 设置线程池的数量大小
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "15");
        // 结果集
        CommonReportDto<NewPersonOrderEntity> reportDto = new CommonReportDto<>();
        // 数据结果
        List<NewPersonOrderEntity> results = Collections.synchronizedList(new ArrayList<>());
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

    public void getDeptReports(List<ThreadQuery> ts, List<NewPersonOrderEntity> results, Date startTime, Date endTime) {
        ts.parallelStream().forEach(e -> {
            try {
                Map<String, Object> map = new HashMap<>();
                // 查询当前时间段,新员工
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

    private void getReportValue(List<NewPersonOrderEntity> results, Date startTime, Date endTime, List<UserEntity> userEntities) {
        for (UserEntity u : userEntities) {
            Map<String, Object> map = new HashMap<>();
            // 排除,毁单,坏账,取消
            List<String> noOrderStatuses = new ArrayList<>();
            noOrderStatuses.add(OrderStatus.Destroy);
            noOrderStatuses.add(OrderStatus.BadDebt);
            noOrderStatuses.add(OrderStatus.HasCancel);

            map.put("noOrderStatuses", noOrderStatuses);
            map.put("orderTimeStart", startTime);
            map.put("orderTimeEnd", endTime);
            map.put("isDeleted", Boolean.FALSE);

            map.put("empCode", u.getUserCode());
            List<OrderEntity> orders = orderDbDao.selectByMap(map, "order_time", true);
            map.clear();
            // 指定时间段内开单的人,并且他的开单时间减去入职时间少于等于30天
            if (orders != null && !orders.isEmpty()) {
                OrderEntity orderEntity = orders.get(0);
                Date orderTime = orderEntity.getOrderTime();
                long as = DateUtil.between(orderTime, u.getEntryTime(), DateUnit.DAY);
                if (as <= 30) {
                    NewPersonOrderEntity result = new NewPersonOrderEntity();
                    // 名称
                    result.setUserName(u.getUserName());
                    // 部门
                    result.setDeptName(u.getOwnerDeptName());
                    // 入职时长
                    Date entryTime = u.getEntryTime();
                    result.setEntryTime(DateUtil.format(entryTime, "yyyy-MM-dd"));
                    // 离职时间
                    Date quitTime = u.getQuitTime();
                    result.setQuitTime(DateUtil.format(quitTime, "yyyy-MM-dd"));
                    // 入职到开单时间
                    result.setEntryReachOrderTime(as + "天");
                    // 开单数量
                    result.setOrderCount(orders.size());
                    // 开单金额
                    BigDecimal totalMoney = new BigDecimal(0);
                    for (OrderEntity ord : orders) {
                        totalMoney = totalMoney.add(ord.getCommission() != null ? ord.getCommission() : BigDecimal.ZERO);
                    }
                    result.setOrderMoney(totalMoney);
                    // 分成业绩
                    BigDecimal totalPercentage = new BigDecimal(0);
                    map.put("peEmpCode", u.getUserCode());
                    map.put("noOrderStatuses", noOrderStatuses);
                    map.put("orderTimeStart", startTime);
                    map.put("orderTimeEnd", endTime);
                    map.put("isDeleted", Boolean.FALSE);
                    BigDecimal ops = orderPercentageDbDao.selectByMapAndStatus(map);
                    totalPercentage = totalPercentage.add(ops != null ? ops : BigDecimal.ZERO);
                    result.setDivideMoney(totalPercentage);

                    results.add(result);
                }
            }
        }
    }
}
