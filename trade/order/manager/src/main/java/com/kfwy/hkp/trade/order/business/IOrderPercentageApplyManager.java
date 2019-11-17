package com.kfwy.hkp.trade.order.business;


import com.gniuu.framework.common.business.IManager;
import com.kfwy.hkp.trade.order.entity.OrderEntity;
import com.kfwy.hkp.trade.order.entity.OrderPercentageApplyEntity;
import com.kfwy.hkp.trade.order.entity.OrderUpdateApplyEntity;

import java.util.List;

/**
 * 订单分成修改 申请 manager
 */
public interface IOrderPercentageApplyManager extends IManager<OrderPercentageApplyEntity> {

    /**
     * 更新分成信息
     * @param orderPercentageApplyEntityList
     * @param orderUpdateApplyEntity
     */
    void update(List<OrderPercentageApplyEntity> orderPercentageApplyEntityList, OrderUpdateApplyEntity orderUpdateApplyEntity);

    /**
     * @param orderPercentageApplyEntity
     * @param userCode 订单成交人
     * @return
     */
    public int createServicePe(OrderPercentageApplyEntity orderPercentageApplyEntity, String userCode);
}
