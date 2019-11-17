package com.kfwy.hkp.controller.crm.customer.vo;

import com.gniuu.framework.service.AbstractServiceRequest;
import com.kfwy.hkp.crm.customer.entity.CustomerPushEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/10/25 17:37
 */
public class CustomerPushServiceRequest extends AbstractServiceRequest<CustomerPushVo> {

    private List<CustomerPushEntity> pushEntities;


    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<CustomerPushEntity> getPushEntities() {
        return pushEntities;
    }

    public void setPushEntities(List<CustomerPushEntity> pushEntities) {
        this.pushEntities = pushEntities;
    }
}