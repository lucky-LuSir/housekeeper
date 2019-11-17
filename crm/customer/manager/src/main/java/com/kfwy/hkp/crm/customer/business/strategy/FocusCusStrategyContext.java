package com.kfwy.hkp.crm.customer.business.strategy;

import com.kfwy.hkp.base.FocusCusConfig;
import com.kfwy.hkp.base.SystemConfig;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 集中获客策略上下文
 */
@Component
public  class FocusCusStrategyContext  {
    private final Map<String, FocusCusStrategy> strategyMap = new ConcurrentHashMap<> ();

    @Autowired
    public FocusCusStrategyContext (Map<String, FocusCusStrategy> strategyMap) {
        this.strategyMap.clear ();
        strategyMap.forEach (this.strategyMap :: put);
    }


    public List<UserEntity>  addTargetUsersForFocusCus (CustomerEntity customer, List<UserEntity> targetUsers, FocusCusConfig focusCusConfig) {

      return this.strategyMap.get (focusCusConfig.getStrategy ()).handle (customer, targetUsers, focusCusConfig);
    }




}
