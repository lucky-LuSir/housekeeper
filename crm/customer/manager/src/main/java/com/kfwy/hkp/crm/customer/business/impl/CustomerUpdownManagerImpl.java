package com.kfwy.hkp.crm.customer.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.crm.customer.business.ICustomerUpdownMager;
import com.kfwy.hkp.crm.customer.dao.ICustomerUpdownDbDao;
import com.kfwy.hkp.crm.customer.entity.CustomerUpdownEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author:ChenJie Created by kfwy_it_009 on 2019/3/18.
 */
@Service
public class CustomerUpdownManagerImpl extends AbstractManager<CustomerUpdownEntity> implements ICustomerUpdownMager {
    @Autowired
    private ICustomerUpdownDbDao customerUpdownDbDao;

    @Override
    protected IMyBatisBaseDao<CustomerUpdownEntity, Long> getMyBatisDao() {
        return this.customerUpdownDbDao;
    }
}
