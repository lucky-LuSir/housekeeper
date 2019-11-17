package com.kfwy.hkp.cooperate.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.cooperate.entity.EvaluateEntity;

import java.util.List;

/**
 * 用户合作评价
 */
public interface ApplyEvaluateDao extends IMyBatisBaseDao<EvaluateEntity,Long> {

    /**
     * 用户合作评价新增
     * @param evaluateEntity
     * @return
     */
    int evaluvteInsert(EvaluateEntity evaluateEntity);

}
