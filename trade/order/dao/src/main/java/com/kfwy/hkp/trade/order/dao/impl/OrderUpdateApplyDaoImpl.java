package com.kfwy.hkp.trade.order.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.trade.order.dao.IOrderUpdateApplyDao;
import com.kfwy.hkp.trade.order.entity.OrderUpdateApplyEntity;
import org.springframework.stereotype.Repository;

/**
 * 订单修改申请 dao 实现
 */
@Repository
public class OrderUpdateApplyDaoImpl extends AbstractMyBatisDao<OrderUpdateApplyEntity,Long> implements IOrderUpdateApplyDao {


    @Override
    public OrderUpdateApplyEntity detail(String orderCode) {
        return this.getSqlSession().selectOne(this.mapperNamespace + "detail", orderCode);
    }
}
