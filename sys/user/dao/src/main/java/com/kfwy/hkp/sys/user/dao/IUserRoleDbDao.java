package com.kfwy.hkp.sys.user.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.sys.user.entity.UserRoleEntity;

/**
 * Created by davidcun on 2018/5/22.
 */
public interface IUserRoleDbDao extends IMyBatisBaseDao<UserRoleEntity,Long>{
    /**
     *
     * @param userCode
     * @return
     */
    public int deleteByUserCode(String userCode);
}
