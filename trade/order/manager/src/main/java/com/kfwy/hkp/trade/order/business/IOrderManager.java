package com.kfwy.hkp.trade.order.business;


import com.gniuu.framework.common.business.IManager;
import com.kfwy.hkp.trade.order.entity.DeptPostChangeEntity;
import com.kfwy.hkp.trade.order.entity.OrderEntity;
import com.kfwy.hkp.trade.order.entity.UserPostChangeEntity;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zjp on 2018/8/15.
 */
public interface IOrderManager extends IManager<OrderEntity> {

    /**
     * 通过orderCode来查询详情
     * @param orderCode
     * @return
     */
    OrderEntity detail(String orderCode);
    /**
     * 更新订单状态
     * @param orderEntity
     * @return
     */
    int udpateOrderStatus(OrderEntity orderEntity);

    int appUdpateOrderStatus(OrderEntity orderEntity);

    /**
     * 收回佣金确认书
     * @param orderEntity
     * @return
     */
    int hasCommissionConfirm(OrderEntity orderEntity);

    /**
     * 查询客服分成
     * @param empCode
     * @return
     */
    OrderEntity querycusservice(String empCode,String cusCode);

    /**
     *
     * @param map
     * @return
     */
     OrderEntity selectUniqueByMap(Map<String,Object> map);


     List<OrderEntity> orderStatistics(Map<String,Object> map);

     List<OrderEntity> orderDivideInto(Map<String,Object> map);

     List<OrderEntity> orderTransDept(Map<String,Object> map);

     List<OrderEntity> selectOrderHistory(Map<String,Object> map);

     int dealRatio() throws ParseException;

     int dealPost(List<UserPostChangeEntity> userPostChangeEntities);
    int dealPostGroup(List<UserPostChangeEntity> userPostChangeEntities);
    int dealPostLeader(List<DeptPostChangeEntity> deptPostChangeEntities);

    void dealPayBack(Date startTime,Date endTime,String flag);
    void dealPayBackHistory() throws ParseException;

    OrderEntity querySysAutoDivide(OrderEntity orderVEntity);
}
