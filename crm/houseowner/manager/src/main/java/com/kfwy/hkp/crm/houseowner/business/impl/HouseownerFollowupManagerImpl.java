package com.kfwy.hkp.crm.houseowner.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.crm.houseowner.business.IHouseownerFollowupManager;
import com.kfwy.hkp.crm.houseowner.dao.IHouseownerFollowupDbDao;
import com.kfwy.hkp.crm.houseowner.entity.HouseownerFollowupEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hjh on 2018/10/18.
 */
@Service
public class HouseownerFollowupManagerImpl extends AbstractManager<HouseownerFollowupEntity> implements IHouseownerFollowupManager {

    @Autowired
    private IHouseownerFollowupDbDao houseownerDbDao;

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.houseownerDbDao;
    }

}
