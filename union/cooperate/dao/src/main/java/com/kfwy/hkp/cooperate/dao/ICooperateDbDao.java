package com.kfwy.hkp.cooperate.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.cooperate.entity.CooperateEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by zjp on 2018/8/15.
 */
public interface ICooperateDbDao extends IMyBatisBaseDao<CooperateEntity,Long>{

    CooperateEntity detail(String cooCode);

    List<CooperateEntity> querySimpleCooperateListByMap(Map param);

}
