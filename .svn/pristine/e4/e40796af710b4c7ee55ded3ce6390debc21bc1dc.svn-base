package com.kfwy.hkp.cooperate.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.cooperate.dao.ApplyEvaluateDao;
import com.kfwy.hkp.cooperate.entity.EvaluateEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户合作评价
 */
@Repository
public class ApplyEvaluateDaoImpl extends AbstractMyBatisDao<EvaluateEntity,Long> implements ApplyEvaluateDao {
    @Override
    public int evaluvteInsert(EvaluateEntity evaluateEntity) {
        return this.getSqlSession().insert(this.mapperNamespace+"evaluvteInsert",evaluateEntity);
    }

}
