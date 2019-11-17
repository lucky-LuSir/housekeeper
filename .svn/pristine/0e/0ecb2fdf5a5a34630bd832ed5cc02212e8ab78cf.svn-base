package com.kfwy.hkp.crm.customer.business.strategy;


import com.kfwy.hkp.base.FocusCusConfig;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.sys.user.entity.UserEntity;

import java.util.List;

/**
 * 集中获客算法策略接口
 */
public interface FocusCusStrategy {

    String DEPARTMENT_MANAGER = "PT201603310001";

    String CUSTOMER_SERVICE = "PT201712210005ce7f";

    String CUSTOMER_SERVICE_DIRECTOR = "PT201712080001d5ba";

    String CUSTOMER_SERVICE_GROUP_LEADER = "PT2019010200017450";

    List<UserEntity> handle (CustomerEntity customer, List<UserEntity> targetUsers, FocusCusConfig focusCusConfig);

    List<UserEntity> handleFocusCus (CustomerEntity customer, List<UserEntity> targetUsers, FocusCusConfig focusCusConfig, Integer remainFocusCusCount);

}
