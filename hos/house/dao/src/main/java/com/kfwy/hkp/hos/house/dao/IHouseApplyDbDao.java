package com.kfwy.hkp.hos.house.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.hos.house.entity.HouseApplyEntity;

import java.util.Map;

/**
 * Create By hjh on 2018/8/10
 */
public interface IHouseApplyDbDao extends IMyBatisBaseDao<HouseApplyEntity,Long>{


    int toDayByCount(Map<String, Object> param);
}
