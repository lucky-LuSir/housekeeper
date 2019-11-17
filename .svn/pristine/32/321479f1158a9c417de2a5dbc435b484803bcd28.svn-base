package com.kfwy.hkp.sys.user.entity;


import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.user.entity.BaseUserEntity;
import com.kfwy.hkp.sys.auth.entity.RoleEntity;
import com.kfwy.hkp.sys.user.enums.UserType;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by davidcun on 2018/5/16.
 *
 * 此User是当前系统的用户登录
 */
public class InternalUserEntity extends BaseUserEntity {


    /**
     * 工号
     */
    private String workNumber;

    /**
     * 用户类型
     * @see UserType
     */
    private String userType;
    private String userTypeName;

    /**
     * 设备ID，友盟推送用
     */
    private String deviceToken;

    /**
     * 设备编号，用来授权作用
     */
    private String identifyNumber;

    /**
     * 新增时客户的类型： 1 平台  2 经纪人
     *  CustomerType
     */
    private String enterCusType;


    /**如下是ERP信息==============================*/

    /**
     * 是否是经理
     */
    private Boolean hasManager;

    /**
     * 领导编码
     */
    private String leaderCode;

    /**
     * 岗位编码
     */
    private String postCode;

    /**
     * 岗位名称
     */
    private String postName;

    /**
     * 入职时间
     */
    private Date entryTime;
    /**
     * 离职时间
     */
    private Date quitTime;
    /**
     * 角色
     */
    private List<RoleEntity> userRole;

    public List<RoleEntity> getUserRole() {
        return userRole;
    }

    public void setUserRole(List<RoleEntity> userRole) {
        this.userRole = userRole;
    }

    public String getIdentifyNumber() {
        return identifyNumber;
    }

    public void setIdentifyNumber(String identifyNumber) {
        this.identifyNumber = identifyNumber;
    }

    public String getLeaderCode() {
        return leaderCode;
    }

    public void setLeaderCode(String leaderCode) {
        this.leaderCode = leaderCode;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Date getQuitTime() {
        return quitTime;
    }

    public void setQuitTime(Date quitTime) {
        this.quitTime = quitTime;
    }

    private String deptCode;

    private String deptName;

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

    public String getUserTypeName() {
        return DictionaryStorage.get(UserType.getKey(),this.getUserType()).getName();
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }

    public String getUserType() {

        if (StringUtils.isEmpty(userType)){
            userType = UserType.Employee;
        }
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }


    public String getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(String workNumber) {
        this.workNumber = workNumber;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getEnterCusType() {
        return enterCusType;
    }

    public void setEnterCusType(String enterCusType) {
        this.enterCusType = enterCusType;
    }

    public Boolean getHasManager() {
        return hasManager;
    }

    public void setHasManager(Boolean hasManager) {
        this.hasManager = hasManager;
    }
}
