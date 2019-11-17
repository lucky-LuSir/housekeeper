package com.kfwy.hkp.sys.user.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.sys.user.dao.IUserAreaDbDao;
import com.kfwy.hkp.sys.user.entity.UserAreaEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/12/20 10:52
 */
@Repository
public class UserAreaDbDaoImpl extends AbstractMyBatisDao<UserAreaEntity,Long> implements IUserAreaDbDao {

    @Override
    public void updateUserArea(List<UserAreaEntity> userAreaEntitys) {
        this.getSqlSession().update("updateUserArea",userAreaEntitys);
    }
}
