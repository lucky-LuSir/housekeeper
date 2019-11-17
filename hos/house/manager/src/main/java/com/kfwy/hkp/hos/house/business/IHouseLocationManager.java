package com.kfwy.hkp.hos.house.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.hkp.hos.house.entity.HouseLocPolygonEntity;
import com.kfwy.hkp.hos.house.entity.HouseLocRegionEntity;
import com.kfwy.hkp.hos.house.entity.HouseLocationEntity;

import java.util.List;
import java.util.Map;

/**
 * Create By hjh on 2018/7/31
 */
public interface IHouseLocationManager extends IManager<HouseLocationEntity> {
    int createLocRegion(HouseLocationEntity hLoc);

    List<HouseLocPolygonEntity> findSeeArea(HouseLocRegionEntity leftDown, HouseLocRegionEntity rightUp, String locationCode);

    List<HouseLocationEntity> findMapLoc(Map<String,Object> map);

    boolean findRepeatRegion(HouseLocRegionEntity leftDown, HouseLocRegionEntity rightUp, HouseLocationEntity locationEntity);

    int createLoc(HouseLocRegionEntity leftDown, HouseLocRegionEntity rightUp, HouseLocationEntity locationEntity);

    List<HouseLocationEntity> queryLocByHos(Map<String,Object> param);

    void editPointAndRegion(HouseLocRegionEntity leftDown, HouseLocRegionEntity rightUp, HouseLocationEntity locationEntity);
}
