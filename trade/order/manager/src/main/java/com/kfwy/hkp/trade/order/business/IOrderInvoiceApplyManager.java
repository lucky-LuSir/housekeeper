package com.kfwy.hkp.trade.order.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.hkp.trade.order.entity.OrderInvoiceApplyEntity;


/**
 * 订单开票申请manager
 */
public interface IOrderInvoiceApplyManager extends IManager<OrderInvoiceApplyEntity> {

    public void createApply(OrderInvoiceApplyEntity orderInvoiceApplyEntity);

    }
