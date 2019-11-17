package com.kfwy.hkp.bi.count.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.bi.count.dao.ISupportDbDao;
import com.kfwy.hkp.bi.count.entity.SupportEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * Create By hjh on 2019/07/23
 */
@Repository
public class SupportDbDaoImpl extends AbstractMyBatisDao<SupportEntity,Long> implements ISupportDbDao {


    @Override
    public List<SupportEntity> selectSupport(Map<String, Object> param) {
        return this.getSqlSession().selectList(this.mapperNamespace + "selectSupport", param);
    }
}
