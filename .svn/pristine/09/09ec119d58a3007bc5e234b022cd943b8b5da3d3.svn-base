package com.kfwy.hkp.trade.order.dao.impl;


import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.trade.order.dao.IOrderPaybackDbDao;
import com.kfwy.hkp.trade.order.entity.OrderPaybackEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by bruce on 2016/11/24.
 */
@Repository
public class OrderPaybackDbDaoImpl extends AbstractMyBatisDao<OrderPaybackEntity, Long> implements IOrderPaybackDbDao {
    @Override
    public OrderPaybackEntity selectPayBackPayment(Map<String, Object> map) {
        return this.getSqlSession().selectOne(this.mapperNamespace + "selectPayBackPayment",map);
    }

    @Override
    public int updateDeductions(Map<String, Object> param) {
        return this.getSqlSession().update(this.mapperNamespace + "updateDeductions",param);
    }

    @Override
    public int updateDeductionsSecond(Map<String, Object> param) {
        return this.getSqlSession().update(this.mapperNamespace + "updateDeductionsSecond",param);

    }

    @Override
    public List<OrderPaybackEntity> selectPayBack(Map<String, Object> param) {
        return this.getSqlSession().selectList(this.mapperNamespace + "selectPayBack",param);
    }

    @Override
    public OrderPaybackEntity selectDeductionsForInvoice(Map<String, Object> param) {
        return this.getSqlSession().selectOne(this.mapperNamespace + "selectDeductionsForInvoice",param);
    }
}
