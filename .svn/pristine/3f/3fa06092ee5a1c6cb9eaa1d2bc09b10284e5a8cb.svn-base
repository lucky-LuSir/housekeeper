package com.kfwy.hkp.trade.order.business;


import com.gniuu.framework.common.business.IManager;
import com.kfwy.hkp.trade.order.entity.OrderEntity;
import com.kfwy.hkp.trade.order.entity.OrderPercentageEntity;

import java.util.List;

/**
 * Created by zjp on 2018/8/15.
 */
public interface IOrderPercentageManager extends IManager<OrderPercentageEntity> {

    /**
     * 更新分成信息
     * @param orderPercentageEntityList
     * @param orderEntity
     */
    void update(List<OrderPercentageEntity> orderPercentageEntityList, OrderEntity orderEntity);

    /**
     * @param orderPercentageEntity
     * @param userCode 订单成交人
     * @return
     */
    public int createServicePe(OrderPercentageEntity orderPercentageEntity,String userCode);
}
