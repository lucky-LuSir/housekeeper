package com.kfwy.hkp.crm.houseowner.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.crm.houseowner.entity.HouseownerEntity;

import java.util.Map;

/**
 * Created by zjp on 2018/7/31.
 */
public interface IHouseownerDbDao extends IMyBatisBaseDao<HouseownerEntity,Long> {

    HouseownerEntity queryHouseownerByHouseCode(Map param);

}
