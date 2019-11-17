package com.kfwy.hkp.trade.order.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.trade.order.entity.OrderUpdateApplyEntity;

/**
 * 订单修改申请 dao
 */
public interface IOrderUpdateApplyDao extends IMyBatisBaseDao<OrderUpdateApplyEntity,Long> {

    /**
     * 订单修改申请详情
     * @param orderCode
     * @return
     */
    OrderUpdateApplyEntity detail(String orderCode);
}
