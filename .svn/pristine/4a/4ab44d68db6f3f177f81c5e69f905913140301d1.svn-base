package com.kfwy.hkp.trade.order.entity;


import com.gniuu.framework.dic.DictionaryStorage;
import com.kfwy.hkp.trade.order.enums.InvoiceApplyStatus;

/**
 * 订单开票申请实体类
 */
public class OrderInvoiceApplyEntity extends OrderInvoiceEntity {

    /**
     * @see com.kfwy.hkp.trade.order.enums.InvoiceApplyStatus
     */
    protected String applyStatus;

    protected String applyStatusName;

    public String getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }

    public String getApplyStatusName() {
        if (this.applyStatusName == null && null != this.applyStatus) {
            this.applyStatusName = DictionaryStorage.get(InvoiceApplyStatus.class.getName(), this.getApplyStatus()).getName();
        }
        return applyStatusName;
    }

    public void setApplyStatusName(String applyStatusName) {
        this.applyStatusName = applyStatusName;
    }
}
