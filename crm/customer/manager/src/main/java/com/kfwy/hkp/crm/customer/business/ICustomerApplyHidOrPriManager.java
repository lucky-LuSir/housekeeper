package com.kfwy.hkp.crm.customer.business;

import com.gniuu.framework.common.business.IManager;
import com.gniuu.framework.dataaccess.PageResult;
import com.kfwy.hkp.crm.customer.entity.CustomerApplyHidOrPriEntity;

import java.util.Map;

public interface ICustomerApplyHidOrPriManager extends IManager<CustomerApplyHidOrPriEntity> {
    PageResult<CustomerApplyHidOrPriEntity> selectByMap(Map map, Integer start, Integer pageSize);

    int apply(String cusCode,Integer type,String reason);

    int check(CustomerApplyHidOrPriEntity customerApplyHidOrPri);
}
