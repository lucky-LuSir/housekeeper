package com.kfwy.hkp.hos.house.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.hos.house.dao.IHouseLocRegionDbDao;
import com.kfwy.hkp.hos.house.dao.IHouseLocationDbDao;
import com.kfwy.hkp.hos.house.entity.HouseLocRegionEntity;
import com.kfwy.hkp.hos.house.entity.HouseLocationEntity;
import org.springframework.stereotype.Repository;

/**
 * Create By hjh on 2018/7/31
 */
@Repository
public class HouseLocRegionDbDaoImpl extends AbstractMyBatisDao<HouseLocRegionEntity,Long> implements IHouseLocRegionDbDao {

    @Override
    public int deleteByLocationCode(String locationCode) {
        return this.getSqlSession().delete(this.mapperNamespace+"deleteByLocationCode",locationCode);
    }
}
