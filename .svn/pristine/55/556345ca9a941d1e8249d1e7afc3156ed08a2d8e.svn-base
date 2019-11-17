package com.kfwy.hkp.crm.houseowner.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.crm.houseowner.dao.IHouseownerContactsDbDao;
import com.kfwy.hkp.crm.houseowner.entity.HouseownerContactsEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by zjp on 2018/8/1.
 */
@Repository
public class HouseownerContactsDbDaoImpl extends AbstractMyBatisDao<HouseownerContactsEntity,Long> implements IHouseownerContactsDbDao {

    @Override
    public int insertEntity(HouseownerContactsEntity houseownerContactsEntity) {
        return this.getSqlSession().insert(this.mapperNamespace + "insertEntity", houseownerContactsEntity);
    }

    @Override
    public int deleteContactsByHouseCode(Map param) {
        return this.getSqlSession().delete(this.mapperNamespace + "deleteContactsByHouseCode", param);
    }
}
