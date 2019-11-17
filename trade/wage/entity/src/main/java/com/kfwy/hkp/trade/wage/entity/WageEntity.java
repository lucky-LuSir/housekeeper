package com.kfwy.hkp.trade.wage.entity;



import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.hkp.trade.wage.enums.CommissionType;
import com.kfwy.hkp.trade.wage.enums.WageType;

import java.math.BigDecimal;
import java.util.Date;


/**
 *
 * @author lzp
 * @date 2019/4/2
 */
public class WageEntity extends BaseEntity {


    private String userCode;
    private String userName;
    private String deptCode;
    private String deptName;
    private String postName;
    private String postCode;
    private Date orderTime;
    private BigDecimal wage;
    private String workNumber;
    private String inserviceTime;
    private BigDecimal payBackAmount;
    private BigDecimal payBackAchievements;
    private BigDecimal realPayBackAchievements;
    private String orderCode;
    private String peOrderCode;
    private String payBackCode;
    private String commissionWageCode;
    private String commissionRatioCode;
    private Date payBackTime;
    private String commissionType;
    private String commissionTypeName;
    private Boolean hasChanged;
    private String wageType;
    private Date quitTime;
    private Date entryTime;
    private String groupName;
    /**
     * 是否结算  开票订单所在月未回款 且订单状态不是回款中和已回款的不进行汇总计算
     * 该字段数据库进行默认true处理,  月初job进行统一判断修改
     */
    private Boolean settlement;

    public Boolean getSettlement() {
        return settlement;
    }

    public void setSettlement(Boolean settlement) {
        this.settlement = settlement;
    }

    public BigDecimal getRealPayBackAchievements() {
        return realPayBackAchievements;
    }

    public void setRealPayBackAchievements(BigDecimal realPayBackAchievements) {
        this.realPayBackAchievements = realPayBackAchievements;
    }

    private BigDecimal percentage;

    private BigDecimal commissionRatio;

    private String wageTypeName;

    public String getWageTypeName() {
        if (wageTypeName == null && wageType != null) {
            wageTypeName = DictionaryStorage.get(WageType.class.getName(), wageType).getName();
        }
        return wageTypeName;
    }

    public void setWageTypeName(String wageTypeName) {
        this.wageTypeName = wageTypeName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getQuitTime() {
        return quitTime;
    }

    public void setQuitTime(Date quitTime) {
        this.quitTime = quitTime;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }



    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getInserviceTime() {
        return inserviceTime;
    }

    public void setInserviceTime(String inserviceTime) {
        this.inserviceTime = inserviceTime;
    }

    public BigDecimal getPayBackAmount() {
        return payBackAmount;
    }

    public void setPayBackAmount(BigDecimal payBackAmount) {
        this.payBackAmount = payBackAmount;
    }

    public BigDecimal getPayBackAchievements() {
        return payBackAchievements;
    }

    public void setPayBackAchievements(BigDecimal payBackAchievements) {
        this.payBackAchievements = payBackAchievements;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getPeOrderCode() {
        return peOrderCode;
    }

    public void setPeOrderCode(String peOrderCode) {
        this.peOrderCode = peOrderCode;
    }

    public String getPayBackCode() {
        return payBackCode;
    }

    public void setPayBackCode(String payBackCode) {
        this.payBackCode = payBackCode;
    }

    public String getCommissionRatioCode() {
        return commissionRatioCode;
    }

    public void setCommissionRatioCode(String commissionRatioCode) {
        this.commissionRatioCode = commissionRatioCode;
    }

    public Date getPayBackTime() {
        return payBackTime;
    }

    public void setPayBackTime(Date payBackTime) {
        this.payBackTime = payBackTime;
    }

    public String getCommissionType() {
        return commissionType;
    }

    public void setCommissionType(String commissionType) {
        this.commissionType = commissionType;
    }

    public String getCommissionTypeName() {

        if (commissionTypeName == null && commissionType != null) {
            commissionTypeName = DictionaryStorage.get(CommissionType.class.getName(), commissionType).getName();
        }

        return commissionTypeName;
    }

    public void setCommissionTypeName(String commissionType) {
        this.commissionTypeName = commissionType;
    }



    public Boolean getHasChanged() {
        return hasChanged;
    }

    public void setHasChanged(Boolean hasChanged) {
        this.hasChanged = hasChanged;
    }


    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }

    public BigDecimal getCommissionRatio() {
        return commissionRatio;
    }

    public void setCommissionRatio(BigDecimal commissionRatio) {
        this.commissionRatio = commissionRatio;
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

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }


    public String getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(String workNumber) {
        this.workNumber = workNumber;
    }

    public BigDecimal getWage() {
        return wage;
    }

    public void setWage(BigDecimal wage) {
        this.wage = wage;
    }

    public String getCommissionWageCode() {
        return commissionWageCode;
    }

    public void setCommissionWageCode(String commissionWageCode) {
        this.commissionWageCode = commissionWageCode;
    }

    public String getWageType() {
        return wageType;
    }

    public void setWageType(String wageType) {
        this.wageType = wageType;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
