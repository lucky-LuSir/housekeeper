package com.kfwy.hkp.hrm.org.employee.entity;

import com.gniuu.framework.entity.BaseEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * Created by davidcun on 2018/5/15.
 *
 */
public class EmployeeEntity extends BaseEntity {

    /**
     * 人员编号
     */
    private String empCode;
    /**
     * 工号(可登陆账号)
     */
    private String workNumber;
    /**
     * 人员信息
     */
    /**
     * 姓名
     */
    private String empName;
    /**
     * 性别(男,女)
     */
    private String sex;
    /**
     * 手机号
     */
    private String empPhone;

    /**
     * 与empPhone通样值
     */
    private String phoneNumber;
    /**
     * 身份证
     */
    private String card;
    /**
     * 照片
     */
    private String empImg;
    /**
     * 出生日期
     */
    private Date birthDate;
    /**
     * 婚否
     */
    private Boolean hasMarry;
    /**
     * 户籍类型
     */
    private String householdType;
    /**
     * 户籍所在地
     */
    private String householdLocation;
    /**
     * 现住址
     */
    private String homeAddress;
    /**
     * 学历
     */
    private String education;
    /**
     * 毕业院校
     */
    private String graduate;




    /**
     * 企业信息
     */
    /**
     * 岗位
     */
    private String cpyJobs;
    /**
     * 开户银行
     */
    private String openBank;
    /**
     * 银行卡号
     */
    private String bankCard;
    /**
     * 劳动合同起始日
     */
    private Date contractStartDate;
    /**
     * 劳动合同到期日
     */
    private Date contractEndDate;
    /**
     * 社保地
     */
    private String socialSecurityCity;

    /**
     * 岗位编码
     */
    private String postCode;
    /**
     * 岗位名称
     */
    private String postName;

    /**
     * 所属部门编码
     */
    private String deptCode;
    /**
     * 所属部门名称
     */
    private String deptName;

    /**
     * 职级编码
     */
    private String rankCode;
    /**
     * 职级名称
     */
    private String rankName;


    //紧急联系人0925
    private String urgName;
    private String urgPhone;
    private String urgRelation;

    /**
     * 录入类型(1,平台  2,经纪人)
     * (1,平台(客服)  2,经纪人)
     */
    private String enterCusType;

    /**
     * 是否经理
     */
    public Boolean hasManager;


    //暂时不用0925
    /**
     * 岗位编码
     */
    private String empPostCode;
    /**
     * 岗位名称
     */
    private String empPostName;

    public String getPhoneNumber() {
        if (StringUtils.isEmpty(phoneNumber)){
            this.phoneNumber = this.empPhone;
        }
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(String workNumber) {
        this.workNumber = workNumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmpPhone() {
        if (StringUtils.isEmpty(empPhone)){
            this.empPhone = this.phoneNumber;
        }
        return empPhone;
    }

    public void setEmpPhone(String empPhone) {
        this.empPhone = empPhone;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getEmpImg() {
        return empImg;
    }

    public void setEmpImg(String empImg) {
        this.empImg = empImg;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getHasMarry() {
        return hasMarry;
    }

    public void setHasMarry(Boolean hasMarry) {
        this.hasMarry = hasMarry;
    }

    public String getHouseholdType() {
        return householdType;
    }

    public void setHouseholdType(String householdType) {
        this.householdType = householdType;
    }

    public String getHouseholdLocation() {
        return householdLocation;
    }

    public void setHouseholdLocation(String householdLocation) {
        this.householdLocation = householdLocation;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getGraduate() {
        return graduate;
    }

    public void setGraduate(String graduate) {
        this.graduate = graduate;
    }

    public String getCpyJobs() {
        return cpyJobs;
    }

    public void setCpyJobs(String cpyJobs) {
        this.cpyJobs = cpyJobs;
    }

    public String getOpenBank() {
        return openBank;
    }

    public void setOpenBank(String openBank) {
        this.openBank = openBank;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public Date getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(Date contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    public Date getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(Date contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    public String getSocialSecurityCity() {
        return socialSecurityCity;
    }

    public void setSocialSecurityCity(String socialSecurityCity) {
        this.socialSecurityCity = socialSecurityCity;
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

    public String getRankCode() {
        return rankCode;
    }

    public void setRankCode(String rankCode) {
        this.rankCode = rankCode;
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    public String getUrgName() {
        return urgName;
    }

    public void setUrgName(String urgName) {
        this.urgName = urgName;
    }

    public String getUrgPhone() {
        return urgPhone;
    }

    public void setUrgPhone(String urgPhone) {
        this.urgPhone = urgPhone;
    }

    public String getUrgRelation() {
        return urgRelation;
    }

    public void setUrgRelation(String urgRelation) {
        this.urgRelation = urgRelation;
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

    public Boolean getHasManager() {
        return hasManager;
    }

    public void setHasManager(Boolean hasManager) {
        this.hasManager = hasManager;
    }

    public String getEnterCusType() {
        return enterCusType;
    }

    public void setEnterCusType(String enterCusType) {
        this.enterCusType = enterCusType;
    }
}
