package com.kfwy.hkp.sys.user.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.sys.user.business.IUserRoleManager;
import com.kfwy.hkp.sys.user.dao.IUserRoleDbDao;
import com.kfwy.hkp.sys.user.entity.UserRoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/6/29 15:54
 */
@Service
public class UserRoleManagerImpl extends AbstractManager<UserRoleEntity> implements IUserRoleManager {

    @Autowired
    private IUserRoleDbDao userRoleDbDao;

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.userRoleDbDao;
    }
}
