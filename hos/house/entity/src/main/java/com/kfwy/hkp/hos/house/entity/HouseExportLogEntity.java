package com.kfwy.hkp.hos.house.entity;

import com.gniuu.framework.entity.BaseEntity;

import java.util.Date;

public class HouseExportLogEntity extends BaseEntity {

    private String userCode;

    private Date startTime;

    private Date endTime;

    private String deptCodes;

    private Integer resultNumber;

    public Integer getResultNumber() {
        return resultNumber;
    }

    public void setResultNumber(Integer resultNumber) {
        this.resultNumber = resultNumber;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getDeptCodes() {
        return deptCodes;
    }

    public void setDeptCodes(String deptCodes) {
        this.deptCodes = deptCodes;
    }
}
