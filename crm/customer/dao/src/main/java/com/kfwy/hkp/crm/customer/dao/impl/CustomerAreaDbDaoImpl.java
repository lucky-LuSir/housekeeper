package com.kfwy.hkp.crm.customer.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.crm.customer.dao.ICustomerAreaDbDao;
import com.kfwy.hkp.crm.customer.entity.CustomerAreaEntity;
import com.kfwy.hkp.crm.customer.entity.CustomerWaitAddAreaEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public class CustomerAreaDbDaoImpl extends AbstractMyBatisDao<CustomerAreaEntity,Long> implements ICustomerAreaDbDao {

    @Override
    public int insertEntity(CustomerAreaEntity customerAreaEntity) {
        return this.getSqlSession().insert(this.mapperNamespace + "insertEntity", customerAreaEntity);
    }

    @Override
    public int deleteAreaByCusCode(Map<String, Object> map) {
        return this.getSqlSession().delete(this.mapperNamespace + "deleteAreaByCusCode", map);
    }

    @Override
    public int cusWaitAddArea (List<CustomerWaitAddAreaEntity> customerWaitAddAreaEntitys) {
        return this.getSqlSession ().insert (this.mapperNamespace+"cusWaitAddArea",customerWaitAddAreaEntitys);
    }

    @Override
    public int deleteCusWaitAddArea (String cusPhone) {
        return this.getSqlSession ().delete (this.mapperNamespace+"deleteCusWaitAddArea",cusPhone);
    }


}
