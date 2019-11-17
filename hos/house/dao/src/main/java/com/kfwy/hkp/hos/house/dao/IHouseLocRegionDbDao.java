package com.kfwy.hkp.hos.house.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.hos.house.entity.HouseLocRegionEntity;
import com.kfwy.hkp.hos.house.entity.HouseLocationEntity;

/**
 * Create By hjh on 2018/7/31
 */
public interface IHouseLocRegionDbDao extends IMyBatisBaseDao<HouseLocRegionEntity,Long> {

    int deleteByLocationCode(String roleCode);
}
