package com.kfwy.hkp.crm.customer.business;


import com.gniuu.framework.common.business.IManager;
import com.kfwy.hkp.crm.customer.entity.CustomerApplyEntity;

import java.util.Map;

/**
 * Create By hjh on 2018/8/11
 */
public interface ICustomerApplyManager extends IManager<CustomerApplyEntity> {

    public int toDayByCount(Map<String, Object> map);
    public String checkApplyCountStatistics();
}
