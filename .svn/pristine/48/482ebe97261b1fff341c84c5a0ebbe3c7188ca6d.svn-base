package com.kfwy.hkp.crm.customer.business.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.common.utils.ListUtils;
import com.kfwy.hkp.crm.customer.business.ICustomerAreaManager;
import com.kfwy.hkp.crm.customer.dao.ICustomerAreaDbDao;
import com.kfwy.hkp.crm.customer.entity.CustomerAreaEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zjp on 2018/8/7.
 */
@Service
public class CustomerAreaManagerImpl extends AbstractManager<CustomerAreaEntity> implements ICustomerAreaManager {

    @Autowired
    private ICustomerAreaDbDao customerAreaDbDao;

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.customerAreaDbDao;
    }

    @Override
    public int create(CustomerAreaEntity customerAreaEntity) {
        OperateFillUtils.fill(customerAreaEntity);
        return customerAreaDbDao.insertEntity(customerAreaEntity);
    }

    @Override
    public CustomerAreaEntity findOne(Map<String,Object> map) {
        CustomerAreaEntity customerAreaEntity = customerAreaDbDao.selectUniqueByMap(map);
        return customerAreaEntity;
    }

    @Override
    @Transactional
    public void update(List<CustomerAreaEntity> customerAreaEntityList, String cusCode) {
        List<Long> ids = null; // 不删除的id集合
        if(CollectionUtil.isNotEmpty(customerAreaEntityList)){
            ids = new ArrayList<>();
            for(CustomerAreaEntity customerAreaEntity : customerAreaEntityList){
                if(customerAreaEntity.getId() == null){
                    // 保存新区域
                    if (StringUtils.isEmpty(customerAreaEntity.getCusCode())) {
                        customerAreaEntity.setCusCode(cusCode);
                    }
                    this.create(customerAreaEntity);
                    ids.add(customerAreaEntity.getId());
                }else {
                    ids.add(customerAreaEntity.getId());
                }
            }
            // 删除ownerCode内 不包含的id
            Map<String,Object> map = new HashMap<>();
            map.put("notIds",ids);
            map.put("cusCode",cusCode);
            customerAreaDbDao.deleteAreaByCusCode(map);
        }
    }

    @Override
    public int batchInsert(List<CustomerAreaEntity> customerAreaEntityList) {
        if (ListUtils.isEmpty(customerAreaEntityList)) {
            return 0;
        }
        int count = 0;
        for (CustomerAreaEntity customerAreaEntity : customerAreaEntityList) {
            count += this.create(customerAreaEntity);
        }
        return count;
    }

    @Override
    public int deleteByCusCode (String cusCode) {
        Map map = new HashMap ();
        map.put ("cusCode",cusCode);
        return this.customerAreaDbDao.deleteAreaByCusCode (map);
    }
}
