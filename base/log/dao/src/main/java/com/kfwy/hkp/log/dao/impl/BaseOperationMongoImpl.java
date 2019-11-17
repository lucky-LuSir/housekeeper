package com.kfwy.hkp.log.dao.impl;

import com.kfwy.hkp.common.mongo.dao.impl.BaseDaoImpl;
import com.kfwy.hkp.log.dao.IBaseOperationMongoDao;
import com.kfwy.hkp.log.entity.BaseOperationEntity;
import org.springframework.stereotype.Repository;

/**
 * 基础操作日志表(BaseOperation)dao层实现类
 *
 * @author liwensihan
 * @since 2019-05-28 10:48:30
 */
@Repository("baseOperationMongoDao")
public class BaseOperationMongoImpl extends BaseDaoImpl<BaseOperationEntity> implements IBaseOperationMongoDao {


    @Override
    protected Class<BaseOperationEntity> getEntityClass() {
        return BaseOperationEntity.class;
    }
}