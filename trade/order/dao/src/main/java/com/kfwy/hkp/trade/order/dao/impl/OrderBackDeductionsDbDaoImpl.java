package com.kfwy.hkp.trade.order.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.trade.order.dao.IOrderBackDeductionsDbDao;
import com.kfwy.hkp.trade.order.entity.OrderBackDeductionsEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;


/**
 * @Author:lzp 2019/06/28.
 */
@Repository
public class OrderBackDeductionsDbDaoImpl extends AbstractMyBatisDao<OrderBackDeductionsEntity,Long> implements IOrderBackDeductionsDbDao {

    @Override
    public OrderBackDeductionsEntity selectOrderDeductions(Map<String, Object> param) {
        return this.getSqlSession().selectOne(this.mapperNamespace + "selectOrderDeductions", param);

    }
}
