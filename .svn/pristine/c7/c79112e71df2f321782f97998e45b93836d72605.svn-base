package com.kfwy.hkp.trade.wage.entity;


import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.hkp.trade.wage.enums.CommissionType;
import com.kfwy.hkp.trade.wage.enums.WageType;

import java.math.BigDecimal;
import java.util.Date;


/**
 *提成金额明细(开票扣款明细)
 * @author lzp
 * @date 2019/03/20
 */
public class CommissionWageEntity extends BaseEntity {

    /**
     * 提成金额编号
     */
    private String commissionWageCode;
    /**
     * 提成比例编号
     */
    private String commissionRatioCode;
    /**
     * 分成人编码
     */
    private String peUserCode;
    /**
     * 订单编号
     */
    private String orderCode;
    /**
     * 分成编号
     */
    private String peOrderCode;
    /**
     * 分成比例
     * 做字段冗余，存在回款后修改订单分成
     *
     */
    private BigDecimal percentage;
    /**
     * 回款编码/开票编码
     */
    private String payBackOrderCode;
    /**
     * 回款金额/税额
     */
    private BigDecimal payBackAmount;
    /**
     * 回款业绩
     */
    private BigDecimal payBackAchievements;
    /**
     * 提成类型
     */
    private String commissionType;
    /**
     * 提成类型
     */
    private String commissionTypeName;
    /**
     * 提成比例
     */
    private BigDecimal commissionRatio;
    /**
     * 提成金额
     */
    private BigDecimal commissionWage;
    /**
     * 提成受益人
     */
    private String userCode;
    /**
     * 提成受益人
     */
    private String userName;
    private Date payBackTime;

    private Boolean hasChanged;

    /**
     * 类型  1为回款  2为开票
     */
    private String wageType;

    private String wageTypeName;

    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }

    public String getWageTypeName() {
        if (wageTypeName == null && wageType != null) {
            wageTypeName = DictionaryStorage.get(WageType.class.getName(), wageType).getName();
        }
        return wageTypeName;
    }

    public void setWageTypeName(String wageTypeName) {
        this.wageTypeName = wageTypeName;
    }
    public String getWageType() {
        return wageType;
    }

    public void setWageType(String wageType) {
        this.wageType = wageType;
    }

    public Boolean getHasChanged() {
        return hasChanged;
    }

    public void setHasChanged(Boolean hasChanged) {
        this.hasChanged = hasChanged;
    }

    public String getPeUserCode() {
        return peUserCode;
    }

    public void setPeUserCode(String peUserCode) {
        this.peUserCode = peUserCode;
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

    public Date getPayBackTime() {
        return payBackTime;
    }

    public void setPayBackTime(Date payBackTime) {
        this.payBackTime = payBackTime;
    }

    public String getCommissionWageCode() {
        return commissionWageCode;
    }

    public void setCommissionWageCode(String commissionWageCode) {
        this.commissionWageCode = commissionWageCode;
    }

    public String getCommissionRatioCode() {
        return commissionRatioCode;
    }

    public void setCommissionRatioCode(String commissionRatioCode) {
        this.commissionRatioCode = commissionRatioCode;
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



    public BigDecimal getCommissionRatio() {
        return commissionRatio;
    }

    public void setCommissionRatio(BigDecimal commissionRatio) {
        this.commissionRatio = commissionRatio;
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

    public String getPayBackOrderCode() {
        return payBackOrderCode;
    }

    public void setPayBackOrderCode(String payBackOrderCode) {
        this.payBackOrderCode = payBackOrderCode;
    }

    public BigDecimal getCommissionWage() {
        return commissionWage;
    }

    public void setCommissionWage(BigDecimal commissionWage) {
        this.commissionWage = commissionWage;
    }
}
