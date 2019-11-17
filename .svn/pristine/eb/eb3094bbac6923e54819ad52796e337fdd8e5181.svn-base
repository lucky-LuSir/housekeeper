package com.kfwy.hkp.trade.order.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.trade.order.entity.OrderEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by zjp on 2018/8/15.
 */
public interface IOrderDbDao extends IMyBatisBaseDao<OrderEntity,Long>{

    OrderEntity detail(String orderCode);

    public int update(OrderEntity order);

    public long selectMoneyReturn();

    List<OrderEntity> orderStatistics(Map<String,Object> map);

    List<OrderEntity> orderDivideInto(Map<String,Object> map);

    List<OrderEntity> orderTransDept(Map<String,Object> map);

    List<OrderEntity> selectOrderHistory(Map<String, Object> map);

    public int simpleCountByMap(Map<String,Object> map);

    public List<OrderEntity> selectOrderByPayBack(Map<String,Object> map);
}
