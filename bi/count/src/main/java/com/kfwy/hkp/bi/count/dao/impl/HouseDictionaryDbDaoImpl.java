package com.kfwy.hkp.bi.count.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.bi.count.dao.IHouseDictionaryDbDao;
import com.kfwy.hkp.bi.count.entity.HouseDictionaryEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Create By hjh on 2019/2/26
 */
@Repository
public class HouseDictionaryDbDaoImpl extends AbstractMyBatisDao<HouseDictionaryEntity, Long> implements IHouseDictionaryDbDao {

    @Override
    public long selectLocationCount(Map<String, Object> param) {
        return this.getSqlSession().selectOne(this.mapperNamespace + "selectLocationCount", param);
    }

    @Override
    public long selectHouseCount(Map<String, Object> param) {
        return this.getSqlSession().selectOne(this.mapperNamespace + "selectHouseCount", param);
    }

    @Override
    public float selectHouseAreaSum(Map<String, Object> param) {
        return this.getSqlSession().selectOne(this.mapperNamespace + "selectHouseAreaSum", param);
    }
}
