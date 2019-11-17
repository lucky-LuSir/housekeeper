package com.kfwy.hkp.controller.payment.vo;

import com.gniuu.framework.service.AbstractServiceRequest;
import com.kfwy.hkp.payment.entity.PaymentEntity;

/**
 * Created by davidcun on 2018/5/22.
 */
public class PaymentServiceRequest extends AbstractServiceRequest<PaymentEntity> {

    private String keyword;

    private String payType;

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
