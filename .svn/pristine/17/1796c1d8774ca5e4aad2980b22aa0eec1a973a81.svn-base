package com.kfwy.hkp.crm.customer.dao.impl;

import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.crm.customer.dao.ICustomerFollowupDbDao;
import com.kfwy.hkp.crm.customer.entity.CustomerFollowupEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Create By hjh on 2018/8/23
 */
@Repository
public class CustomerFollowupDbDaoImpl extends AbstractMyBatisDao<CustomerFollowupEntity,Long> implements ICustomerFollowupDbDao {
    @Override
    public int updateCoo(Map<String, Object> map) {

        return this.getSqlSession().update(this.mapperNamespace+"updateCoo",map);
    }

    @Override
    public int selectFollowupCount(Map<String, Object> map) {
        return this.getSqlSession().selectOne(this.mapperNamespace+"selectFollowupCount",map);
    }

    @Override
    public CustomerFollowupEntity detailSee(Map<String, Object> map) {
        return this.getSqlSession().selectOne(this.mapperNamespace+"detailSee",map);
    }

    @Override
    public PageResult<CustomerFollowupEntity> takeLookByCusEvaluate(Map map, Integer start, Integer pageSize) {
        return this.selectByStatement("takeLookByCusEvaluate",map,start,pageSize);
    }
}
