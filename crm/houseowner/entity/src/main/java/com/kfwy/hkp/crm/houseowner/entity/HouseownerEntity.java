package com.kfwy.hkp.crm.houseowner.entity;

import com.gniuu.framework.common.enums.SexType;
import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.hkp.crm.houseowner.enums.HouseownerType;

import javax.validation.constraints.NotNull;

/**
 * Created by zjp on 2018/7/31.
 */
public class HouseownerEntity extends BaseEntity {

    /**
     * 业主编号
     */
    protected String houseownerCode;

    /**
     * 业主姓名
     */
    @NotNull
    protected String houseownerName;

    /**
     * 业主手机号
     */
    @NotNull
    protected String houseownerPhone;

    /**
     * 业主性别
     */
    @NotNull
    protected String houseownerSex;
    /**
     * 业主性别名
     */
    protected String houseownerSexName;

    /**
     * 业主类型
     */
    protected String houseownerType;

    /**
     * 业主类型名字
     */
    protected String houseownerTypeName;

    /**
     * 公司名字
     */
    protected String companyName;
    protected String empCode;

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    protected String empName;
    protected String deptName;

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getHouseownerCode() {
        return houseownerCode;
    }

    public void setHouseownerCode(String houseownerCode) {
        this.houseownerCode = houseownerCode;
    }

    public String getHouseownerName() {
        return houseownerName;
    }

    public void setHouseownerName(String houseownerName) {
        this.houseownerName = houseownerName;
    }

    public String getHouseownerPhone() {
        return houseownerPhone;
    }

    public void setHouseownerPhone(String houseownerPhone) {
        this.houseownerPhone = houseownerPhone;
    }

    public String getHouseownerSex() {
        return houseownerSex;
    }

    public void setHouseownerSex(String houseownerSex) {
        this.houseownerSex = houseownerSex;
    }

    public String getHouseownerSexName() {

        if (houseownerSexName == null && houseownerSex != null) {
            houseownerSexName = DictionaryStorage.get(SexType.getKey(), this.getHouseownerSex()).getName();
        }

        return houseownerSexName;
    }

    public void setHouseownerSexName(String houseownerSexName) {
        this.houseownerSexName = houseownerSexName;
    }

    public String getHouseownerType() {
        return houseownerType;
    }

    public void setHouseownerType(String houseownerType) {
        this.houseownerType = houseownerType;
        if (this.houseownerTypeName == null && null != this.houseownerType){
            this.houseownerTypeName = DictionaryStorage.get(HouseownerType.class.getName(),this.getHouseownerType()).getName();
        }
    }

    public String getHouseownerTypeName() {

        if (this.houseownerTypeName == null && null != this.houseownerType){
            this.houseownerTypeName = DictionaryStorage.get(HouseownerType.class.getName(),this.getHouseownerType()).getName();
        }

        return houseownerTypeName;
    }

    public void setHouseownerTypeName(String houseownerTypeName) {
        this.houseownerTypeName = houseownerTypeName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
