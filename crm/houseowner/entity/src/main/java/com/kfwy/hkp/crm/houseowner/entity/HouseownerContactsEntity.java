package com.kfwy.hkp.crm.houseowner.entity;

import com.gniuu.framework.entity.BaseEntity;

/**
 * Created by zjp on 2018/8/1.
 */
public class HouseownerContactsEntity extends BaseEntity {

    /**
     * 联系人编码
     */
    protected String contactCode;

    /**
     * 联系人姓名
     */
    protected String contactName;

    /**
     * 联系人手机号
     */
    protected String contactPhone;

    /**
     * 联系人性别
     */
    protected String contactSex;

    /**
     * 业主编码（目前尚未关联业主）
     */
    protected String houseownerCode;

    /**
     * 房源编码
     */
    protected String houseCode;

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

    public String getHouseownerCode() {
        return houseownerCode;
    }

    public void setHouseownerCode(String houseownerCode) {
        this.houseownerCode = houseownerCode;
    }

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }
}
