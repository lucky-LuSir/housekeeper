package com.kfwy.hkp.hos.house.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.hos.house.dao.IHouseApplyDbDao;
import com.kfwy.hkp.hos.house.entity.HouseApplyEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Create By hjh on 2018/8/10
 */
@Repository
public class HouseApplyDbDaoImpl extends AbstractMyBatisDao<HouseApplyEntity,Long> implements IHouseApplyDbDao {

    @Override
    public int toDayByCount(Map<String, Object> param) {
        return ((Integer)this.getSqlSession().selectOne(this.mapperNamespace + "toDayByCount", param)).intValue();
    }
}
