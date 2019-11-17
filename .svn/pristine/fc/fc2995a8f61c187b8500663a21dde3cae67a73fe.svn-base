package com.kfwy.hkp.crm.customer.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.crm.customer.business.ICustomerHouseManager;
import com.kfwy.hkp.crm.customer.dao.ICustomerHouseDbDao;
import com.kfwy.hkp.crm.customer.entity.CustomerHouseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:ChenJie Created by kfwy_it_009 on 2019/2/28.
 */
@Service
public class CustomerHouseManagerImpl extends AbstractManager<CustomerHouseEntity> implements ICustomerHouseManager {
    @Autowired
    private ICustomerHouseDbDao customerHouseDbDao;
    @Override
    public List<CustomerHouseEntity> getCustomerHouseEntity(String followupCode) {
        String preSql = "SELECT * FROM t_hkp_crm_followup_house" +
                " WHERE followup_code=\'%s\'";
        String sql = String.format(preSql, followupCode);
        List<CustomerHouseEntity> customerHouseEntity = customerHouseDbDao.selectByNativeSql(sql);
        return customerHouseEntity;
    }

    @Override
    protected IMyBatisBaseDao<CustomerHouseEntity, Long> getMyBatisDao() {
        return null;
    }
}
