package com.kfwy.hkp.crm.customer.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.common.utils.ListUtils;
import com.kfwy.hkp.crm.customer.business.ICustomerFavoriteManager;
import com.kfwy.hkp.crm.customer.dao.ICustomerFavoriteDbDao;
import com.kfwy.hkp.crm.customer.entity.CustomerFavoriteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zjp on 2018/8/8.
 */
@Service
public class CustomerFavoriteManagerImpl extends AbstractManager<CustomerFavoriteEntity> implements ICustomerFavoriteManager {

    @Autowired
    private ICustomerFavoriteDbDao customerFavoriteDbDao;

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.customerFavoriteDbDao;
    }

    @Override
    public int cancelFavorite(Map param) {
        return customerFavoriteDbDao.cancelFavorite(param);
    }

    @Override
    public synchronized int create(CustomerFavoriteEntity customerFavoriteEntity){
        Map param = new HashMap<String, Object>();
        param.put("cusCode", customerFavoriteEntity.getCusCode());
        param.put("empCode", customerFavoriteEntity.getEmpCode());
        List<CustomerFavoriteEntity> list = this.findByMap(param);
        if (!ListUtils.isEmpty(list)) {
            throw new RemoveStackBusinessException ("已经收藏过该客户");
        }
        return customerFavoriteDbDao.insert(customerFavoriteEntity);
    }
}
