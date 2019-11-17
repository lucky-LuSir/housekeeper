package com.kfwy.hkp.crm.customer.business.impl;


import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.crm.customer.business.ICustomerWaitAddManager;
import com.kfwy.hkp.crm.customer.dao.ICustomerAreaDbDao;
import com.kfwy.hkp.crm.customer.dao.ICustomerWaitAddDbDao;
import com.kfwy.hkp.crm.customer.entity.CustomerAreaEntity;
import com.kfwy.hkp.crm.customer.entity.CustomerWaitAddAreaEntity;
import com.kfwy.hkp.crm.customer.entity.CustomerWaitAddEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerWaitAddManagerImpl extends AbstractManager<CustomerWaitAddEntity> implements ICustomerWaitAddManager {

    @Autowired
    private ICustomerWaitAddDbDao customerWaitAddDbDao;
    @Autowired
    private ICustomerAreaDbDao customerAreaDbDao;

    @Override
    protected IMyBatisBaseDao<CustomerWaitAddEntity, Long> getMyBatisDao () {
        return this.customerWaitAddDbDao;
    }

    @Override
    public void afterCreate (CustomerWaitAddEntity customerWaitAddEntity) {
        List<CustomerWaitAddAreaEntity> customerAreaEntityList = customerWaitAddEntity.getCustomerAreaEntityList ();
        if (customerAreaEntityList != null && customerAreaEntityList.size () > 0) {

            customerAreaEntityList.forEach (customerWaitAddAreaEntity -> customerWaitAddAreaEntity.setCusPhone (customerWaitAddEntity.getCusPhone ()));
            customerAreaDbDao.cusWaitAddArea (customerAreaEntityList);
        }
    }

    @Override
    public void beforeCreate (CustomerWaitAddEntity customerWaitAddEntity) {
        if (customerWaitAddEntity.getCusPhone () == null || customerWaitAddEntity.getCusPhone () == "") {
            throw new RemoveStackBusinessException ("待新增客户电话号码不能为空！");
        }
        CustomerWaitAddEntity entity = customerWaitAddDbDao.detail (customerWaitAddEntity.getCusPhone ());
        if (entity != null) {
            throw new RemoveStackBusinessException ("该客户已经存在于待新增客户表中！");
        }
    }

    @Override
    public CustomerWaitAddEntity detail (String cusPhone) {
        return customerWaitAddDbDao.detail (cusPhone);
    }

    @Override
    @Transactional
    public int deleteCusWaitAdd (String cusPhone) {
        try {
            customerAreaDbDao.deleteCusWaitAddArea (cusPhone);
            customerWaitAddDbDao.deleteCusWaitAdd (cusPhone);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
}
