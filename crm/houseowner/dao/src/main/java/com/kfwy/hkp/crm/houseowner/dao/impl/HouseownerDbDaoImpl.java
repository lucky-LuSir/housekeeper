package com.kfwy.hkp.crm.houseowner.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.crm.houseowner.dao.IHouseownerDbDao;
import com.kfwy.hkp.crm.houseowner.entity.HouseownerEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by zjp on 2018/7/31.
 */
@Repository
public class HouseownerDbDaoImpl extends AbstractMyBatisDao<HouseownerEntity,Long> implements IHouseownerDbDao {

    @Override
    public HouseownerEntity queryHouseownerByHouseCode(Map param) {
        return this.getSqlSession().selectOne(this.mapperNamespace + "queryHouseownerByHouseCode", param);
    }
}
