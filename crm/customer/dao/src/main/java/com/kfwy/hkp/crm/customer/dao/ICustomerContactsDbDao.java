package com.kfwy.hkp.crm.customer.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.crm.customer.entity.CustomerContactsEntity;

import java.util.Map;

/**
 * Created by zjp on 2018/8/8.
 */
public interface ICustomerContactsDbDao extends IMyBatisBaseDao<CustomerContactsEntity,Long> {

    int insertEntity(CustomerContactsEntity customerContactsEntity);

    int deleteContactsByCusCode(Map<String, Object> map);
}
