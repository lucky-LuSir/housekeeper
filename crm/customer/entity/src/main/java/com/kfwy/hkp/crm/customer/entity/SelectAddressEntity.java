package com.kfwy.hkp.crm.customer.entity;

import com.gniuu.framework.entity.BaseEntity;

import java.util.List;

/**
 * @Description TODO
 * @Auth xpp
 * @Date 2018/11/26
 * @Version 1.0
 */
public class SelectAddressEntity extends BaseEntity {

    // 选址唯一编码
    private String selectAddressCode;
    // 客户编码
    private String cusCode;
    // 客户姓名
    private String cusName;
    // 发选址人手机
    private String ownerUserPhone;
    // 跳转的url
    private String addressUrl;

    //发选址人用户头像
    private String userImg;
    // 选址房源(子表)
    private List<SelectHouseEntity> selectHouseList ;


    //------------------字段end--------------------

    public String getSelectAddressCode() {
        return selectAddressCode;
    }

    public void setSelectAddressCode(String selectAddressCode) {
        this.selectAddressCode = selectAddressCode;
    }

    public String getCusCode() {
        return cusCode;
    }

    public void setCusCode(String cusCode) {
        this.cusCode = cusCode;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getOwnerUserPhone() {
        return ownerUserPhone;
    }

    public void setOwnerUserPhone(String ownerUserPhone) {
        this.ownerUserPhone = ownerUserPhone;
    }

    public String getAddressUrl() {
        return addressUrl;
    }

    public void setAddressUrl(String addressUrl) {
        this.addressUrl = addressUrl;
    }

    public List<SelectHouseEntity> getSelectHouseList() {
        return selectHouseList;
    }

    public void setSelectHouseList(List<SelectHouseEntity> selectHouseList) {
        this.selectHouseList = selectHouseList;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }
}
