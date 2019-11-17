package com.kfwy.hkp.crm.customer.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.crm.customer.dao.ICustomerFavoriteDbDao;
import com.kfwy.hkp.crm.customer.entity.CustomerFavoriteEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zjp on 2018/8/8.
 */
@Repository
public class CustomerFavoriteDbDaoImpl extends AbstractMyBatisDao<CustomerFavoriteEntity,Long> implements ICustomerFavoriteDbDao {

    @Override
    public int cancelFavorite(Map param) {
        return this.getSqlSession().delete(this.mapperNamespace + "deleteFavoriteByCusCodeAndEmpCode", param);
    }

    @Override
    public List<CustomerFavoriteEntity> getCountGroupByUserCode() {
        return this.getSqlSession().selectList(this.mapperNamespace + "getCountGroupByUserCode");
    }
}
