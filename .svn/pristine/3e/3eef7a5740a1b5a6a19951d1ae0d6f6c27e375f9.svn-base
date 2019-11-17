package com.kfwy.hkp.hos.house.business;

import com.gniuu.framework.common.business.IManager;
import com.gniuu.framework.dataaccess.PageResult;
import com.kfwy.hkp.hos.house.dto.HousePriceDto;
import com.kfwy.hkp.hos.house.entity.HouseMapEntity;
import com.kfwy.hkp.hos.house.entity.HousePriceEntity;
import com.kfwy.hkp.hos.house.entity.WarehouseEntity;

import java.util.List;
import java.util.Map;

/**
 * Create By hjh on 2018/12/3
 */
public interface IHouseMapManager extends IManager<HouseMapEntity> {

    public List<HouseMapEntity> count(Map<String, Object> map);

    public PageResult<WarehouseEntity> query(Map<String, Object> map, int start, int pageSize);

    public List<HouseMapEntity> pointCount(Map<String, Object> map);

    public List<HousePriceDto> monthPrice(Map<String, Object> map);

    public HousePriceEntity avgPriceByMonth(Map<String, Object> map);

    public List<HouseMapEntity> togetherCount(Map<String, Object> param);
}
