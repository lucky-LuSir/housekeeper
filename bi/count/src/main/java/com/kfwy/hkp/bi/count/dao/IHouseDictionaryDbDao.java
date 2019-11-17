package com.kfwy.hkp.bi.count.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.bi.count.entity.HouseDictionaryEntity;

import java.util.Map;

/**
 * Create By hjh on 2019/2/26
 */
public interface IHouseDictionaryDbDao extends IMyBatisBaseDao<HouseDictionaryEntity, Long> {

    /*
    *  查询位置(园区)
    * */
    public long selectLocationCount(Map<String, Object> param);

    /*
    *  查询房源
    * */
    public long selectHouseCount(Map<String, Object> param);

    /*
    *  查询面积
    * */
    public float selectHouseAreaSum(Map<String, Object> param);
}
