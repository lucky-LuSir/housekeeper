package com.kfwy.hkp.crm.customer.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.hkp.crm.customer.entity.CustomerAreaEntity;

import java.util.List;

/**
 * Created by zjp on 2018/8/7.
 */
public interface ICustomerAreaManager extends IManager<CustomerAreaEntity> {

    /**
     * 更新客户的需求区域信息
     * @param customerAreaEntityList
     * @param cusCode
     */
    void update(List<CustomerAreaEntity> customerAreaEntityList, String cusCode);

    /**
     * 批量插入需求区域信息
     * @param customerAreaEntityList
     * @return
     */
    int batchInsert(List<CustomerAreaEntity> customerAreaEntityList);

    int deleteByCusCode(String cusCode);

}
