package com.kfwy.imports.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gniuu.framework.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by davidcun on 2015-11-12.
 *
 * @author davidcun
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestEmployeeEntity extends BaseEntity {
    /**
     * 登录账号
     */
    private String loginAccount;
    /**
     * 员工编码
     */
    private String empCode;
    /**
     * 员工姓名
     */
    private String empName;
    /**
     * 密码
     */
    private String password;
    /**
     * 明码
     */
    private String orgPassword;
    /**
     * 员工类型，普通职员、兼职人员
     */
    private Integer empType;
    /**
     * 电话号码
     */
    private String phoneNumber;
    /**
     * 直管人员编号
     */
    private String parentCode;
    /**
     * 性别
     */
    private String sex;
    /**
     * 微信
     */
    private String weiXin;
    /**
     * 扣扣
     */
    private String qq;
    /**
     * 邮编
     */
    private String email;

    /**
     * 是否启用
     */
    private Boolean enable;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 区
     */
    private String region;

    private String provinceName;

    private String cityName;

    private String regionName;
  /*  private Attachment imagesFiled;

    public Attachment getImagesFiled() {
        return imagesFiled;
    }

    public void setImagesFiled(Attachment imagesFiled) {
        this.imagesFiled = imagesFiled;
    }*/

    /**
     * 部门编码
     */
    private String deptCode;

    /**
     * 部门名字
     */
    private String deptName;

    /**
     * 员工岗位
     */
    private String empPostName;

    /**
     * 岗位编码
     */
    private String empPostCode;

    /**
     * 业务员描述
     */
    private String description;

    /**
     * 员工头像
     */
    private String url;

    /**
     * 基本工资
     */
    private BigDecimal baseSalary;

    /**
     * 提成工资
     */
    private BigDecimal gainSalary;
    /**
     * 提成规则编码
     */
    private String cinCode;
    /**
     * 提成规则
     */
    private String cinRule;
    /**
     * 提成类容
     */
    private String content;
    /**
     * 新密码
     */
    private String newPassword;
    /**
     * 重复密码
     */
    private String repeatPassword;
    /**
     * 是否修改密码
     */
    private Integer updatePassword;
    /**
     * 人员身份证
     */
    private String card;
    /**
     * 开户银行
     */
    private String openBank;
    /**
     * 银行卡号
     */
    private String bankCard;
    /**
     * 入职时间
     */
    private Date entryTime;
    /**
     * 离职时间
     */
    private Date quitTime;
    //是否已经离职
    private Boolean hasQuit;
    /**
     * 是否需要认证
     */
    private Boolean needAuth;

    /**
     * 移动办公是否认证通过
     */
    private Boolean hasAuth;

    /**
     * 学历
     */
    private String education;
    /**
     * 毕业院校
     */
    private String graduateSchool;

    /**
     * 所学专业
     */
    private String professional;

    /**

     * 毕业时间
     */
    private Date graduateDate;

    /**
     * 婚否
     */
    private Boolean hasMarry;
    /**
     * 户籍类型
     */
    private String householdRegisterType;
    /**
     * 社保城市
     */
    private String socialSecurityCity;
    /**
     * 出生年月日
     */
    private Date birthDate;
    /**
     * 户籍所在地
     */
    private String householdRegisterLocation;
    /**
     * 家庭住址（在沪住址）
     */
    private String homeAddress;
    /**
     * 转正日期
     */
    private Date becomeFomalDate;
    /**
     * 签订合同日期
     */
    private Date contractStartDate;
    /**
     * 合同到期日期
     */
    private Date contractEndDate;
    /**
     * 工作经历
     */
    private String workExperience;

    /**
     * 紧急联系人
     */
    private String emergencyContact;

    /**
     * 紧急联系人手机号
     */
    private String emergencyContactPhone;
    /**
     * 紧急联系人2
     */
    private String emergencyContactTwo;

    /**
     * 紧急联系人2手机号
     */
    private String emergencyContactPhoneTwo;

    public String getEmergencyContactTwo() {
        return emergencyContactTwo;
    }

    public void setEmergencyContactTwo(String emergencyContactTwo) {
        this.emergencyContactTwo = emergencyContactTwo;
    }

    public String getEmergencyContactPhoneTwo() {
        return emergencyContactPhoneTwo;
    }

    public void setEmergencyContactPhoneTwo(String emergencyContactPhoneTwo) {
        this.emergencyContactPhoneTwo = emergencyContactPhoneTwo;
    }

    //临时添加
    private String doType;

    //一级部门
    private String oneDeptName;
    //二级部门
    private String twoDeptName;
    //三级部门
    private String threeDeptName;
    //四级部门
    private String fourDeptName;

    //员工入职状态
    private Integer entryStatus;

    /**
     * 员工工号
     */
    private String jobNumber;

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public Integer getEntryStatus() {
        return entryStatus;
    }

    public void setEntryStatus(Integer entryStatus) {
        this.entryStatus = entryStatus;
    }

    public String getOneDeptName() {
        return oneDeptName;
    }

    public void setOneDeptName(String oneDeptName) {
        this.oneDeptName = oneDeptName;
    }

    public String getTwoDeptName() {
        return twoDeptName;
    }

    public void setTwoDeptName(String twoDeptName) {
        this.twoDeptName = twoDeptName;
    }

    public String getThreeDeptName() {
        return threeDeptName;
    }

    public void setThreeDeptName(String threeDeptName) {
        this.threeDeptName = threeDeptName;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getEmergencyContactPhone() {
        return emergencyContactPhone;
    }

    public void setEmergencyContactPhone(String emergencyContactPhone) {
        this.emergencyContactPhone = emergencyContactPhone;
    }

    public String getFourDeptName() {
        return fourDeptName;
    }

    public void setFourDeptName(String fourDeptName) {
        this.fourDeptName = fourDeptName;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmpPostName() {
        return empPostName;
    }

    public void setEmpPostName(String empPostName) {
        this.empPostName = empPostName;
    }

    public String getEmpPostCode() {
        return empPostCode;
    }

    public void setEmpPostCode(String empPostCode) {
        this.empPostCode = empPostCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOrgPassword() {
        return orgPassword;
    }

    public void setOrgPassword(String orgPassword) {
        this.orgPassword = orgPassword;
    }

    public Integer getEmpType() {
        return empType;
    }

    public void setEmpType(Integer empType) {
        this.empType = empType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getWeiXin() {
        return weiXin;
    }

    public void setWeiXin(String weiXin) {
        this.weiXin = weiXin;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public BigDecimal getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(BigDecimal baseSalary) {
        this.baseSalary = baseSalary;
    }

    public BigDecimal getGainSalary() {
        return gainSalary;
    }

    public void setGainSalary(BigDecimal gainSalary) {
        this.gainSalary = gainSalary;
    }

    public String getCinCode() {
        return cinCode;
    }

    public void setCinCode(String cinCode) {
        this.cinCode = cinCode;
    }

    public String getCinRule() {
        return cinRule;
    }

    public void setCinRule(String cinRule) {
        this.cinRule = cinRule;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public Integer getUpdatePassword() {
        return updatePassword;
    }

    public void setUpdatePassword(Integer updatePassword) {
        this.updatePassword = updatePassword;
    }


    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
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

    public Boolean getHasQuit() {
        return hasQuit;
    }

    public void setHasQuit(Boolean hasQuit) {
        this.hasQuit = hasQuit;
    }

    public Boolean getNeedAuth() {
        return needAuth;
    }

    public void setNeedAuth(Boolean needAuth) {
        this.needAuth = needAuth;
    }

    public Boolean getHasAuth() {
        return hasAuth;
    }

    public void setHasAuth(Boolean hasAuth) {
        this.hasAuth = hasAuth;
    }

    public String getDoType() {
        return doType;
    }

    public void setDoType(String doType) {
        this.doType = doType;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getGraduateSchool() {
        return graduateSchool;
    }

    public void setGraduateSchool(String graduateSchool) {
        this.graduateSchool = graduateSchool;
    }

    public Boolean getHasMarry() {
        return hasMarry;
    }

    public void setHasMarry(Boolean hasMarry) {
        this.hasMarry = hasMarry;
    }

    public String getHouseholdRegisterType() {
        return householdRegisterType;
    }

    public void setHouseholdRegisterType(String householdRegisterType) {
        this.householdRegisterType = householdRegisterType;
    }

    public String getSocialSecurityCity() {
        return socialSecurityCity;
    }

    public void setSocialSecurityCity(String socialSecurityCity) {
        this.socialSecurityCity = socialSecurityCity;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getHouseholdRegisterLocation() {
        return householdRegisterLocation;
    }

    public void setHouseholdRegisterLocation(String householdRegisterLocation) {
        this.householdRegisterLocation = householdRegisterLocation;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Date getBecomeFomalDate() {
        return becomeFomalDate;
    }

    public void setBecomeFomalDate(Date becomeFomalDate) {
        this.becomeFomalDate = becomeFomalDate;
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

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }
    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public Date getGraduateDate() {
        return graduateDate;
    }

    public void setGraduateDate(Date graduateDate) {
        this.graduateDate = graduateDate;
    }
}
