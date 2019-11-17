package com.kfwy.hkp.trade.order.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.hkp.trade.order.entity.OrderUpdateApplyEntity;

/**
 * 订单修改申请 manager实现
 */
public interface IOrderUpdateApplyManager extends IManager<OrderUpdateApplyEntity> {

    /**
     * 订单修改申请详情
     * @param orderCode
     * @return
     */
    OrderUpdateApplyEntity orderUpdateApplyDetail(String orderCode);

    /**
     * 全部审核完成  修改申请
     * @param orderUpdateApplyEntity
     */
    void auditFinish(OrderUpdateApplyEntity orderUpdateApplyEntity);
}
