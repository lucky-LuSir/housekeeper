package com.kfwy.hkp.crm.customer.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.crm.customer.dao.ICustomerContactsDbDao;
import com.kfwy.hkp.crm.customer.entity.CustomerContactsEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by zjp on 2018/8/8.
 */
@Repository
public class CustomerContactsDbDaoImpl extends AbstractMyBatisDao<CustomerContactsEntity,Long> implements ICustomerContactsDbDao {

    @Override
    public int insertEntity(CustomerContactsEntity customerContactsEntity) {
        return this.getSqlSession().insert(this.mapperNamespace + "insertEntity", customerContactsEntity);
    }

    @Override
    public int deleteContactsByCusCode(Map<String, Object> map) {
        return this.getSqlSession().delete(this.mapperNamespace + "deleteContactsByCusCode", map);
    }
}
