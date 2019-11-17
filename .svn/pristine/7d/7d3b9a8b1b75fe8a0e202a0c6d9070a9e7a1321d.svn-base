package com.kfwy.hkp.crm.customer.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.crm.customer.entity.CustomerPushEntity;

import java.util.List;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/10/25 17:25
 */
public interface ICustomerPushDbDao extends IMyBatisBaseDao<CustomerPushEntity,Long> {

    /**
     * 获取客户推送接收人与接收部门
     * @param cusCode
     * @return
     */
    List<CustomerPushEntity> getPushDeptOrUser(String cusCode);
}
