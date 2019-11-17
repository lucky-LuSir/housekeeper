package com.kfwy.hkp.trade.order.dao.impl;

import com.gniuu.framework.dataaccess.enums.DataAccessErrorMsg;
import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.gniuu.framework.utils.ValidateUtils;
import com.kfwy.hkp.trade.order.dao.IOrderDbDao;
import com.kfwy.hkp.trade.order.entity.OrderEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by zjp on 2018/8/15.
 */
@Repository
public class OrderDbDaoImpl extends AbstractMyBatisDao<OrderEntity,Long>   implements IOrderDbDao {

    @Override
    public OrderEntity detail(String cooCode) {
        return this.getSqlSession().selectOne(this.mapperNamespace + "detail", cooCode);
    }

    @Override
    public int update(OrderEntity order) {
        ValidateUtils.notNull(order, DataAccessErrorMsg.UpdateArgsEmpty);
        List<OrderEntity> entities = new ArrayList<OrderEntity>();
        entities.add(order);
        return this.batchUpdate(entities);
    }

    @Override
    public long selectMoneyReturn() {
        return this.getSqlSession().selectOne(this.mapperNamespace + "selectMoneyReturn");
    }

    @Override
    public List<OrderEntity> orderStatistics(Map<String, Object> map) {
        return this.getSqlSession().selectList(this.mapperNamespace + "orderStatistics",map);
    }

    @Override
    public List<OrderEntity> orderDivideInto(Map<String, Object> map) {
        return this.getSqlSession().selectList(this.mapperNamespace + "orderDivideInto",map);
    }

    @Override
    public List<OrderEntity> orderTransDept(Map<String, Object> map) {
        return this.getSqlSession().selectList(this.mapperNamespace + "orderTransDept",map);
    }

    @Override
    public List<OrderEntity> selectOrderHistory(Map<String, Object> map) {
        return this.getSqlSession().selectList(this.mapperNamespace + "selectOrderHistory",map);
    }

    @Override
    public int simpleCountByMap(Map<String, Object> map) {
        return this.getSqlSession().selectOne(this.mapperNamespace + "simpleCountByMap",map);
    }

    @Override
    public List<OrderEntity> selectOrderByPayBack(Map<String, Object> map) {
        return this.getSqlSession().selectList(this.mapperNamespace + "selectOrderByPayBack",map);
    }
}
