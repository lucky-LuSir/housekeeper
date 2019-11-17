package com.kfwy.hkp.sys.user.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.sys.user.business.IPortUserManager;
import com.kfwy.hkp.sys.user.dao.IPortUserDbDao;
import com.kfwy.hkp.sys.user.entity.PortUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PortUserManagerImpl extends AbstractManager<PortUserEntity> implements IPortUserManager {

    @Autowired
    private IPortUserDbDao portUserDbDao;

    @Override
    protected IMyBatisBaseDao<PortUserEntity, Long> getMyBatisDao () {
        return portUserDbDao;
    }

    @Override
    public int deleteAll () {
        return portUserDbDao.deleteAll ();
    }

    @Override
    public int createAll (List<PortUserEntity> list) {
        return portUserDbDao.batchInsert (list);
    }

    @Override
    public PortUserEntity findPortUserByLittlePhone (String caller) {
        return portUserDbDao.findPortUserByLittlePhone (caller);
    }
}
