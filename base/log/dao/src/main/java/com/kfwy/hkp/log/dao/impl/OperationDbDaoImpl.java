package com.kfwy.hkp.log.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.log.dao.IOperationDbDao;
import com.kfwy.hkp.log.entity.OperationEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2019/1/19 14:03
 */
@Repository
public class OperationDbDaoImpl extends AbstractMyBatisDao<OperationEntity,Long> implements IOperationDbDao {
    @Override
    public List<String> getCallPhonePeopleCount (Map map) {
        return this.getSqlSession ().selectList (this.mapperNamespace+"getCallPhonePeopleCount",map);
    }

    @Override
    public OperationEntity getLastCallCusPhoneLog (Map map) {
        return this.getSqlSession ().selectOne (this.mapperNamespace+"getLastCallCusPhoneLog",map);
    }

    @Override
    public String callPhoneIsHandle (Map map) {
        return this.getSqlSession ().selectOne (this.mapperNamespace+"callPhoneIsHandle",map);
    }

    @Override
    public int updateCallPhoneHandle (Map map) {
        return this.getSqlSession ().update (this.mapperNamespace+"updateCallPhoneHandle",map);
    }
}
