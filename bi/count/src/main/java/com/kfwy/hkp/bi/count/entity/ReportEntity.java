package com.kfwy.hkp.bi.count.entity;

import com.gniuu.framework.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Create By hjh on 2018/10/23
 */
public class ReportEntity extends BaseEntity {

    // ReportType 1 大区报表 2 部门报表 3 人员报表 4个人报表
    private String ReportType;

    // 兼职开发
    private Integer partTimeDevelop;

    // 房源数
    private Integer houseDevelop;
    // 房源委托
    private Integer houseDepute;
    // 房源跟进
    private Integer houseFollowup;
    // 业主拜访
    private Integer ownerVisit;

    // 客户数
    private Integer cusDevelop;
    // 集中获客
    private Integer focusCount;
    // 客户推送
    private Integer cusPushCount;
    // 有效客户数
    private Integer validCus;
    // 客户跟进数
    private Integer followupCount;
    // 带看数
    private Integer seeCount;
    // 经理跟进数
    private Integer managerFollowupCount;

    // 客户上架数
    private Integer cusUpCount;
    // 房源上架数
    private Integer hosUpCount;

    // 签约
    private Integer orderCount;
    // 开单金额
    private BigDecimal totalMoney;
    // 营收
    private BigDecimal revenue;
    // 分成业绩
    private BigDecimal totalPercentage;
    // 人均收入
    private BigDecimal perIncome;
    // 团队人数
    private Integer manNumber;
    // 入职人数
    private Integer entryPerson;
    // 离职人数
    private Integer quitPerson;


    // 服务专员
    private String showName;
    // 入职时间
    private String showTime;
    // 离职时间
    private String quitTime;


    // 部门数
    private Integer deptSum;

    // 部门
    private String deptCode;

    // 开始时间
    private Date startDate;
    // 结束时间
    private Date endDate;

    private Date inMonth;

    public Date getInMonth() {
        return inMonth;
    }

    public void setInMonth(Date inMonth) {
        this.inMonth = inMonth;
    }

    public String getReportType() {
        return ReportType;
    }

    public void setReportType(String reportType) {
        ReportType = reportType;
    }

    public Integer getPartTimeDevelop() {
        return partTimeDevelop;
    }

    public void setPartTimeDevelop(Integer partTimeDevelop) {
        this.partTimeDevelop = partTimeDevelop;
    }

    public Integer getHouseDevelop() {
        return houseDevelop;
    }

    public void setHouseDevelop(Integer houseDevelop) {
        this.houseDevelop = houseDevelop;
    }

    public Integer getHouseDepute() {
        return houseDepute;
    }

    public void setHouseDepute(Integer houseDepute) {
        this.houseDepute = houseDepute;
    }

    public Integer getHouseFollowup() {
        return houseFollowup;
    }

    public void setHouseFollowup(Integer houseFollowup) {
        this.houseFollowup = houseFollowup;
    }

    public Integer getOwnerVisit() {
        return ownerVisit;
    }

    public void setOwnerVisit(Integer ownerVisit) {
        this.ownerVisit = ownerVisit;
    }

    public Integer getCusDevelop() {
        return cusDevelop;
    }

    public void setCusDevelop(Integer cusDevelop) {
        this.cusDevelop = cusDevelop;
    }

    public Integer getFocusCount() {
        return focusCount;
    }

    public void setFocusCount(Integer focusCount) {
        this.focusCount = focusCount;
    }

    public Integer getCusPushCount() {
        return cusPushCount;
    }

    public void setCusPushCount(Integer cusPushCount) {
        this.cusPushCount = cusPushCount;
    }

    public Integer getValidCus() {
        return validCus;
    }

    public void setValidCus(Integer validCus) {
        this.validCus = validCus;
    }

    public Integer getFollowupCount() {
        return followupCount;
    }

    public void setFollowupCount(Integer followupCount) {
        this.followupCount = followupCount;
    }

    public Integer getSeeCount() {
        return seeCount;
    }

    public void setSeeCount(Integer seeCount) {
        this.seeCount = seeCount;
    }

    public Integer getManagerFollowupCount() {
        return managerFollowupCount;
    }

    public void setManagerFollowupCount(Integer managerFollowupCount) {
        this.managerFollowupCount = managerFollowupCount;
    }

    public Integer getCusUpCount() {
        return cusUpCount;
    }

    public void setCusUpCount(Integer cusUpCount) {
        this.cusUpCount = cusUpCount;
    }

    public Integer getHosUpCount() {
        return hosUpCount;
    }

    public void setHosUpCount(Integer hosUpCount) {
        this.hosUpCount = hosUpCount;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public BigDecimal getRevenue() {
        return revenue;
    }

    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }

    public BigDecimal getTotalPercentage() {
        return totalPercentage;
    }

    public void setTotalPercentage(BigDecimal totalPercentage) {
        this.totalPercentage = totalPercentage;
    }

    public BigDecimal getPerIncome() {
        return perIncome;
    }

    public void setPerIncome(BigDecimal perIncome) {
        this.perIncome = perIncome;
    }

    public Integer getManNumber() {
        return manNumber;
    }

    public void setManNumber(Integer manNumber) {
        this.manNumber = manNumber;
    }

    public Integer getEntryPerson() {
        return entryPerson;
    }

    public void setEntryPerson(Integer entryPerson) {
        this.entryPerson = entryPerson;
    }

    public Integer getQuitPerson() {
        return quitPerson;
    }

    public void setQuitPerson(Integer quitPerson) {
        this.quitPerson = quitPerson;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public String getQuitTime() {
        return quitTime;
    }

    public void setQuitTime(String quitTime) {
        this.quitTime = quitTime;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public Integer getDeptSum() {
        return deptSum;
    }

    public void setDeptSum(Integer deptSum) {
        this.deptSum = deptSum;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
