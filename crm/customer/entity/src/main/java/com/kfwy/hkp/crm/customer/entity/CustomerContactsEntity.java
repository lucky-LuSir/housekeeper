package com.kfwy.hkp.crm.customer.entity;

import com.gniuu.framework.entity.BaseEntity;

/**
 * 客户联系方式实体类
 */
public class CustomerContactsEntity extends BaseEntity {

    /**
     * 联系人编码
     */
    private String contactCode;

    /**
     * 联系人姓名
     */
    private String contactName;

    /**
     * 联系人手机号
     */
    private String contactPhone;

    /**
     * 联系人性别
     */
    private String contactSex;

    /**
     * 客户编码
     */
    private String cusCode;

    public String getContactCode() {
        return contactCode;
    }

    public void setContactCode(String contactCode) {
        this.contactCode = contactCode;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactSex() {
        return contactSex;
    }

    public void setContactSex(String contactSex) {
        this.contactSex = contactSex;
    }

    public String getCusCode() {
        return cusCode;
    }

    public void setCusCode(String cusCode) {
        this.cusCode = cusCode;
    }
}
