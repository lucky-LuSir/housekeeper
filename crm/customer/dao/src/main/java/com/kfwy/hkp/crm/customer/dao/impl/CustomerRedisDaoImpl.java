package com.kfwy.hkp.crm.customer.dao.impl;

import com.gniuu.framework.dataaccess.redis.AbstractRedisDao;
import com.kfwy.hkp.crm.customer.dao.ICustomerRedisDao;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import org.springframework.stereotype.Repository;


/**
 * 客户redis dao层实现
 */
@Repository
public class CustomerRedisDaoImpl extends AbstractRedisDao<CustomerEntity> implements ICustomerRedisDao<CustomerEntity> {

}
