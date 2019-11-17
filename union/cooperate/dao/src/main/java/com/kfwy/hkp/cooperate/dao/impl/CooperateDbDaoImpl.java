package com.kfwy.hkp.cooperate.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.cooperate.dao.ICooperateDbDao;
import com.kfwy.hkp.cooperate.entity.CooperateEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * Created by zjp on 2018/8/15.
 */
@Repository
public class CooperateDbDaoImpl extends AbstractMyBatisDao<CooperateEntity,Long> implements ICooperateDbDao {

    @Override
    public CooperateEntity detail(String cooCode) {
        return this.getSqlSession().selectOne(this.mapperNamespace + "detail", cooCode);
    }

    @Override
    public List<CooperateEntity> querySimpleCooperateListByMap(Map param) {
        return this.getSqlSession().selectList(this.mapperNamespace + "querySimpleCooperateListByMap", param);
    }
}
