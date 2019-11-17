package com.kfwy.hkp.trade.order.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.trade.order.entity.OrderInvoiceEntity;

import java.util.List;
import java.util.Map;

/**
 * @Author:ChenJie Created by kfwy_it_009 on 2018/11/11.
 */
public interface IOrderInvoiceDbDao extends IMyBatisBaseDao<OrderInvoiceEntity, Long> {
    List<OrderInvoiceEntity> selectInvoiceWithoutPayBack(Map<String,Object> param);
    int updateDeductions(Map<String,Object> param);
    List<OrderInvoiceEntity> selectInvoiceWithoutDeductions(Map<String,Object> param);
    OrderInvoiceEntity selectOne(Map<String,Object> param);
}
