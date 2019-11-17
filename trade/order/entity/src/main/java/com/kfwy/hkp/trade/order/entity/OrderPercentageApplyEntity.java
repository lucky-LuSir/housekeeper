package com.kfwy.hkp.trade.order.entity;

/**
 * 订单修改分成 申请表实体
 */
public class OrderPercentageApplyEntity extends OrderPercentageEntity {

    /**
     * 订单修改申请编码
     */
    protected String applyCode;

    public String getApplyCode() {
        return applyCode;
    }

    public void setApplyCode(String applyCode) {
        this.applyCode = applyCode;
    }
}
