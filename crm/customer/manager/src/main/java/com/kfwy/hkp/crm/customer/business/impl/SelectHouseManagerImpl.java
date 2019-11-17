package com.kfwy.hkp.crm.customer.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.crm.customer.business.*;
import com.kfwy.hkp.crm.customer.dao.*;
import com.kfwy.hkp.crm.customer.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by xpp on 2018/7/16.
 */
@Service
public class SelectHouseManagerImpl extends AbstractManager<SelectHouseEntity> implements ISelectHouseManager {

    @Autowired
    private ISelectHouseDbDao selectHouseDbDao;


    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.selectHouseDbDao;
    }



}
