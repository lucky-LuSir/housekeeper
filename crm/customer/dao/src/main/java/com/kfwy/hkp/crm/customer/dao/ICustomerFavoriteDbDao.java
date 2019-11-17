package com.kfwy.hkp.crm.customer.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.crm.customer.entity.CustomerFavoriteEntity;

import java.util.List;
import java.util.Map;


/**
 * Created by zjp on 2018/8/8.
 */
public interface ICustomerFavoriteDbDao extends IMyBatisBaseDao<CustomerFavoriteEntity,Long> {

    int cancelFavorite(Map param);

    //根据所属者code分组 并查出昨天的客户收藏数
    List<CustomerFavoriteEntity> getCountGroupByUserCode();
}
