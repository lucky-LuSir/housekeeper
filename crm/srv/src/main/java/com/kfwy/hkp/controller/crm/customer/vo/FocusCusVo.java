package com.kfwy.hkp.controller.crm.customer.vo;

import com.kfwy.hkp.crm.customer.dto.CustomerFocusCusDto;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.crm.customer.entity.FocusCusEntity;


/**
 * 集中获客列表vo
 */
public class FocusCusVo extends CustomerFocusCusDto {
    protected String userName;

    public String getUserName () {
        return userName;
    }

    public void setUserName (String userName) {
        this.userName = userName;
    }
}
