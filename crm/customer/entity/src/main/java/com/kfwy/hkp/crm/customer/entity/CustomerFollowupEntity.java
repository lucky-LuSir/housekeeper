package com.kfwy.hkp.crm.customer.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.hkp.crm.customer.enums.CustomerFollowupType;
import com.kfwy.hkp.sys.file.entity.FileRelationEntity;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Create By hjh on 2018/8/23
 * @author baiye
 */
public class CustomerFollowupEntity extends BaseEntity {
    /**
     * 是否决策人：是/否
     */
    protected Boolean isDecision;
    /**
     * 是否主动询问找房进度：是/否
     */
    protected Boolean isInitiativeAsk;
    /**
     * 带看是否积极主动：是否/否
     */
    protected Boolean isActive;
    /**
     * 是否在当地找过房子：是/否
     */
    protected Boolean isFindLocal;
    /**
     * 经理对意向客户的跟进
     */
    protected Boolean managerFollowup;
    /**
    * 跟进编码
    * */
    protected String followupCode;
    /**
    * 跟进内容
    * */
    protected String followupContent;

    /**
    * 跟进类型 1 实地带看 2 电话拜访 3 上门拜访
    * */
    protected String followupType;
    /**
     *
     */
    protected String followupTypeName;

    /**
    * 客户编号
    * */
    @NotNull(message = "客户编码不能为空")
    protected String cusCode;

    protected String cusName;

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    /**
     * 跟进人编号
     */
    protected String empCode;

    /**
     * 跟进人姓名
     */
    protected String empName;

    /**
     *  租赁到期时间
     */
    protected Date leaseExpiration;

    /**
     * 跟进人电话
     */
    protected String empPhone;
    /**
     * 部门编码
     */
    protected String deptCode;

    /**
     * 部门名称
     */
    protected String deptName;

    /**
     * 员工头像
     */
    protected String userImg;

    /**
     *
     */
    protected String jointRemark;

    /**
    * 文件
    * */
    protected List<FileRelationEntity> fileList;
    /**
    * 多房源
    * */
    protected List<FollowupHouseEntity> followupHouseList;

    public Date getLeaseExpiration() {
        return leaseExpiration;
    }

    public void setLeaseExpiration(Date leaseExpiration) {
        this.leaseExpiration = leaseExpiration;
    }

    // 房源信息
    public List<FollowupHouseEntity> getFollowupHouseList() {
        return followupHouseList;
    }

    public void setFollowupHouseList(List<FollowupHouseEntity> followupHouseList) {
        this.followupHouseList = followupHouseList;
    }

    public List<FileRelationEntity> getFileList() {
        return fileList;
    }

    public void setFileList(List<FileRelationEntity> fileList) {
        this.fileList = fileList;
    }

    public String getFollowupCode() {
        return followupCode;
    }

    public void setFollowupCode(String followupCode) {
        this.followupCode = followupCode;
    }

    public String getFollowupContent() {
        return followupContent;
    }

    public void setFollowupContent(String followupContent) {
        this.followupContent = followupContent;
    }

    public String getFollowupType() {
        return followupType;
    }

    public void setFollowupType(String followupType) {
        this.followupType = followupType;
    }

    public String getFollowupTypeName() {
        if (this.followupTypeName == null && null != this.followupType){
            this.followupTypeName = DictionaryStorage.get(CustomerFollowupType.class.getName(),this.getFollowupType()).getName();
        }

        return followupTypeName;
    }

    public void setFollowupTypeName(String followupTypeName) {
        this.followupTypeName = followupTypeName;
    }

    public String getCusCode() {
        return cusCode;
    }

    public void setCusCode(String cusCode) {
        this.cusCode = cusCode;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpPhone() {
        return empPhone;
    }

    public void setEmpPhone(String empPhone) {
        this.empPhone = empPhone;
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
    public Boolean getIsDecision() {
        return this.isDecision;
    }

    public void setIsDecision(Boolean isDecision) {
        this.isDecision = isDecision;
    }

    public Boolean getIsInitiativeAsk() {
        return this.isInitiativeAsk;
    }

    public void setInitiativeAsk(Boolean isInitiativeAsk) {
        this.isInitiativeAsk = isInitiativeAsk;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsFindLocal() {
        return isFindLocal;
    }

    public void setIsFindLocal(Boolean isFindLocal) {
        this.isFindLocal = isFindLocal;
    }

    public Boolean getManagerFollowup() {
        return managerFollowup;
    }

    public void setManagerFollowup(Boolean managerFollowup) {
        this.managerFollowup = managerFollowup;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getJointRemark() {
        return jointRemark;
    }

    public void setJointRemark(String jointRemark) {
        this.jointRemark = jointRemark;
    }
}
