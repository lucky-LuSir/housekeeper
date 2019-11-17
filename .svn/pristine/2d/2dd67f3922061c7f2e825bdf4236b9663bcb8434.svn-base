package com.kfwy.hkp.crm.customer.business;

import com.gniuu.framework.common.business.IManager;
import com.gniuu.framework.dataaccess.PageResult;
import com.kfwy.hkp.crm.customer.entity.CustomerFollowupEntity;
import com.kfwy.hkp.crm.customer.entity.FollowupHouseEntity;
import io.swagger.models.auth.In;

import java.util.Map;

/**
 * Create By hjh on 2018/8/23
 */
public interface ICustomerFollowupManager extends IManager<CustomerFollowupEntity> {
    PageResult<CustomerFollowupEntity> selectByMap(Map<String,Object> param, Integer start, Integer pageSize);

    PageResult<FollowupHouseEntity> selectCusSeeHouse(Map param,Integer start,Integer pageSize);

    PageResult<CustomerFollowupEntity> takeLookByCusEvaluate(Map param, Integer start,Integer pageSize);
}
