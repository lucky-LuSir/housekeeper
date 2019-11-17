package com.kfwy.hkp.bi.count.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.bi.count.dao.IGraphDbDao;
import com.kfwy.hkp.bi.count.entity.GraphEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * Create By lzp on 2019/08/9
 */
@Repository
public class GraphDbDaoImpl extends AbstractMyBatisDao<GraphEntity,Long> implements IGraphDbDao {
    @Override
    public List<GraphEntity> selectEntrustByMap(Map<String, Object> param) {
        return this.getSqlSession().selectList(this.mapperNamespace + "selectEntrustByMap", param);
    }

    @Override
    public List<GraphEntity> selectBespeakByMap(Map<String, Object> param) {
        return this.getSqlSession().selectList(this.mapperNamespace + "selectBespeakByMap", param);
    }

    @Override
    public List<GraphEntity> selectEntrustAndBespeakCountByMap(Map<String, Object> param) {
        return this.getSqlSession().selectList(this.mapperNamespace + "selectEntrustAndBespeakCountByMap", param);
    }

}
