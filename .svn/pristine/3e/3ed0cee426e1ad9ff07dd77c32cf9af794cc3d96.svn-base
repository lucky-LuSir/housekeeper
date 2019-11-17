package com.kfwy.hkp.sys.user.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.sys.user.dao.IUserRoleDbDao;
import com.kfwy.hkp.sys.user.entity.UserRoleEntity;
import org.springframework.stereotype.Repository;

/**
 *
 * @author liwensihan
 * @date 2018/11/7
 */
@Repository
public class UserRoleDbDaoImpl extends AbstractMyBatisDao<UserRoleEntity,Long> implements IUserRoleDbDao {
    @Override
    public int deleteByUserCode(String userCode) {
        return this.getSqlSession().delete(this.mapperNamespace+"deleteByUserCode",userCode);
    }
}
