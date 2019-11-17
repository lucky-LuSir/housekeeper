package com.kfwy.hkp.trade.order.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.trade.order.dao.IOrderInvoiceApplyDbDao;
import com.kfwy.hkp.trade.order.entity.OrderInvoiceApplyEntity;
import org.springframework.stereotype.Repository;


/**
 * 订单开票申请dao实现
 */
@Repository
public class OrderInvoiceApplyDbDaoImpl extends AbstractMyBatisDao<OrderInvoiceApplyEntity,Long> implements IOrderInvoiceApplyDbDao {

}
