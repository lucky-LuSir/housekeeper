package com.kfwy.hkp.controller.sys.user.vo;

import com.gniuu.framework.service.AbstractServiceResponse;

/**
 * Created by zjp on 2018/8/7.
 */
public class AppUserServiceResponse extends AbstractServiceResponse {

    private String token;

    private String deviceBrand;

    private String cityCode;

    private String provinceCode;

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }


    public String getDeviceBrand() {
        return deviceBrand;
    }

    public void setDeviceBrand(String deviceBrand) {
        this.deviceBrand = deviceBrand;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
