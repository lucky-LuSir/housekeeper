package com.kfwy.hkp.bi.count.entity;

import com.gniuu.framework.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * Create By lzp on 2019/7/22
 */
public class SupportAchievementsEntity extends BaseEntity {

    private String supportCode;
    //月份 yyyy-mm
    private String inMonth;
    //客户数
    private Integer customerNum;
    //房源数
    private Integer houseNum;
    //带看数
    private Integer takeLookNum;
    //成交数
    private Integer dealNum;
    //分成业绩
    private BigDecimal achievements;

    public String getSupportCode() {
        return supportCode;
    }

    public void setSupportCode(String supportCode) {
        this.supportCode = supportCode;
    }

    public String getInMonth() {
        return inMonth;
    }

    public void setInMonth(String inMonth) {
        this.inMonth = inMonth;
    }

    public Integer getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(Integer customerNum) {
        this.customerNum = customerNum;
    }

    public Integer getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(Integer houseNum) {
        this.houseNum = houseNum;
    }

    public Integer getTakeLookNum() {
        return takeLookNum;
    }

    public void setTakeLookNum(Integer takeLookNum) {
        this.takeLookNum = takeLookNum;
    }

    public Integer getDealNum() {
        return dealNum;
    }

    public void setDealNum(Integer dealNum) {
        this.dealNum = dealNum;
    }

    public BigDecimal getAchievements() {
        return achievements;
    }

    public void setAchievements(BigDecimal achievements) {
        this.achievements = achievements;
    }
}
