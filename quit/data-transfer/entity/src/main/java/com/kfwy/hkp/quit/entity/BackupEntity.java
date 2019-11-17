package com.kfwy.hkp.quit.entity;

import com.gniuu.framework.entity.BaseEntity;

import java.util.List;

/**
 * 备份实体类
 */
public class BackupEntity extends BaseEntity {

    /**
     * 转移人编码
     */
    protected String hisEmpCode;
    /**
     * 转移人姓名
     */
    protected String hisEmpName;
    /**
     * 接收人编号
     */
    protected String newEmpCode;
    /**
     * 接收人姓名
     */
    protected String newEmpName;
    /**
     * 转移客户资料数量
     */
    protected Integer shiftCustomerCount;
    /**
     * 转移房源资料数量
     */
    protected Integer shiftHouseCount;
    /**
     * 转移兼职资料数量
     */
    protected Integer shiftUserCount;
    /**
     * 转移业主资料数量

     */
    protected Integer shiftOwnerCount;
    /**
     * 客户编号字符串
     */
    protected String shiftCustomerData;
    /**
     * 房源编号字符串
     */
    protected String shiftHouseData;
    /**
     * 兼职编号字符串
     */
    protected String shiftUserData;
    /**
     * 业主编号字符串
     */
    protected String shiftOwnerData;

    /**
     * 客户编码
     */
    protected List<String> cusCodes;

    /**
     * 房源编码
     */
    protected List<String> hosCodes;

    /**
     * 业主编码
     */
    protected List<String> ownCodes;

    /**
     * 兼职编码
     */
    protected List<String> userCodes;
    /**
     * 是否撤回
     */
    protected Boolean hasWithDraw;

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

    public Integer getShiftCustomerCount() {
        return shiftCustomerCount;
    }

    public void setShiftCustomerCount(Integer shiftCustomerCount) {
        this.shiftCustomerCount = shiftCustomerCount;
    }

    public Integer getShiftHouseCount() {
        return shiftHouseCount;
    }

    public void setShiftHouseCount(Integer shiftHouseCount) {
        this.shiftHouseCount = shiftHouseCount;
    }

    public Integer getShiftUserCount() {
        return shiftUserCount;
    }

    public void setShiftUserCount(Integer shiftUserCount) {
        this.shiftUserCount = shiftUserCount;
    }

    public Integer getShiftOwnerCount() {
        return shiftOwnerCount;
    }

    public void setShiftOwnerCount(Integer shiftOwnerCount) {
        this.shiftOwnerCount = shiftOwnerCount;
    }

    public String getShiftCustomerData() {
        return shiftCustomerData;
    }

    public void setShiftCustomerData(String shiftCustomerData) {
        this.shiftCustomerData = shiftCustomerData;
    }

    public String getShiftHouseData() {
        return shiftHouseData;
    }

    public void setShiftHouseData(String shiftHouseData) {
        this.shiftHouseData = shiftHouseData;
    }

    public String getShiftUserData() {
        return shiftUserData;
    }

    public void setShiftUserData(String shiftUserData) {
        this.shiftUserData = shiftUserData;
    }

    public String getShiftOwnerData() {
        return shiftOwnerData;
    }

    public void setShiftOwnerData(String shiftOwnerData) {
        this.shiftOwnerData = shiftOwnerData;
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

    public Boolean isHasWithDraw() {
        return hasWithDraw;
    }

    public void setHasWithDraw(Boolean hasWithDraw) {
        this.hasWithDraw = hasWithDraw;
    }
}
