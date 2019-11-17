package com.kfwy.hkp.hos.house.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.hos.house.dao.IHouseFavoriteDbDao;
import com.kfwy.hkp.hos.house.entity.HouseFavoriteEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Create By hjh on 2018/8/8
 */
@Repository
public class HouseFavoriteDbDaoImpl extends AbstractMyBatisDao<HouseFavoriteEntity,Long> implements IHouseFavoriteDbDao {

    @Override
    public void delete(Map<String, Object> param) {
        this.getSqlSession().delete(this.mapperNamespace+"deleteByHouseCode",param);
    }

    @Override
    public List<HouseFavoriteEntity> getCountGroupByUserCode() {
        return this.getSqlSession().selectList(this.mapperNamespace+"getCountGroupByUserCode");
    }
}
