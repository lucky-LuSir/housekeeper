package com.kfwy.hkp.quit.entity;


import com.gniuu.framework.entity.BaseEntity;

import java.util.List;

/**
 * 工具箱  转移实体类
 */
public class SpecialEntity extends BaseEntity {

    /**
     * 转移人编码
     */
    private String hisEmpCode;
    /**
     * 转移人姓名
     */
    private String hisEmpName;
    /**
     * 接收人编号
     */
    private String newEmpCode;
    /**
     * 接收人姓名
     */
    private String newEmpName;

    //省市区街道
    private String province;
    private String city;
    private String region;
    private String street;

    /**
     * 客户编码
     */
    private List<String> cusCodes;

    /**
     * 房源编码
     */
    private List<String> hosCodes;

    /**
     * 业主编码
     */
    private List<String> ownCodes;


    /**
     * 兼职编码
     */
    private List<String> userCodes;

    /**
     * 更新编码
     */
    private String updateCode;

    public String getUpdateCode() {
        return updateCode;
    }

    public void setUpdateCode(String updateCode) {
        this.updateCode = updateCode;
    }

    public String getHisEmpCode() {
        return hisEmpCode;
    }

    public void setHisEmpCode(String hisEmpCode) {
        this.hisEmpCode = hisEmpCode;
    }

    public String getHisEmpName() {
        return hisEmpName;
    }

    public void setHisEmpName(String hisEmpName) {
        this.hisEmpName = hisEmpName;
    }

    public String getNewEmpCode() {
        return newEmpCode;
    }

    public void setNewEmpCode(String newEmpCode) {
        this.newEmpCode = newEmpCode;
    }

    public String getNewEmpName() {
        return newEmpName;
    }

    public void setNewEmpName(String newEmpName) {
        this.newEmpName = newEmpName;
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

    public List<String> getCusCodes() {
        return cusCodes;
    }

    public void setCusCodes(List<String> cusCodes) {
        this.cusCodes = cusCodes;
    }

    public List<String> getHosCodes() {
        return hosCodes;
    }

    public void setHosCodes(List<String> hosCodes) {
        this.hosCodes = hosCodes;
    }

    public List<String> getOwnCodes() {
        return ownCodes;
    }

    public void setOwnCodes(List<String> ownCodes) {
        this.ownCodes = ownCodes;
    }

    public List<String> getUserCodes() {
        return userCodes;
    }

    public void setUserCodes(List<String> userCodes) {
        this.userCodes = userCodes;
    }
}
