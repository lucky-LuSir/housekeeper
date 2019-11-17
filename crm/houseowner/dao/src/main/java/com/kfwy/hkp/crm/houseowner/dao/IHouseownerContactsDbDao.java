package com.kfwy.hkp.crm.houseowner.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.crm.houseowner.entity.HouseownerContactsEntity;

import java.util.Map;

/**
 * Created by zjp on 2018/8/1.
 */
public interface IHouseownerContactsDbDao extends IMyBatisBaseDao<HouseownerContactsEntity,Long> {

    int insertEntity(HouseownerContactsEntity houseownerContactsEntity);

    int deleteContactsByHouseCode(Map param);

}
