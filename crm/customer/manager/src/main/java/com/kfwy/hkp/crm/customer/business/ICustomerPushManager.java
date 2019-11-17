package com.kfwy.hkp.crm.customer.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.hkp.crm.customer.entity.CustomerPushEntity;

import java.util.List;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/10/25 17:27
 */
public interface ICustomerPushManager extends IManager<CustomerPushEntity> {

    /**
     * 批量新增
     * @param pushEntities
     * @return
     */
    public int batchCreate(List<CustomerPushEntity> pushEntities);
}
