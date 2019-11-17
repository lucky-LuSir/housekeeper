package com.kfwy.hkp.bi.count.entity;

import com.gniuu.framework.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * @Auther: HJH
 * @Date: 2019/6/15
 * @Description: TODO
 */
public class NewPersonOrderEntity extends BaseEntity {

    // 姓名
    private String userName;

    // 所属部门
    private String deptName;

    // 入职时长
    private String entryTime;

    // 入职时长
    private String quitTime;

    // 入职到开单时间
    private String entryReachOrderTime;

    // 开单数量
    private Integer orderCount;

    // 开单金额
    private BigDecimal orderMoney;

    // 分成金额
    private BigDecimal divideMoney;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getQuitTime() {
        return quitTime;
    }

    public void setQuitTime(String quitTime) {
        this.quitTime = quitTime;
    }

    public String getEntryReachOrderTime() {
        return entryReachOrderTime;
    }

    public void setEntryReachOrderTime(String entryReachOrderTime) {
        this.entryReachOrderTime = entryReachOrderTime;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public BigDecimal getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(BigDecimal orderMoney) {
        this.orderMoney = orderMoney;
    }

    public BigDecimal getDivideMoney() {
        return divideMoney;
    }

    public void setDivideMoney(BigDecimal divideMoney) {
        this.divideMoney = divideMoney;
    }
}
