package com.kfwy.hkp.controller.quit.vo;

import com.kfwy.hkp.quit.entity.BackupEntity;

public class TransferEntityVo extends BackupEntity {

    // 省编码
    protected String province;
    // 省名
    protected String provinceName;
    // 城市编码
    protected String city;
    // 城市名称
    protected String cityName;
    // 区域编码
    protected String region;
    // 区域名称
    protected String regionName;
    // 街道编码
    protected String street;
    // 街道名称
    protected String streetName;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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
