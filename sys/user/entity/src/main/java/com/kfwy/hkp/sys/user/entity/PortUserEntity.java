package com.kfwy.hkp.sys.user.entity;


import com.gniuu.framework.entity.BaseEntity;

/**
 * 端口用户实体类
 */
public class PortUserEntity extends BaseEntity {
    /**
     * 展示号码
     */
    private String showPhone;
    /**
     * 端口对应部门编码
     */
    private String portDeptCode;
    /**
     * 端口对应部门名
     */
    private String portDeptName;
    /**
     *所属大区
     */
    private String regionalName;
    /**
     * 端口使用人
     */
    private String portUserName;

    private String portUserCode;

    public String getPortUserCode () {
        return portUserCode;
    }

    public void setPortUserCode (String portUserCode) {
        this.portUserCode = portUserCode;
    }

    public String getShowPhone () {
        return showPhone;
    }

    public void setShowPhone (String showPhone) {
        this.showPhone = showPhone;
    }

    public String getPortDeptCode () {
        return portDeptCode;
    }

    public void setPortDeptCode (String portDeptCode) {
        this.portDeptCode = portDeptCode;
    }

    public String getPortDeptName () {
        return portDeptName;
    }

    public void setPortDeptName (String portDeptName) {
        this.portDeptName = portDeptName;
    }

    public String getRegionalName () {
        return regionalName;
    }

    public void setRegionalName (String regionalName) {
        this.regionalName = regionalName;
    }

    public String getPortUserName () {
        return portUserName;
    }

    public void setPortUserName (String portUserName) {
        this.portUserName = portUserName;
    }
}
