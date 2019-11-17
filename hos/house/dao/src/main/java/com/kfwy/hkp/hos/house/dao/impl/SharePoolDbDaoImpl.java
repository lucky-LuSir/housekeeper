package com.kfwy.hkp.hos.house.dao.impl;

import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.hos.house.dao.ISharePoolDbDao;
import com.kfwy.hkp.hos.house.entity.SharePoolEntity;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class SharePoolDbDaoImpl extends AbstractMyBatisDao<SharePoolEntity,Long> implements ISharePoolDbDao {

    @Override
    public int add(SharePoolEntity sharePoolEntity) {
        return this.getSqlSession().insert(this.mapperNamespace+"add",sharePoolEntity);
    }

    @Override
    public PageResult<SharePoolEntity> query(Map map) {
        return null;
    }

    @Override
    public List<SharePoolEntity> selectByMyDept(Map map) {
        return this.getSqlSession().selectList(this.mapperNamespace+"selectByMyDept",map);
    }

    @Override
    public List<SharePoolEntity> selectByShareCodeMap(HashMap<String, Object> map) {
        List<SharePoolEntity> spList = this.getSqlSession().selectList(this.mapperNamespace+"selectByShareCodeMap",map);
        return spList;
    }

}
