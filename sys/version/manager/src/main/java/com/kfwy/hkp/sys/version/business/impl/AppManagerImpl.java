package com.kfwy.hkp.sys.version.business.impl;

import com.kfwy.hkp.sys.version.business.IAppManager;
import com.kfwy.hkp.sys.version.dao.IAppInfoRedisDao;
import com.kfwy.hkp.sys.version.entity.AppInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by davidcun on 2015-8-26.
 *
 * @author davidcun
 */
@Service
public class AppManagerImpl implements IAppManager {

    @Autowired
    private IAppInfoRedisDao appInfoRedisDao;

    /**
     * @param type
     * @return
     */
    @Override
    public AppInfoEntity getCurAppInfo(String type) {
        return appInfoRedisDao.getAppInfo(type);
    }

    @Override
    public AppInfoEntity getEmpAppInfo(String type) {
        return this.appInfoRedisDao.getOfficeAppInfo(type);
    }

}
