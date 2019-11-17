package com.kfwy.hkp.bi.count.business.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.NumberUtil;
import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.bi.count.business.IOrderDivideRatioManager;
import com.kfwy.hkp.bi.count.dto.CommonReportDto;
import com.kfwy.hkp.bi.count.entity.OrderDivideRatioEntity;
import com.kfwy.hkp.bi.count.entity.ThreadQuery;
import com.kfwy.hkp.bi.count.enums.ReportType;
import com.kfwy.hkp.hrm.org.dept.business.IDeptManager;
import com.kfwy.hkp.hrm.org.dept.entity.DeptEntity;
import com.kfwy.hkp.sys.user.dao.IUserDbDao;
import com.kfwy.hkp.trade.order.dao.IOrderDbDao;
import com.kfwy.hkp.trade.order.dao.IOrderPercentageDbDao;
import com.kfwy.hkp.trade.order.enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Auther: HJH
 * @Date: 2019/6/10
 * @Description: TODO
 */
@Service
public class OrderDivideRatioManagerImpl extends AbstractManager<OrderDivideRatioEntity> implements IOrderDivideRatioManager {

    @Autowired
    private IDeptManager deptManager;
    @Autowired
    private IUserDbDao userDbDao;
    @Autowired
    private IOrderDbDao orderDbDao;
    @Autowired
    private IOrderPercentageDbDao orderPercentageDbDao;

    @Override
    protected IMyBatisBaseDao<OrderDivideRatioEntity, Long> getMyBatisDao() {
        return null;
    }

    @Override
    public CommonReportDto<OrderDivideRatioEntity> select(Date startTime, Date endTime, String code) {
        // 设置线程池的数量大小
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "15");
        // 结果集
        CommonReportDto<OrderDivideRatioEntity> reportDto = new CommonReportDto<>();
        // 数据结果
        List<OrderDivideRatioEntity> results = Collections.synchronizedList(new ArrayList<>());
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
                List<String> userCodes = userDbDao.selectUserCodeByMap(map);
                if (CollectionUtil.isEmpty(userCodes)) {
                    return reportDto;
                }
                this.getUserReport(startTime, endTime, userCodes, results, dept.getDeptName());
            } else {
                List<ThreadQuery> ts = new ArrayList<>();
                // 获取所有下级部门编码包括自身
                for (DeptEntity d : depts) {
                    List<String> deptCodes = deptManager.getValidDownDeptCode(d.getDeptCode(), startTime, endTime);
                    ThreadQuery query = new ThreadQuery();
                    query.setShowName(d.getDeptName());
                    query.setParams(deptCodes);
                    ts.add(query);
                }
                // 获取数据
                this.getDeptReport(startTime, endTime, ts, results);
                // 总计
                this.getSum(results);
            }
        }
        reportDto.setReportEntities(results);
        return reportDto;
    }

    private void getSum(List<OrderDivideRatioEntity> results) {
        int showNum = 0;
        int orderNum = 0;
        int orderPersonNum = 0;
        int divideIntoPersonNum = 0;
        for (OrderDivideRatioEntity o : results) {
            showNum += o.getShowNum();
            orderNum += o.getOrderNum();
            orderPersonNum += o.getOrderPersonNum();
            divideIntoPersonNum += o.getDivideIntoPersonNum();
        }
        OrderDivideRatioEntity entity = new OrderDivideRatioEntity();
        // 显示名称
        entity.setShowName("总计");
        // 人数
        entity.setShowNum(showNum);
        // 开单比
        entity.setOrderNum(orderNum);
        entity.setOrderPersonNum(orderPersonNum);
        entity.setOrderRatio(this.getPercentage(orderPersonNum, showNum));
        // 未开单人
        entity.setNotOrderPerson(showNum - orderPersonNum);
        // 分成比
        entity.setDivideIntoPersonNum(divideIntoPersonNum);
        entity.setDivideIntoRatio(this.getPercentage(divideIntoPersonNum, showNum));
        results.add(entity);
    }

    private void getDeptReport(Date startTime, Date endTime, List<ThreadQuery> ts, List<OrderDivideRatioEntity> results) {
        ts.parallelStream().forEach(e -> {
            // 查询人员编码
            Map<String, Object> map = new HashMap<>();
            map.put("ownerDeptCodes", e.getParams());
            map.put("quitTimeStart", startTime);
            map.put("entryTimeEnd", endTime);
            List<String> userCodes = userDbDao.selectUserCodeByMap(map);

            // 查询数据
            OrderDivideRatioEntity result = this.getReportValue(startTime, endTime, e.getParams(), ReportType.DEPT);
            // 显示名称
            result.setShowName(e.getShowName());
            // 人数
            result.setShowNum(userCodes.size());
            // 未开单人
            result.setNotOrderPerson(result.getShowNum() - result.getOrderPersonNum());
            // 开单比
            result.setOrderRatio(this.getPercentage(result.getOrderPersonNum(), result.getShowNum()));
            // 分成比
            result.setDivideIntoRatio(this.getPercentage(result.getDivideIntoPersonNum(), result.getShowNum()));
            results.add(result);
        });
    }

    private void getUserReport(Date startTime, Date endTime, List<String> userCodes, List<OrderDivideRatioEntity> results, String deptName) {
        // 所部人员的数据
        OrderDivideRatioEntity result = this.getReportValue(startTime, endTime, userCodes, ReportType.EMP);
        // 显示名称
        result.setShowName(deptName);
        // 人数
        result.setShowNum(userCodes.size());
        // 未开单人
        result.setNotOrderPerson(result.getShowNum() - result.getOrderPersonNum());
        // 开单比
        result.setOrderRatio(this.getPercentage(result.getOrderPersonNum(), result.getShowNum()));
        // 分成比
        result.setDivideIntoRatio(this.getPercentage(result.getDivideIntoPersonNum(), result.getShowNum()));
        results.add(result);
    }

    private OrderDivideRatioEntity getReportValue(Date startTime, Date endTime, Object param, String reportType) {
        // 结果实体
        OrderDivideRatioEntity result = new OrderDivideRatioEntity();
        // 参数
        Map<String, Object> map = new HashMap<>();

        // 排除,毁单,坏账,取消
        List<String> noOrderStatuses = new ArrayList<>();
        noOrderStatuses.add(OrderStatus.Destroy);
        noOrderStatuses.add(OrderStatus.BadDebt);
        noOrderStatuses.add(OrderStatus.HasCancel);

        if (reportType.equals(ReportType.DEPT)) {
            map.put("deptCodes", param);
        } else {
            map.put("empCodes", param);
        }

        // 开单数
        map.put("noOrderStatuses", noOrderStatuses);
        map.put("orderTimeStart", startTime);
        map.put("orderTimeEnd", endTime);
        map.put("isDeleted", Boolean.FALSE);
        int orderNum = orderDbDao.countByMap(map);
        result.setOrderNum(orderNum);

        // 开单人数(去重)
        map.put("groupBy", "emp_code");
        int orderPersonNum = orderDbDao.simpleCountByMap(map);
        result.setOrderPersonNum(orderPersonNum);

        map.clear();
        // 分成人数
        if (reportType.equals(ReportType.DEPT)) {
            map.put("peDeptCodes", param);
        } else {
            map.put("peEmpCodes", param);
        }
        map.put("noOrderStatuses", noOrderStatuses);
        map.put("orderTimeStart", startTime);
        map.put("orderTimeEnd", endTime);
        map.put("isDeleted", Boolean.FALSE);
        map.put("groupBy", "pe_emp_code");
        int divideIntoPersonNum = orderPercentageDbDao.simpleCountByMap(map);
        result.setDivideIntoPersonNum(divideIntoPersonNum);

        return result;
    }


    private String getPercentage(int a, int b) {
        if (b == 0) {
            return "0%";
        }
        Double as = Double.valueOf(a);
        Double bs = Double.valueOf(b);
        double v = (as / bs) * 100;
        return NumberUtil.decimalFormat("0.00", v) + "%";
    }
}
