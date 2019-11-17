package com.kfwy.hkp.bi.count.entity;

import com.gniuu.framework.entity.BaseEntity;

import java.util.Date;

/**
 * @Auther: Lzp
 * @Date: 2019/8/18
 * @Description: TODO
 */
public class LastDayReportEntity extends BaseEntity {

    // 日期
    private Date inTime;

    private String userCode;

    private String userName;

    private String deptCode;
    // 数量
    private Integer callEmpPhone;

    private Integer seeHosDetail;

    private Integer seeCusDetail;

    private Integer callHouseOwnerPhone;

    private Integer callCusPhone;

    private Integer seeHouseOwnerDetail;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public Integer getCallEmpPhone() {
        return callEmpPhone;
    }

    public void setCallEmpPhone(Integer callEmpPhone) {
        this.callEmpPhone = callEmpPhone;
    }

    public Integer getSeeHosDetail() {
        return seeHosDetail;
    }

    public void setSeeHosDetail(Integer seeHosDetail) {
        this.seeHosDetail = seeHosDetail;
    }

    public Integer getSeeCusDetail() {
        return seeCusDetail;
    }

    public void setSeeCusDetail(Integer seeCusDetail) {
        this.seeCusDetail = seeCusDetail;
    }

    public Integer getCallHouseOwnerPhone() {
        return callHouseOwnerPhone;
    }

    public void setCallHouseOwnerPhone(Integer callHouseOwnerPhone) {
        this.callHouseOwnerPhone = callHouseOwnerPhone;
    }

    public Integer getCallCusPhone() {
        return callCusPhone;
    }

    public void setCallCusPhone(Integer callCusPhone) {
        this.callCusPhone = callCusPhone;
    }

    public Integer getSeeHouseOwnerDetail() {
        return seeHouseOwnerDetail;
    }

    public void setSeeHouseOwnerDetail(Integer seeHouseOwnerDetail) {
        this.seeHouseOwnerDetail = seeHouseOwnerDetail;
    }
}
