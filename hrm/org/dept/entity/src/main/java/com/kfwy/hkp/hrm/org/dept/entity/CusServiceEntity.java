package com.kfwy.hkp.hrm.org.dept.entity;

import com.gniuu.framework.entity.BaseEntity;

import java.util.List;

/**
 * Created by xpp on 15-12-28.
 * 15-12-28
 */
public class CusServiceEntity extends BaseEntity {
    //配置客服表唯一编码
    private String cusserviceCode;
    //部门编码
    private String deptCode;
    //部门名称
    private String deptName;
    //客服人员编码
    private String userCode;
    //客服人员名称
    private String userName;


    public String getCusserviceCode() {
        return cusserviceCode;
    }

    public void setCusserviceCode(String cusserviceCode) {
        this.cusserviceCode = cusserviceCode;
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

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
