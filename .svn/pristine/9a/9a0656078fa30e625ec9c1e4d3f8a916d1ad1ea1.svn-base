package com.kfwy.hkp.hrm.org.dept.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.hkp.common.enums.ApplyState;
import com.kfwy.hkp.common.enums.DeptType;
import com.kfwy.hkp.hrm.org.dept.enums.PayStatus;

import java.util.Date;
import java.util.List;

/**
 * Created by davidcun on 2018/5/22.
 * 部门实体
 */
public class DeptEntity extends BaseEntity {

    /**
     * 部门编码
     */
    private String deptCode;
    /**
     * 部门名字
     */
    private String deptName;
    /**
     * 部门类型
     *
     * @see DeptType
     */
    private String deptType;

    private String deptTypeName;
    /**
     * 部门等级
     */
    private Integer deptLevel;
    /**
     * 上级部门编码
     */
    private String parentCode;
    /**
     * 上级部门名称
     */
    private String parentName;
    /**
     * 领导人编码
     */
    private String leaderCode;
    /**
     * 领导人姓名
     */
    private String leaderName;
    /**
     * 领导人电话
     */
    private String leaderPhone;

    /**
     * 领导人头像
     */
    private String leaderImg;

    /**
     * 部门地址
     */
    private String deptAddress;

    /**
     * 是否可以部门内客户共享
     */
    private Boolean hasShareCus;


    /**
     * 是否可以部门内房源共享
     */
    private Boolean hasShareHos;
    /**
     * 资深经理编码
     */
    private String managerCode;
    /**
     * 资深经理
     */
    private String managerName;

    /**
     * 删除时间
     */
    private Date deleteTime;

    private List<DeptAreaEntity> deptAreas;

    // 用于树结构封装
    private List<DeptEntity> children;

    /**
     * 加入团队字段
     */
    //是否已经注册公司
    private Boolean hasRegister;
    //是否显示公司名称
    private String cpyShowName;
    //是否限制隐藏客户
    private Boolean hasLimitHiden;
    //是否财务代理
    private Boolean hasFinanceProxy;
    //是否团队,true表示团队false表示不是团队
    private Boolean teamFlag;


    private Boolean shareBtnFlag;
    /**
     * 状态:已申请,已共享,已拒绝
     *
     * @see ApplyState
     */
    private String applyState;
    private String applyStateName;

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

    /**
     * 支付状态
     *
     * @see PayStatus
     * 支付状态,枚举,思汗补充枚举状态,初始为Begin
     */
    private String payStatus;
    private String payStatusName;
    /**
     * 团队推荐人编码
     */
    private String inviterCode;
    /**
     * 团队推荐人
     */
    private String inviter;

    /**
     * 部门查看区域
     * city 按城市查询
     * region  按区、县查询
     * street 按街道查询
     */
    private String lookArea;

    private Boolean focusCusSeeTimeLimit;

    private Boolean agentCusSeeTimeLimit;

    public Boolean getAgentCusSeeTimeLimit () {
        return agentCusSeeTimeLimit;
    }

    public void setAgentCusSeeTimeLimit (Boolean agentCusSeeTimeLimit) {
        this.agentCusSeeTimeLimit = agentCusSeeTimeLimit;
    }

    public Boolean getFocusCusSeeTimeLimit () {
        return focusCusSeeTimeLimit;
    }

    public void setFocusCusSeeTimeLimit (Boolean focusCusSeeTimeLimit) {
        this.focusCusSeeTimeLimit = focusCusSeeTimeLimit;
    }
//============================================


    public String getLookArea() {
        return lookArea;
    }

    public void setLookArea(String lookArea) {
        this.lookArea = lookArea;
    }

    public String getManagerCode() {
        return managerCode;
    }

    public void setManagerCode(String managerCode) {
        this.managerCode = managerCode;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getDeptTypeName() {

        return DictionaryStorage.get(DeptType.getKey(), this.deptType).getName();
    }

    public void setDeptTypeName(String deptTypeName) {
        this.deptTypeName = deptTypeName;
    }


    public List<DeptAreaEntity> getDeptAreas() {
        return deptAreas;
    }

    public void setDeptAreas(List<DeptAreaEntity> deptAreas) {
        this.deptAreas = deptAreas;
    }

    public List<DeptEntity> getChildren() {
        return children;
    }

    public void setChildren(List<DeptEntity> children) {
        this.children = children;
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

    public String getDeptType() {
        return deptType;
    }

    public void setDeptType(String deptType) {
        this.deptType = deptType;
    }

    public Integer getDeptLevel() {
        return deptLevel;
    }

    public void setDeptLevel(Integer deptLevel) {
        this.deptLevel = deptLevel;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getLeaderCode() {
        return leaderCode;
    }

    public void setLeaderCode(String leaderCode) {
        this.leaderCode = leaderCode;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getLeaderPhone() {
        return leaderPhone;
    }

    public void setLeaderPhone(String leaderPhone) {
        this.leaderPhone = leaderPhone;
    }

    public String getDeptAddress() {
        return deptAddress;
    }

    public void setDeptAddress(String deptAddress) {
        this.deptAddress = deptAddress;
    }

    public Boolean getHasShareCus() {
        return hasShareCus;
    }

    public void setHasShareCus(Boolean hasShareCus) {
        this.hasShareCus = hasShareCus;
    }

    public Boolean getHasShareHos() {
        return hasShareHos;
    }

    public void setHasShareHos(Boolean hasShareHos) {

        this.hasShareHos = hasShareHos;
    }


    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {

        this.payStatus = payStatus;
    }

    public String getPayStatusName() {
        if (this.payStatusName == null && null != this.payStatus) {
            this.payStatusName = DictionaryStorage.get(PayStatus.class.getName(), this.getPayStatus()).getName();
        }
        return payStatusName;
    }

    public void setPayStatusName(String payStatusName) {
        this.payStatusName = payStatusName;
    }

    public String getCpyShowName() {
        return cpyShowName;
    }

    public void setCpyShowName(String cpyShowName) {
        this.cpyShowName = cpyShowName;
    }

    public Boolean getHasRegister() {
        return hasRegister;
    }

    public void setHasRegister(Boolean hasRegister) {
        this.hasRegister = hasRegister;
    }

    public Boolean getHasLimitHiden() {
        return hasLimitHiden;
    }

    public void setHasLimitHiden(Boolean hasLimitHiden) {
        this.hasLimitHiden = hasLimitHiden;
    }

    public Boolean getHasFinanceProxy() {
        return hasFinanceProxy;
    }

    public void setHasFinanceProxy(Boolean hasFinanceProxy) {
        this.hasFinanceProxy = hasFinanceProxy;
    }

    public Boolean getTeamFlag() {
        return teamFlag;
    }

    public void setTeamFlag(Boolean teamFlag) {
        this.teamFlag = teamFlag;
    }

    public Boolean getShareBtnFlag() {
        return shareBtnFlag;
    }

    public void setShareBtnFlag(Boolean shareBtnFlag) {
        this.shareBtnFlag = shareBtnFlag;
    }

    public String getApplyState() {
        return applyState;
    }

    public void setApplyState(String applyState) {
        this.applyState = applyState;
    }

    public String getApplyStateName() {
        if (this.applyStateName == null && null != this.applyState) {
            this.applyStateName = DictionaryStorage.get(ApplyState.class.getName(), this.getApplyState()).getName();
        }
        return applyStateName;
    }

    public void setApplyStateName(String applyStateName) {

        this.applyStateName = applyStateName;
    }

    public String getLeaderImg() {
        return leaderImg;
    }

    public void setLeaderImg(String leaderImg) {
        this.leaderImg = leaderImg;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }
}
