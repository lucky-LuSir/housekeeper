package com.kfwy.hkp.crm.customer.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.crm.customer.dao.ICustomerApplyDbDao;
import com.kfwy.hkp.crm.customer.entity.CustomerApplyEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Create By hjh on 2018/8/10
 */
@Repository
public class CustomerApplyDbDaoImpl extends AbstractMyBatisDao<CustomerApplyEntity,Long> implements ICustomerApplyDbDao {

    @Override
    public int toDayByCount(Map<String, Object> param) {

        return ((Integer)this.getSqlSession().selectOne(this.mapperNamespace + "toDayByCount", param)).intValue();
    }
}
