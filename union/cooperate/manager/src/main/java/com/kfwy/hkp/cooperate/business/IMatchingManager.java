package com.kfwy.hkp.cooperate.business;

import com.gniuu.framework.dataaccess.PageResult;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.hos.house.entity.HouseEntity;

public interface IMatchingManager {

    /**
     * 房源智能匹配客户
     * @return
     */
    PageResult<CustomerEntity> matchCus(String houseCode,int start,int pageSize,String orderBy,boolean isAse);

    /**
     * 客户智能匹配房源
     * @return
     */
    PageResult<HouseEntity> matchHos(String cusCode,int start,int pageSize,String orderBy,boolean isAse);
}
