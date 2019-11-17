package com.kfwy.hkp.controller.trade.order.vo;

import com.gniuu.framework.service.AbstractServiceRequest;
import com.kfwy.hkp.trade.order.entity.OrderInvoiceEntity;

/**
 * @Author:ChenJie Created by kfwy_it_009 on 2018/11/9.
 */
public class OrderInvoiceServiceRequest extends AbstractServiceRequest<OrderInvoiceEntity> {
    private String fileCode;

    public String getFileCode() {
        return fileCode;
    }

    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
    }
}
