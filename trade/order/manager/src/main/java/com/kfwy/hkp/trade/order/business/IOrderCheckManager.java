package com.kfwy.hkp.trade.order.business;

import com.kfwy.hkp.trade.order.entity.OrderInvoiceApplyEntity;
import com.kfwy.hkp.trade.order.entity.OrderInvoiceEntity;

/**
 * 订单权限检查接口
 */
public interface IOrderCheckManager {

    //每月调用企查查接口默认最高次数的键
    String qiChaChaDefaultRedisKey = "ky:hkp:qiChaCha:defaultNum";
    //每月已经调用企查查接口多少次的键前缀
    String qiChaChaCurrentRedisKey = "ky:hkp:qiChaCha:currentNum:";
    /**
     * 订单开票申请创建 检查
     */
    void orderInvoiceApplyCreateCheck(OrderInvoiceApplyEntity orderInvoiceApplyEntity);

    /**
     * 调用企查查接口获取公司税务信息  检查
     */
    String getCompanyCheck();
}
