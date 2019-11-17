package com.kfwy.hkp.bi.count.entity;

import com.gniuu.framework.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * Create By hjh on 2018/9/5
 */
public class WorkbenchEntity extends BaseEntity {

    // 我的总利润
    private BigDecimal totalProfit;
    // 我的月利润
    private BigDecimal moonProfit;
    // 房源数量
    private Integer houseCount;
    // 客户数量
    private Integer cusCount;
    // 合作数量
    private Integer unionCount;
    // 带看数量
    private Integer followupCount;


    private String empCode;
    private String empName;
    private String empImg;
    private String deptName;
    private BigDecimal income;

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

    public String getEmpImg() {
        return empImg;
    }

    public void setEmpImg(String empImg) {
        this.empImg = empImg;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public BigDecimal getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(BigDecimal totalProfit) {
        this.totalProfit = totalProfit;
    }

    public BigDecimal getMoonProfit() {
        return moonProfit;
    }

    public void setMoonProfit(BigDecimal moonProfit) {
        this.moonProfit = moonProfit;
    }

    public Integer getHouseCount() {
        return houseCount;
    }

    public void setHouseCount(Integer houseCount) {
        this.houseCount = houseCount;
    }

    public Integer getCusCount() {
        return cusCount;
    }

    public void setCusCount(Integer cusCount) {
        this.cusCount = cusCount;
    }

    public Integer getUnionCount() {
        return unionCount;
    }

    public void setUnionCount(Integer unionCount) {
        this.unionCount = unionCount;
    }

    public Integer getFollowupCount() {
        return followupCount;
    }

    public void setFollowupCount(Integer followupCount) {
        this.followupCount = followupCount;
    }
}
