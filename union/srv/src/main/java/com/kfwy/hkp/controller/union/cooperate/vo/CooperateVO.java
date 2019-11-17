package com.kfwy.hkp.controller.union.cooperate.vo;

import com.kfwy.hkp.cooperate.entity.CooperateEntity;

/**
 * Created by zjp on 2018/8/16.
 */
public class CooperateVO extends CooperateEntity {

    /**
     * 查询类型： 1  我申请的  2 我收到的
     */
    protected String queryType;

    /**
     * 客户电话
     */
    protected String cusPhone;
    /**
     * 业主电话
     */
    protected String houseownerPhone;
    /**
     * 省份
     */
    public String province;

    /**
     * 城市
     */
    public String city;

    /**
     * 区域
     */
    public String region;

    /**
     * 街道
     */
    public String street;
    /**
     * 房源地址
     */
    protected String detailAddress;
    /**
     * 房源标题
     */
    protected String houseName;
    /**
     * 合作人员姓名
     */
    protected String unionName;
    /**
     * 合作人员电话
     */
    protected String unionPhone;


    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public String getCusPhone() {
        return cusPhone;
    }

    public void setCusPhone(String cusPhone) {
        this.cusPhone = cusPhone;
    }

    public String getHouseownerPhone() {
        return houseownerPhone;
    }

    public void setHouseownerPhone(String houseownerPhone) {
        this.houseownerPhone = houseownerPhone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getUnionName() {
        return unionName;
    }

    public void setUnionName(String unionName) {
        this.unionName = unionName;
    }

    public String getUnionPhone() {
        return unionPhone;
    }

    public void setUnionPhone(String unionPhone) {
        this.unionPhone = unionPhone;
    }
}
