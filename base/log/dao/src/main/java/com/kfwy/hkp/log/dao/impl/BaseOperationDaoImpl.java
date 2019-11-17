/*
package com.kfwy.hkp.log.dao.impl;

import com.kfwy.hkp.log.dao.BaseOperationDao;
import com.kfwy.hkp.log.entity.BaseOperationEntity;
import org.springframework.stereotype.Repository;
import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;

import java.util.List;

*/
/**
 * 基础操作日志表(BaseOperation)dao层实现类
 *
 * @author liwensihan
 * @since 2019-05-28 10:48:30
 *//*

@Repository("baseOperationDao")
public class BaseOperationDaoImpl extends AbstractMyBatisDao<BaseOperationEntity,Long> implements BaseOperationDao {

    @Override
    public List<String> findUrlsByOptCode(String optCode) {

        return this.getSqlSession().selectList(this.mapperNamespace+"findUrlsByOptCode",optCode);

    }
}*/
