package com.kfwy.hkp.crm.customer.dao;

import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.crm.customer.entity.CustomerFollowupEntity;

import java.util.Map;

/**
 * Create By hjh on 2018/8/23
 */
public interface ICustomerFollowupDbDao extends IMyBatisBaseDao<CustomerFollowupEntity,Long> {

    public int updateCoo(Map<String,Object> map);

    public int selectFollowupCount(Map<String,Object> map);

    public CustomerFollowupEntity detailSee(Map<String,Object> map);

    public PageResult<CustomerFollowupEntity> takeLookByCusEvaluate(Map map,Integer start,Integer pageSize);
}
