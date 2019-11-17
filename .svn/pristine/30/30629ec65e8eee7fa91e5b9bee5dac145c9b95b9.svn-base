package com.kfwy.hkp.crm.customer.business.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.crm.customer.business.ICustomerContactsManager;
import com.kfwy.hkp.crm.customer.dao.ICustomerContactsDbDao;
import com.kfwy.hkp.crm.customer.entity.CustomerContactsEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zjp on 2018/8/8.
 */
@Service
public class CustomerContactsManagerImpl extends AbstractManager<CustomerContactsEntity> implements ICustomerContactsManager {

    @Autowired
    private ICustomerContactsDbDao customerContactsDbDao;

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.customerContactsDbDao;
    }

    @Override
    public int create(CustomerContactsEntity customerContactsEntity) {
        OperateFillUtils.fill(customerContactsEntity);
        customerContactsEntity.setContactCode(CodeGenUtils.generate());
        return customerContactsDbDao.insertEntity(customerContactsEntity);
    }

    @Override
    public CustomerContactsEntity findOne(Map<String,Object> map) {
        CustomerContactsEntity customerContactsEntity = customerContactsDbDao.selectUniqueByMap(map);
        return customerContactsEntity;
    }

    @Override
    @Transactional
    public void update(List<CustomerContactsEntity> customerContactsEntityList, String cusCode) {
        List<Long> ids = null; // 不删除的id集合
        if(CollectionUtil.isNotEmpty(customerContactsEntityList)){
            ids = new ArrayList<>();
            for(CustomerContactsEntity customerContactsEntity : customerContactsEntityList){
                if(customerContactsEntity.getId() == null){
                    // 保存
                    if (StringUtils.isEmpty(customerContactsEntity.getCusCode())) {
                        customerContactsEntity.setCusCode(cusCode);
                    }
                    this.create(customerContactsEntity);
                    ids.add(customerContactsEntity.getId());
                }else {
                    ids.add(customerContactsEntity.getId());
                }
            }
        }
        // 删除ownerCode内 不包含的id
        Map<String,Object> map = new HashMap<>();
        map.put("notIds",ids);
        map.put("cusCode",cusCode);
        customerContactsDbDao.deleteContactsByCusCode(map);
    }

}
