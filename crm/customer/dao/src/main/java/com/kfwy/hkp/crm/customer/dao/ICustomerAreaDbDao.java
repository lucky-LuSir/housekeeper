package com.kfwy.hkp.crm.customer.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.crm.customer.entity.CustomerAreaEntity;
import com.kfwy.hkp.crm.customer.entity.CustomerWaitAddAreaEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by zjp on 2018/8/7.
 */
public interface ICustomerAreaDbDao extends IMyBatisBaseDao<CustomerAreaEntity,Long> {

    int insertEntity(CustomerAreaEntity customerAreaEntity);

    int deleteAreaByCusCode(Map<String, Object> map);

    int cusWaitAddArea(List<CustomerWaitAddAreaEntity> customerWaitAddAreaEntitys);

    int deleteCusWaitAddArea(String cusPhone);

}
