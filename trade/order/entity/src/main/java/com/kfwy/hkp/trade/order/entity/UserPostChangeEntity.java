package com.kfwy.hkp.trade.order.entity;

import com.gniuu.framework.entity.BaseEntity;


import java.util.Date;


public class UserPostChangeEntity extends BaseEntity {


    private String userCode;

    private String workNumber;

    private String beforePost;

    private String afterPost;

    private Date startTime;

    private Date endTime;

    private String beforeDept;

    private String afterDept;

    private String group;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(String workNumber) {
        this.workNumber = workNumber;
    }

    public String getBeforePost() {
        return beforePost;
    }

    public void setBeforePost(String beforePost) {
        this.beforePost = beforePost;
    }

    public String getAfterPost() {
        return afterPost;
    }

    public void setAfterPost(String afterPost) {
        this.afterPost = afterPost;
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

    public String getBeforeDept() {
        return beforeDept;
    }

    public void setBeforeDept(String beforeDept) {
        this.beforeDept = beforeDept;
    }

    public String getAfterDept() {
        return afterDept;
    }

    public void setAfterDept(String afterDept) {
        this.afterDept = afterDept;
    }
}
