package com.kfwy.hkp.hos.house.entity;


import com.gniuu.framework.entity.BaseEntity;

import java.util.Date;

/**
 * Created by hjh on 2018-10-11.
 */
public class HouseFollowupEntity extends BaseEntity {


    /**
     * 跟进人编号
     */
    private String empCode;

    /**
     * 跟进人姓名
     */
    private String empName;

    /**
     * 跟进人电话
     */
    private String empPhone;

    /**
     * 部门编码
     */
    private String deptCode;

    /**
     * 部门名称
     */
    private String deptName;


    /**
     * 跟进结果
     */
    private String followupContext;


    /**
     * 房源类型，仓库、厂房
     * @see com.kfwy.hkp.hos.house.enums.HousePurposeType
     */
    private String housePurpose;

    /**
     * 房源编号
     */
    private String houseCode;

    /**
     * 房源名称
     */
    private String houseName;

    /**
     * 房源地址
     */
    private String houseAddress;

    /**
     * 房源地址
     */
    private Date LeaseExpiration;

    public Date getLeaseExpiration() {
        return LeaseExpiration;
    }

    public void setLeaseExpiration(Date leaseExpiration) {
        LeaseExpiration = leaseExpiration;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpPhone() {
        return empPhone;
    }

    public void setEmpPhone(String empPhone) {
        this.empPhone = empPhone;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getFollowupContext() {
        return followupContext;
    }

    public void setFollowupContext(String followupContext) {
        this.followupContext = followupContext;
    }

    public String getHousePurpose() {
        return housePurpose;
    }

    public void setHousePurpose(String housePurpose) {
        this.housePurpose = housePurpose;
    }

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }
}
