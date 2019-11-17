package com.kfwy.hkp.hos.house.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.hkp.hos.house.entity.HouseEntity;
import com.kfwy.hkp.hos.house.entity.HouseFollowupEntity;

/**
 * Create By hjh on 2018/10/11
 */
public interface IHouseFollowupManager extends IManager<HouseFollowupEntity> {
    public int createHouseFollowupNotLastFollowupTime(HouseFollowupEntity houseFollowupEntity);
}
