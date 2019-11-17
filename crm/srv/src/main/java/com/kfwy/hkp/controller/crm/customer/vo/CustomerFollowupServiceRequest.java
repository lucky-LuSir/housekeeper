package com.kfwy.hkp.controller.crm.customer.vo;

import com.gniuu.framework.service.AbstractServiceRequest;

/**
 * Created by hjh on 2018/8/23.
 */
public class CustomerFollowupServiceRequest extends AbstractServiceRequest<CustomerFollowupVo> {


    @Deprecated
    private CustomerFollowupVo customerFollowupVo;

    public CustomerFollowupVo getCustomerFollowupVo() {
        return customerFollowupVo;
    }

    public void setCustomerFollowupVo(CustomerFollowupVo customerFollowupVo) {
        this.customerFollowupVo = customerFollowupVo;
    }
}
