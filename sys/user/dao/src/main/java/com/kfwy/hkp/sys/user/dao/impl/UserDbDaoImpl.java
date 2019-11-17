package com.kfwy.hkp.sys.user.dao.impl;

import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.user.dao.impl.AbstractBaseUserDbDao;
import com.kfwy.hkp.sys.user.dao.IUserDbDao;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by davidcun on 2018/5/16.
 */
@Repository
public class UserDbDaoImpl extends AbstractBaseUserDbDao<UserEntity> implements IUserDbDao {


    @Override
    public int updateIdentifyNumber(Map map) {
        return this.getSqlSession().update(this.mapperNamespace+"updateIdentifyNumber",map);
    }

    @Override
    public UserEntity selectUniqueByProp(Map map) {
        return this.getSqlSession().selectOne(this.mapperNamespace+"selectUniqueByProp",map);
    }

    @Override
    public PageResult<UserEntity> selectUserBasisInfoByMap(Map<String, Object> map, int start, int pageSize) {
        return this.selectByStatement("selectUserBasisInfoByMap",map,start,pageSize);
    }

    @Override
    public List<UserEntity> selectUserBasisInfoByMap(Map<String, Object> map) {
        return this.selectByStatement("selectUserBasisInfoByMap",map);

    }

    @Override
    public List<String> selectUserCodeByMap(Map<String, Object> map) {
        return this.getSqlSession().selectList(this.mapperNamespace+"selectUserCodeByMap",map);
    }

    @Override
    public List<UserEntity> selectUserByDeptCodes(Map<String, Object> map) {
        return this.getSqlSession().selectList(this.mapperNamespace+"selectUserByDeptCodes",map);
    }



    @Override
    public List<UserEntity> selectSimpleUserByMap(Map<String, Object> map) {
        return this.selectByStatement("selectSimpleUserByMap",map);
    }

    @Override
    public UserEntity getUserEntity(String userCode) {
        return this.getSqlSession().selectOne("getUserEntity",userCode);
    }

    @Override
    public UserEntity login(Map map) {
        return this.getSqlSession().selectOne(this.mapperNamespace+"selectUniqueByProp",map);
    }

    @Override
    public List<UserEntity> queryAllUser() {
        return this.getSqlSession().selectList(this.mapperNamespace+"queryAllUser");
    }

    @Override
    public List<UserEntity> findUsersByDeptArea(Map map) {
        return this.getSqlSession().selectList(this.mapperNamespace+"findUsersByDeptArea",map);
    }

    @Override
    public List<UserEntity> findUsersByNoticeDepts(Map<String, Object> map) {
        return this.getSqlSession().selectList(this.mapperNamespace+"findUsersByNoticeDepts",map);
    }

    @Override
    public List<String> excludeUserCodesByFocusCusCount (Map map) {
        return this.getSqlSession ().selectList (this.mapperNamespace+"excludeUserCodesByFocusCusCount",map);
    }


}
