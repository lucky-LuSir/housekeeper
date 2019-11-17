package com.kfwy.hkp.trade.order.dao;


import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.trade.order.entity.OrderPaybackEntity;

import java.util.List;
import java.util.Map;


/**
 * Created by bruce on 2016/11/24.
 */
public interface IOrderPaybackDbDao extends IMyBatisBaseDao<OrderPaybackEntity, Long> {
    public OrderPaybackEntity selectPayBackPayment(Map<String, Object> map);

    public int updateDeductions(Map<String,Object> param);

    public int updateDeductionsSecond(Map<String,Object> param);

    List<OrderPaybackEntity> selectPayBack(Map<String,Object> param);

    OrderPaybackEntity selectDeductionsForInvoice(Map<String,Object> param);

}
