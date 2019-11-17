package com.kfwy.hkp.crm.customer.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.hkp.crm.customer.entity.SelectAddressEntity;

import java.util.List;

/**
* @Description 描述:
* @Auth xpp
* @Date 2018/11/27 10:46
* @Version 1.0
* @Param
* @return
**/
public interface ISelectAddressManager extends IManager<SelectAddressEntity> {


   public SelectAddressEntity createAddress(SelectAddressEntity saEntity);

   public SelectAddressEntity createNewAddress(SelectAddressEntity saEntity);

    public SelectAddressEntity selectHos(SelectAddressEntity entity);

    public SelectAddressEntity mbwebquery(SelectAddressEntity entity);

    public List<SelectAddressEntity> query(String cusCode);
}
