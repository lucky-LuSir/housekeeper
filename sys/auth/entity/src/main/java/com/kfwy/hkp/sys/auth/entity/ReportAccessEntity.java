package com.kfwy.hkp.sys.auth.entity;

import com.gniuu.framework.entity.BaseEntity;

/**
 * @Auther: HJH
 * @Date: 2019/7/11
 * @Description: TODO
 */
public class ReportAccessEntity extends BaseEntity {

    /**
     * 人员编码
     */
    private String userCode;

    /**
     * 人员名称
     */
    private String userName;

    /**
     * 报表部门编号
     */
    private String deptCode;

    /**
     * 报表部门编号
     */
    private String deptName;

    public ReportAccessEntity() {

    }

    public ReportAccessEntity(String userCode, String deptCode) {
        this.userCode = userCode;
        this.deptCode = deptCode;
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
}
