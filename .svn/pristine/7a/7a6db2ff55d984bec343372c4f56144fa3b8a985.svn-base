package com.kfwy.hkp.hos.house.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.hkp.hos.house.entity.HouseFavoriteEntity;

import java.util.List;
import java.util.Map;

/**
 * Create By hjh on 2018/8/8
 */
public interface IHouseFavoriteManager extends IManager<HouseFavoriteEntity> {

    public void delete(Map<String, Object> param);

    //获取昨天的房源收藏总数 按所属者code分组
    List<HouseFavoriteEntity> getCountGroupByUserCode();
}
