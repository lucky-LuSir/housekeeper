package com.kfwy.hkp.sys.user.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.sys.user.dao.IPortUserDbDao;
import com.kfwy.hkp.sys.user.entity.PortUserEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class PortUserDbDaoImpl extends AbstractMyBatisDao<PortUserEntity,Long> implements IPortUserDbDao {

    @Override
    public int deleteAll () {
        return this.getSqlSession ().delete (this.mapperNamespace+"deleteAll");
    }

    @Override
    public PortUserEntity findPortUserByLittlePhone (String caller) {
        return this.getSqlSession ().selectOne (this.mapperNamespace+"findPortUserByLittlePhone",caller);
    }
}
