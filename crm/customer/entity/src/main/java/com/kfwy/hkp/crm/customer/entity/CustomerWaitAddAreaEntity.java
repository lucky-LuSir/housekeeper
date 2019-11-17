package com.kfwy.hkp.crm.customer.entity;


import com.gniuu.framework.entity.BaseEntity;

/**
 * 客户待新增需求区域实体类
 */
public class CustomerWaitAddAreaEntity extends BaseEntity {


    private String cusPhone;
    /**
     * 省份
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 区域
     */
    private String region;
    /**
     * 街道
     */
    private String street;
    /**
     * 社区
     */
    private String community;

    /**
     * 省名字
     */
    private String provinceName;
    /**
     * 城市名字
     */
    private String cityName;
    /**
     * 区域名字
     */
    private String regionName;
    /**
     * 街道名字
     */
    private String streetName;
    /**
     * 社区名字
     */
    private String communityName;

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getCusPhone () {
        return cusPhone;
    }

    public void setCusPhone (String cusPhone) {
        this.cusPhone = cusPhone;
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

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
}
