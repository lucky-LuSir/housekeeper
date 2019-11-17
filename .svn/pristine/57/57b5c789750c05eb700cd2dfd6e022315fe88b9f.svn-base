package com.kfwy.hkp.sys.user.entity;


import com.gniuu.framework.common.enums.SexType;
import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.user.entity.BaseUserEntity;
import com.kfwy.hkp.hrm.org.dept.entity.DeptAreaEntity;
import com.kfwy.hkp.hrm.org.dept.enums.PayStatus;
import com.kfwy.hkp.sys.auth.entity.RoleEntity;
import com.kfwy.hkp.sys.user.enums.UserSalaryType;
import com.kfwy.hkp.sys.user.enums.UserType;
import com.kfwy.hkp.sys.user.enums.UserCAStatus;
import com.kfwy.hkp.sys.user.enums.InviteState;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by davidcun on 2018/5/16.
 * <p>
 * 此User是当前系统的用户登录
 */
public class UserEntity extends BaseUserEntity {

    /**
     * 用户类型，EMP默认为Employee类型
     *
     * @see UserType
     */
    protected String userType;

    protected Date inDeptTeamTime;
    /**
     * 用户类型名
     */
    protected String userTypeName;

    /**
     * 用户类型，EMP默认为Employee类型
     *
     * @see InviteState
     */
    protected String inviteState;
    /**
     * 用户类型名
     */
    protected String inviteStateName;
    /**
     * 工号
     */
    protected String workNumber;

    /**
     * 是否是经理
     */
    protected Boolean hasManager;

    /**
     * 入职类型： 1 客服  2 其他
     * @see com.kfwy.hkp.crm.customer.enums
     */
    protected String enterType;

    /**
     * 领导编码
     */
    protected String leaderCode;

    /**
     * 领导姓名
     */
    protected String leaderName;

    /**
     * 岗位编码
     */
    protected String empPostCode;

    /**
     * 岗位名称
     */
    protected String empPostName;

    /**
     * 入职时间
     */
    protected Date entryTime;
    /**
     * 离职时间
     */
    protected Date quitTime;

    /**
     * 友盟推送
     */
    protected String deviceToken;
    /**
     * 是否认证
     */
    protected Boolean needAuth;
    /**
     * 设备编号，用来授权作用
     */
    protected String identifyNumber;
    /**
     * 绑定设备名
     */
    protected String deviceBrand;
    /**
     * 认证进度
     */
    protected String userCAStatus;
    /**
     * 认证进度名称
     */
    protected String userCAStatusName;
    /**
     * 身份证卡
     */
    protected String card;
    /**
     * 身份证正面图片路径
     */
    protected String cardImageName;

    /**
     * 银行卡正面图片路径
     */
    protected String bankCardImageName;
    /**
     * 角色
     */
    protected List<RoleEntity> userRole;
    /**
     * 这个字段 第一个值省 第二个市 第三个区 第四个街道 code+name
     */
    protected List<String> userArea;

    /**
     * 用户等级
     */
    protected String userLevel;

    protected List<DeptAreaEntity> deptAreas;

    protected List<UserAreaEntity> userAreas;

    /**
     * 是否被推荐
     */
    protected Boolean hasReferrer;
    /**
     * 推荐人编码
     */
    protected String referrerCode;
    /**
     * 推荐人姓名
     */
    protected String referrerName;
    /**
     * 薪资方案
     */
    protected String salaryType;
    /**
     * 薪资方案名称
     */
    protected String salaryTypeName;

    protected String sexTypeName;

    protected String hasPass;

    protected String groupName;

    /**
     * 支付状态
     * @see PayStatus
     * 支付状态,枚举,思汗补充枚举状态,初始为Begin
     */
    protected String payStatus;

    protected String payStatusName;
    /**
     * 团队推荐人编码
     */
    protected String inviterCode;
    /**
     * 团队推荐人
     */
    protected String inviter;

    /**
     * 账号是否冻结
     */
    protected Boolean isFrost;
    /**
     * 是否能获得激活码
     */
    protected Boolean canGetActivationCode;

    protected Date focusTime;

    protected boolean handle;

    protected Date seeCusTime;

    protected Integer followCount;

    protected Boolean specialPush;

    protected String teacherName;

    protected String teacherCode;

    public Boolean getSpecialPush () {
        return specialPush;
    }

    public void setSpecialPush (Boolean specialPush) {
        this.specialPush = specialPush;
    }

    public Integer getFollowCount () {
        return followCount;
    }

    public void setFollowCount (Integer followCount) {
        this.followCount = followCount;
    }

    public Date getSeeCusTime () {
        return seeCusTime;
    }

    public void setSeeCusTime (Date seeCusTime) {
        this.seeCusTime = seeCusTime;
    }

    public boolean isHandle() {
        return handle;
    }

    public void setHandle(boolean handle) {
        this.handle = handle;
    }

    public Date getFocusTime() {
        return focusTime;
    }

    public void setFocusTime(Date focusTime) {
        this.focusTime = focusTime;
    }

    public Boolean isCanGetActivationCode() {
        return canGetActivationCode;
    }

    public void setCanGetActivationCode(Boolean canGetActivationCode) {
        this.canGetActivationCode = canGetActivationCode;
    }

    public Boolean getIsFrost() {
        return isFrost;
    }

    public void setIsFrost(Boolean isFrost) {
        this.isFrost = isFrost;
    }

    public Boolean getHasReferrer() {
        return hasReferrer;
    }

    public void setHasReferrer(Boolean hasReferrer) {
        this.hasReferrer = hasReferrer;
    }

    public String getReferrerCode() {
        return referrerCode;
    }

    public void setReferrerCode(String referrerCode) {
        this.referrerCode = referrerCode;
    }

    public String getReferrerName() {
        return referrerName;
    }

    public void setReferrerName(String referrerName) {
        this.referrerName = referrerName;
    }

    public String getSalaryType() {
        return salaryType;
    }

    public void setSalaryType(String salaryType) {
        this.salaryType = salaryType;
    }

    public String getSalaryTypeName() {
        return DictionaryStorage.get(UserSalaryType.getKey(), this.getSalaryType()).getName();
    }


    public void setSalaryTypeName(String salaryTypeName) {
        this.salaryTypeName = salaryTypeName;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public List<String> getUserArea() {
        return userArea;
    }

    public void setUserArea(List<String> userArea) {
        this.userArea = userArea;
    }

    public List<RoleEntity> getUserRole() {
        return userRole;
    }

    public void setUserRole(List<RoleEntity> userRole) {
        this.userRole = userRole;
    }

    public String getDeviceBrand() {
        return deviceBrand;
    }

    public void setDeviceBrand(String deviceBrand) {
        this.deviceBrand = deviceBrand;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public List<UserAreaEntity> getUserAreas() {
        return userAreas;
    }

    public void setUserAreas(List<UserAreaEntity> userAreas) {
        this.userAreas = userAreas;
    }

    public List<DeptAreaEntity> getDeptAreas() {
        return deptAreas;
    }

    public void setDeptAreas(List<DeptAreaEntity> deptAreas) {
        this.deptAreas = deptAreas;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public Boolean getNeedAuth() {
        return needAuth;
    }

    public void setNeedAuth(Boolean needAuth) {
        this.needAuth = needAuth;
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

    public String getEmpPostCode() {
        return empPostCode;
    }

    public void setEmpPostCode(String empPostCode) {
        this.empPostCode = empPostCode;
    }

    public String getEmpPostName() {
        return empPostName;
    }

    public void setEmpPostName(String empPostName) {
        this.empPostName = empPostName;
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
    public String getUserCAStatus() {
        return userCAStatus;
    }

    public void setUserCAStatus(String userCAStatus) {
        this.userCAStatus = userCAStatus;
    }

    public String getUserCAStatusName() {
        return DictionaryStorage.get(UserCAStatus.getKey(), this.getUserCAStatus()).getName();
    }

    public void setUserCAStatusName(String userCAStatusName) {
        this.userCAStatusName = userCAStatusName;
    }

    public String getUserTypeName() {
        return DictionaryStorage.get(UserType.getKey(), this.getUserType()).getName();
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }

    public String getInviteState() {
        return inviteState;
    }

    public void setInviteState(String inviteState) {
        this.inviteState = inviteState;
    }

    public String getInviteStateName() {
        return DictionaryStorage.get(InviteState.getKey(), this.getInviteState()).getName();
    }

    public void setInviteStateName(String inviteStateName) {
        this.inviteStateName = inviteStateName;
    }

    public String getSexTypeName() {
        return DictionaryStorage.get(SexType.getKey(), this.getSex()).getName();
    }

    public void setSexTypeName(String sexTypeName) {
        this.sexTypeName = sexTypeName;
    }

    public void setCardImageName(String cardImageName) {
        this.cardImageName = cardImageName;
    }

    public void setBankCardImageName(String bankCardImageName) {
        this.bankCardImageName = bankCardImageName;
    }

    public String getCardImageName() {
        return cardImageName;
    }

    public String getBankCardImageName() {
        return bankCardImageName;
    }

    @Override
    public String getSex() {
        if (StringUtils.isEmpty(sex)) {
            sex = SexType.Lady;
        }
        return sex;
    }

    public String getUserType() {

        if (StringUtils.isEmpty(userType)) {
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

    public String getEnterType() {
        return enterType;
    }

    public void setEnterType(String enterType) {
        this.enterType = enterType;
    }

    public Boolean getHasManager() {
        return hasManager;
    }

    public void setHasManager(Boolean hasManager) {
        this.hasManager = hasManager;
    }

    public String getHasPass() {
        return hasPass;
    }

    public void setHasPass(String hasPass) {
        this.hasPass = hasPass;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Date getInDeptTeamTime() {
        return inDeptTeamTime;
    }

    public void setInDeptTeamTime(Date inDeptTeamTime) {
        this.inDeptTeamTime = inDeptTeamTime;
    }  public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getPayStatusName() {
        return payStatusName;
    }

    public void setPayStatusName(String payStatusName) {
        this.payStatusName = payStatusName;
    }

    public String getInviterCode() {
        return inviterCode;
    }

    public void setInviterCode(String inviterCode) {
        this.inviterCode = inviterCode;
    }

    public String getInviter() {
        return inviter;
    }

    public void setInviter(String inviter) {
        this.inviter = inviter;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherCode() {
        return teacherCode;
    }

    public void setTeacherCode(String teacherCode) {
        this.teacherCode = teacherCode;
    }
}
