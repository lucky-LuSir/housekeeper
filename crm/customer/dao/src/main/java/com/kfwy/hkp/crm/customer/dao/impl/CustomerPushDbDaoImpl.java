package com.kfwy.hkp.crm.customer.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.crm.customer.dao.ICustomerPushDbDao;
import com.kfwy.hkp.crm.customer.entity.CustomerPushEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/10/25 17:26
 */
@Repository
public class CustomerPushDbDaoImpl extends AbstractMyBatisDao<CustomerPushEntity,Long> implements ICustomerPushDbDao {

    @Override
    public List<CustomerPushEntity> getPushDeptOrUser(String cusCode) {
        return this.getSqlSession().selectList(this.mapperNamespace+"getPushDeptOrUser",cusCode);
    }
}
