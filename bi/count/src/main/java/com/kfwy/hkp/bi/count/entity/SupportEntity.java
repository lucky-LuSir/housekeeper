package com.kfwy.hkp.bi.count.entity;

import com.gniuu.framework.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Create By lzp on 2019/7/22
 */
public class SupportEntity extends BaseEntity {

    //访谈编码
    private String supportCode;
    //统计时间
    private Date inTime;
    //待沟通人员
    private String userCode;
    //最后开单时间
    private Date lastOrderTime;
    //访谈日期
    private Date supportTime;
    //访谈人
    private String supportUserCode;
    //原因分析
    private String reason;
    //改善策略
    private String strategy;
    //经理帮扶计划
    private String leaderPlan;
    //需求资源
    private String needResources;
    //近三个月业绩
    private List<SupportAchievementsEntity> supportAchievementsEntities;
    //沟通状态
    private Integer supportStatus;
    //待沟通类型   一个月  两个月 三个月及三个月以上
    private Integer supportType;


    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Date getLastOrderTime() {
        return lastOrderTime;
    }

    public void setLastOrderTime(Date lastOrderTime) {
        this.lastOrderTime = lastOrderTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public String getLeaderPlan() {
        return leaderPlan;
    }

    public void setLeaderPlan(String leaderPlan) {
        this.leaderPlan = leaderPlan;
    }

    public String getNeedResources() {
        return needResources;
    }

    public void setNeedResources(String needResources) {
        this.needResources = needResources;
    }

    public List<SupportAchievementsEntity> getSupportAchievementsEntities() {
        return supportAchievementsEntities;
    }

    public void setSupportAchievementsEntities(List<SupportAchievementsEntity> supportAchievementsEntities) {
        this.supportAchievementsEntities = supportAchievementsEntities;
    }

    public String getSupportCode() {
        return supportCode;
    }

    public void setSupportCode(String supportCode) {
        this.supportCode = supportCode;
    }

    public Date getSupportTime() {
        return supportTime;
    }

    public void setSupportTime(Date supportTime) {
        this.supportTime = supportTime;
    }

    public String getSupportUserCode() {
        return supportUserCode;
    }

    public void setSupportUserCode(String supportUserCode) {
        this.supportUserCode = supportUserCode;
    }

    public Integer getSupportStatus() {
        return supportStatus;
    }

    public void setSupportStatus(Integer supportStatus) {
        this.supportStatus = supportStatus;
    }

    public Integer getSupportType() {
        return supportType;
    }

    public void setSupportType(Integer supportType) {
        this.supportType = supportType;
    }
}
