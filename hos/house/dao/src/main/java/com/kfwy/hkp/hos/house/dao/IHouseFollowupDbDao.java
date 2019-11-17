package com.kfwy.hkp.hos.house.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.hos.house.entity.HouseFollowupEntity;

import java.util.Map;

/*
*   Create By hjh @Date 2018/10/11
* */
public interface IHouseFollowupDbDao extends IMyBatisBaseDao<HouseFollowupEntity,Long> {

    public int selectFollowupCount(Map<String,Object> map);

}
