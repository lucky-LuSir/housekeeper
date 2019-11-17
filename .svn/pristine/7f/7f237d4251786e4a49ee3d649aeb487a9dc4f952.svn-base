package com.kfwy.hkp.crm.customer.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.crm.customer.dao.ICustomerWaitAddDbDao;
import com.kfwy.hkp.crm.customer.entity.CustomerWaitAddEntity;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerWaitAddDbDaoImpl extends AbstractMyBatisDao<CustomerWaitAddEntity,Long> implements ICustomerWaitAddDbDao {
    @Override
    public CustomerWaitAddEntity detail (String cusPhone) {
        return this.getSqlSession().selectOne(this.mapperNamespace + "detail", cusPhone);
    }

    @Override
    public int deleteCusWaitAdd (String cusPhone) {
        return this.getSqlSession ().delete (this.mapperNamespace+"deleteCusWaitAdd",cusPhone);
    }


}
