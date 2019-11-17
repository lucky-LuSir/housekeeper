package com.kfwy.hkp.trade.order.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.trade.order.dao.IOrderInvoiceDbDao;
import com.kfwy.hkp.trade.order.entity.OrderInvoiceEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author:ChenJie Created by kfwy_it_009 on 2018/11/11.
 */
@Repository
public class OrderInvoiceDbDaoImpl extends AbstractMyBatisDao<OrderInvoiceEntity,Long> implements IOrderInvoiceDbDao {
    @Override
    public List<OrderInvoiceEntity> selectInvoiceWithoutPayBack(Map<String, Object> param) {
        return this.getSqlSession().selectList(this.mapperNamespace + "selectInvoiceWithoutPayBack", param);

    }
    @Override
    public int updateDeductions(Map<String, Object> param) {
        return this.getSqlSession().update(this.mapperNamespace + "updateDeductions",param);
    }

    @Override
    public List<OrderInvoiceEntity> selectInvoiceWithoutDeductions(Map<String, Object> param) {
        return this.getSqlSession().selectList(this.mapperNamespace + "selectInvoiceWithoutDeductions", param);
    }

    @Override
    public OrderInvoiceEntity selectOne(Map<String, Object> param) {
        return this.getSqlSession().selectOne(this.mapperNamespace + "selectOne", param);

    }
}
