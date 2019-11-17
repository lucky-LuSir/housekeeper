package com.kfwy.hkp.hos.house.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.hos.house.dto.HousePriceDto;
import com.kfwy.hkp.hos.house.entity.HouseMapEntity;
import com.kfwy.hkp.hos.house.entity.HousePriceEntity;

import java.util.List;
import java.util.Map;

/**
 * Create By hjh on 2018/12/1
 */
public interface IHouseMapDbDao extends IMyBatisBaseDao<HouseMapEntity,Long> {

    public List<HouseMapEntity> count(Map<String, Object> map);

    public List<HouseMapEntity> pointCount(Map<String, Object> map);

    public List<HousePriceDto> monthPrice(Map<String, Object> map);

    public HousePriceEntity avgPriceByMonth(Map<String, Object> map);

    public List<HouseMapEntity> togetherCount(Map<String, Object> param);
}
