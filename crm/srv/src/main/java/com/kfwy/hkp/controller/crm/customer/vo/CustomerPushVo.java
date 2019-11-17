package com.kfwy.hkp.controller.crm.customer.vo;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/11/1 15:41
 */
public class CustomerPushVo extends CustomerVo {

    /**
     * 推送人姓名
     */
    private String pushName;

    /**
     * 是否公开
     */
    protected Boolean pushOpenFlag;

    /**
     * 客户编码
     */
    protected String cusCode;

    public Boolean getPushOpenFlag() {
        return pushOpenFlag;
    }

    public void setPushOpenFlag(Boolean pushOpenFlag) {
        this.pushOpenFlag = pushOpenFlag;
    }

    public String getPushName() {
        return pushName;
    }

    public void setPushName(String pushName) {
        this.pushName = pushName;
    }

    @Override
    public String getCusCode() {
        return cusCode;
    }

    @Override
    public void setCusCode(String cusCode) {
        this.cusCode = cusCode;
    }
}
