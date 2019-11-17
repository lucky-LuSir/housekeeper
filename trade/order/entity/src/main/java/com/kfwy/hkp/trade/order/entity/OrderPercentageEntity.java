package com.kfwy.hkp.trade.order.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;
import com.kfwy.hkp.trade.order.enums.MoneyPercentType;

/**
 * Created by zjp on 2018/8/15.
 */
public class OrderPercentageEntity extends BaseEntity {

    /**
     * 分成编号
     */
    protected String percentageCode;
    /**
     * 订单编号
     */
    protected String orderCode;
    /**
     * 订单日期
     */
    protected Date orderTime;
    /**
     * 分成人编码
     */
    protected String peEmpCode;
    /**
     * 分成人工号
     */
    protected String peEmpNumber;
    /**
     * 分成人名字
     */
    protected String peEmpName;
    /**
     * 分成人部门编码
     */
    protected String peDeptCode;
    /**
     * 分成人部门名字
     */
    protected String peDeptName;
    /**
     *分成名字
     */
    protected String percentageName;
    /**
     * 分成类型
     */
    protected String percentageType;

    /**
     * 金额分成类型  1,客服固定分成  2,分成人分成
     * @see MoneyPercentType
     */
    protected String moneyPercentType;

    /**
     * 金额分成类型(中文名)
     */
    protected String moneyPercentTypeName;
    /**
     * 分成百分比
     */
    protected BigDecimal percentage;
    /**
     * 分成业绩
     */
    protected BigDecimal profit;

    protected String workNumber;

    public String getPeEmpNumber() {
        return peEmpNumber;
    }

    public void setPeEmpNumber(String peEmpNumber) {
        this.peEmpNumber = peEmpNumber;
    }

    public String getPercentageCode() {
        return percentageCode;
    }

    public void setPercentageCode(String percentageCode) {
        this.percentageCode = percentageCode;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getPeEmpCode() {
        return peEmpCode;
    }

    public void setPeEmpCode(String peEmpCode) {
        this.peEmpCode = peEmpCode;
    }

    public String getPeEmpName() {
        return peEmpName;
    }

    public void setPeEmpName(String peEmpName) {
        this.peEmpName = peEmpName;
    }

    public String getPeDeptCode() {
        return peDeptCode;
    }

    public void setPeDeptCode(String peDeptCode) {
        this.peDeptCode = peDeptCode;
    }

    public String getPeDeptName() {
        return peDeptName;
    }

    public void setPeDeptName(String peDeptName) {
        this.peDeptName = peDeptName;
    }

    public String getPercentageName() {
        return percentageName;
    }

    public void setPercentageName(String percentageName) {
        this.percentageName = percentageName;
    }

    public String getPercentageType() {
        return percentageType;
    }

    public void setPercentageType(String percentageType) {
        this.percentageType = percentageType;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public String getMoneyPercentType() {
        return moneyPercentType;
    }

    public void setMoneyPercentType(String moneyPercentType) {
        this.moneyPercentType = moneyPercentType;
    }

    public String getMoneyPercentTypeName() {
        if (moneyPercentTypeName == null && moneyPercentType != null) {
            moneyPercentTypeName = DictionaryStorage.get(MoneyPercentType.class.getName(), moneyPercentType).getName();
        }

        return moneyPercentTypeName;
    }

    public void setMoneyPercentTypeName(String moneyPercentTypeName) {
        this.moneyPercentTypeName = moneyPercentTypeName;
    }

    public String getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(String workNumber) {
        this.workNumber = workNumber;
    }
}
