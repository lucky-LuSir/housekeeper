package com.kfwy.hkp.bi.count.dto;

import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.hkp.trade.order.entity.OrderInvoiceEntity;
import com.kfwy.hkp.trade.order.entity.OrderPaybackEntity;
import com.kfwy.hkp.trade.order.entity.OrderPercentageEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 财务版工资报表
 */
public class FinanceWagesReportDto extends BaseEntity {
    /**
     * 订单编号
     */
    private String orderCode;
    /**
     * 订单状态
     */
    private String orderStatus;
    /**
     * 签单日期
     */
    private Date orderTime;
    /**
     * 成交支付协议服务费（元）
     */
    private BigDecimal receivableCost;
    /**
     * '客户合同佣金'
     */
    private BigDecimal receivableCus;
    /**
     * '业主合同佣金'
     */
    private BigDecimal receivableHos;
    /**
     * 回款说明
     */
    private String paybackDescription;
    /**
     * 回款时间
     */
    private String payBackTime;
    /**
     * 实际从客户处回款金额(所有回款之和)
     */
    private BigDecimal sumPayment;
    /**
     * 返佣
     */
    private BigDecimal backMoney;
    /**
     * 实际返佣
     */
    private BigDecimal realBackMoney;
    /**
     * 实际返佣时间
     */
    private Date realBackTime;
    /**
     * 返佣给客户
     */
    private BigDecimal cusRebate;
    /**
     * 返佣给业主
     */
    private BigDecimal ownerRebate;

    /**
     * 开票税金
     */
    private BigDecimal sumTax;
    /**
     * 实收佣金
     */
    private BigDecimal actCommission;
    /**
     * 剔除后实际业绩(实收佣金-开票税金) //财务版不减税金
     */
    private BigDecimal comeCommission;
    /**
     * 成交所属部门
     */
    private String cDeptName;



    private String cDeptCode;

    private String clerkCode;



    /**
     * 部门领导
     */
    private String cLeaderName;
    /**
     * 部门领导工号
     */
    private String cLeaderNumber;
    /**
     * 开单人员姓名
     */
    private String clerkName;
    /**
     * 开单工号
     */
    private String clerkNumber;
    /**
     * 开单人员部门
     */
    private String clerkDeptName;
    /**
     *分成所属部门
     */
    private String peDeptName;
    /**
     * 开单人员提成比例
     */
    private BigDecimal percentage;
    /**
     * 开单人员提成金额
     */
    private BigDecimal profit;
    /**
     *开单人员业绩
     */
    private BigDecimal performance;
    /**
     * 业务员姓名
     */
    private String clerkName1;
    /**
     * 业务员提成比例
     */
    private BigDecimal percentage1;
    /**
     * 业务员提成金额
     */
    private BigDecimal profit1;
    /**
     * 工号
     */
    private String clerkNumber1;
    /**
     * 开单人员部门
     */
    private String clerkDeptName1;
    /**
     *分成所属部门
     */
    private String peDeptName1;
    /**
     * 业务员业绩
     */
    private BigDecimal performance1;
    /**
     * 业务员姓名
     */
    private String clerkName2;
    /**
     * 工号
     */
    private String clerkNumber2;
    /**
     * 开单人员部门
     */
    private String clerkDeptName2;
    /**
     *分成所属部门
     */
    private String peDeptName2;
    /**
     * 业务员提成比例
     */
    private BigDecimal percentage2;
    /**
     * 业务员提成金额
     */
    private BigDecimal profit2;
    /**
     * 业务员业绩
     */
    private BigDecimal performance2;
    /**
     * 业务员姓名
     */
    private String clerkName3;
    /**
     * 工号
     */
    private String clerkNumber3;
    /**
     * 开单人员部门
     */
    private String clerkDeptName3;
    /**
     *分成所属部门
     */
    private String peDeptName3;
    /**
     * 业务员提成比例
     */
    private BigDecimal percentage3;
    /**
     * 业务员提成金额
     */
    private BigDecimal profit3;
    /**
     * 业务员业绩
     */
    private BigDecimal performance3;
    /**
     * 业务员姓名
     */
    private String clerkName4;
    /**
     * 工号
     */
    private String clerkNumber4;
    /**
     * 开单人员部门
     */
    private String clerkDeptName4;
    /**
     *分成所属部门
     */
    private String peDeptName4;
    /**
     * 业务员提成比例
     */
    private BigDecimal percentage4;
    /**
     * 业务员提成金额
     */
    private BigDecimal profit4;
    /**
     * 业务员业绩
     */
    private BigDecimal performance4;
    /**
     * 业务员姓名
     */
    private String clerkName5;
    /**
     * 工号
     */
    private String clerkNumber5;
    /**
     * 开单人员部门
     */
    private String clerkDeptName5;
    /**
     *分成所属部门
     */
    private String peDeptName5;
    /**
     * 业务员提成比例
     */
    private BigDecimal percentage5;
    /**
     * 业务员提成金额
     */
    private BigDecimal profit5;
    /**
     * 业务员业绩
     */
    private BigDecimal performance5;
    /**
     * 分成人员编码
     */
    private String clerkCode1;
    private String clerkCode2;
    private String clerkCode3;
    private String clerkCode4;
    private String clerkCode5;

    /**
     *分成所属部门
     */
    private String peDeptCode;
    /**
     *分成所属部门
     */
    private String peDeptCode1;
    /**
     *分成所属部门
     */
    private String peDeptCode2;
    /**
     *分成所属部门
     */
    private String peDeptCode3;
    /**
     *分成所属部门
     */
    private String peDeptCode4;
    /**
     *分成所属部门
     */
    private String peDeptCode5;

    //客服分成人信息
    private String cusSerName;//姓名
    private String cusSerNumber;//工号
    private String cusSerDeptName;//部门
    private String cusSerPeDeptName;//分成所属部门
    private BigDecimal cusSerPercentage;//比例
    private BigDecimal cusSerPerformance;//金额
    private String cusSerCode;//编码
    private String cusSerPeDeptCode;//分成部门编码

    private String cusSerName1;
    private String cusSerNumber1;
    private String cusSerDeptName1;
    private String cusSerPeDeptName1;
    private BigDecimal cusSerPercentage1;
    private BigDecimal cusSerPerformance1;
    private String cusSerCode1;
    private String cusSerPeDeptCode1;

    //活动经费信息
    private String actName;
    private String actNumber;
    private String actDeptName;
    private String actPeDeptName;
    private BigDecimal actPercentage;
    private BigDecimal actPerformance;
    private String actCode;
    private String actPeDeptCode;

    //公司获客分成信息
    private String focusCusName;
    private String focusCusNumber;
    private String focusCusDeptName;
    private String focusCusPeDeptName;
    private BigDecimal focusCusPercentage;
    private BigDecimal focusCusPerformance;
    private String focusCusCode;
    private String focusCusPeDeptCode;

    public String getcDeptName() {
        return cDeptName;
    }

    public void setcDeptName(String cDeptName) {
        this.cDeptName = cDeptName;
    }

    public String getcLeaderName() {
        return cLeaderName;
    }

    public void setcLeaderName(String cLeaderName) {
        this.cLeaderName = cLeaderName;
    }

    public String getcLeaderNumber() {
        return cLeaderNumber;
    }

    public void setcLeaderNumber(String cLeaderNumber) {
        this.cLeaderNumber = cLeaderNumber;
    }

    public String getCusSerName() {
        return cusSerName;
    }

    public void setCusSerName(String cusSerName) {
        this.cusSerName = cusSerName;
    }

    public String getCusSerNumber() {
        return cusSerNumber;
    }

    public void setCusSerNumber(String cusSerNumber) {
        this.cusSerNumber = cusSerNumber;
    }

    public String getCusSerDeptName() {
        return cusSerDeptName;
    }

    public void setCusSerDeptName(String cusSerDeptName) {
        this.cusSerDeptName = cusSerDeptName;
    }

    public String getCusSerPeDeptName() {
        return cusSerPeDeptName;
    }

    public void setCusSerPeDeptName(String cusSerPeDeptName) {
        this.cusSerPeDeptName = cusSerPeDeptName;
    }

    public BigDecimal getCusSerPercentage() {
        return cusSerPercentage;
    }

    public void setCusSerPercentage(BigDecimal cusSerPercentage) {
        this.cusSerPercentage = cusSerPercentage;
    }

    public BigDecimal getCusSerPerformance() {
        return cusSerPerformance;
    }

    public void setCusSerPerformance(BigDecimal cusSerPerformance) {
        this.cusSerPerformance = cusSerPerformance;
    }

    public String getCusSerCode() {
        return cusSerCode;
    }

    public void setCusSerCode(String cusSerCode) {
        this.cusSerCode = cusSerCode;
    }

    public String getCusSerPeDeptCode() {
        return cusSerPeDeptCode;
    }

    public void setCusSerPeDeptCode(String cusSerPeDeptCode) {
        this.cusSerPeDeptCode = cusSerPeDeptCode;
    }

    public String getCusSerName1() {
        return cusSerName1;
    }

    public void setCusSerName1(String cusSerName1) {
        this.cusSerName1 = cusSerName1;
    }

    public String getCusSerNumber1() {
        return cusSerNumber1;
    }

    public void setCusSerNumber1(String cusSerNumber1) {
        this.cusSerNumber1 = cusSerNumber1;
    }

    public String getCusSerDeptName1() {
        return cusSerDeptName1;
    }

    public void setCusSerDeptName1(String cusSerDeptName1) {
        this.cusSerDeptName1 = cusSerDeptName1;
    }

    public String getCusSerPeDeptName1() {
        return cusSerPeDeptName1;
    }

    public void setCusSerPeDeptName1(String cusSerPeDeptName1) {
        this.cusSerPeDeptName1 = cusSerPeDeptName1;
    }

    public BigDecimal getCusSerPercentage1() {
        return cusSerPercentage1;
    }

    public void setCusSerPercentage1(BigDecimal cusSerPercentage1) {
        this.cusSerPercentage1 = cusSerPercentage1;
    }

    public BigDecimal getCusSerPerformance1() {
        return cusSerPerformance1;
    }

    public void setCusSerPerformance1(BigDecimal cusSerPerformance1) {
        this.cusSerPerformance1 = cusSerPerformance1;
    }

    public String getCusSerCode1() {
        return cusSerCode1;
    }

    public void setCusSerCode1(String cusSerCode1) {
        this.cusSerCode1 = cusSerCode1;
    }

    public String getCusSerPeDeptCode1() {
        return cusSerPeDeptCode1;
    }

    public void setCusSerPeDeptCode1(String cusSerPeDeptCode1) {
        this.cusSerPeDeptCode1 = cusSerPeDeptCode1;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public String getActNumber() {
        return actNumber;
    }

    public void setActNumber(String actNumber) {
        this.actNumber = actNumber;
    }

    public String getActDeptName() {
        return actDeptName;
    }

    public void setActDeptName(String actDeptName) {
        this.actDeptName = actDeptName;
    }

    public String getActPeDeptName() {
        return actPeDeptName;
    }

    public void setActPeDeptName(String actPeDeptName) {
        this.actPeDeptName = actPeDeptName;
    }

    public BigDecimal getActPercentage() {
        return actPercentage;
    }

    public void setActPercentage(BigDecimal actPercentage) {
        this.actPercentage = actPercentage;
    }

    public BigDecimal getActPerformance() {
        return actPerformance;
    }

    public void setActPerformance(BigDecimal actPerformance) {
        this.actPerformance = actPerformance;
    }

    public String getActCode() {
        return actCode;
    }

    public void setActCode(String actCode) {
        this.actCode = actCode;
    }

    public String getActPeDeptCode() {
        return actPeDeptCode;
    }

    public void setActPeDeptCode(String actPeDeptCode) {
        this.actPeDeptCode = actPeDeptCode;
    }

    public String getFocusCusName() {
        return focusCusName;
    }

    public void setFocusCusName(String focusCusName) {
        this.focusCusName = focusCusName;
    }

    public String getFocusCusNumber() {
        return focusCusNumber;
    }

    public void setFocusCusNumber(String focusCusNumber) {
        this.focusCusNumber = focusCusNumber;
    }

    public String getFocusCusDeptName() {
        return focusCusDeptName;
    }

    public void setFocusCusDeptName(String focusCusDeptName) {
        this.focusCusDeptName = focusCusDeptName;
    }

    public String getFocusCusPeDeptName() {
        return focusCusPeDeptName;
    }

    public void setFocusCusPeDeptName(String focusCusPeDeptName) {
        this.focusCusPeDeptName = focusCusPeDeptName;
    }

    public BigDecimal getFocusCusPercentage() {
        return focusCusPercentage;
    }

    public void setFocusCusPercentage(BigDecimal focusCusPercentage) {
        this.focusCusPercentage = focusCusPercentage;
    }

    public BigDecimal getFocusCusPerformance() {
        return focusCusPerformance;
    }

    public void setFocusCusPerformance(BigDecimal focusCusPerformance) {
        this.focusCusPerformance = focusCusPerformance;
    }

    public String getFocusCusCode() {
        return focusCusCode;
    }

    public void setFocusCusCode(String focusCusCode) {
        this.focusCusCode = focusCusCode;
    }

    public String getFocusCusPeDeptCode() {
        return focusCusPeDeptCode;
    }

    public void setFocusCusPeDeptCode(String focusCusPeDeptCode) {
        this.focusCusPeDeptCode = focusCusPeDeptCode;
    }

    public String getPeDeptName() {
        return peDeptName;
    }


    public String getPeDeptCode() {
        return peDeptCode;
    }

    public void setPeDeptCode(String peDeptCode) {
        this.peDeptCode = peDeptCode;
    }

    public String getPeDeptCode1() {
        return peDeptCode1;
    }

    public void setPeDeptCode1(String peDeptCode1) {
        this.peDeptCode1 = peDeptCode1;
    }

    public String getPeDeptCode2() {
        return peDeptCode2;
    }

    public void setPeDeptCode2(String peDeptCode2) {
        this.peDeptCode2 = peDeptCode2;
    }

    public String getPeDeptCode3() {
        return peDeptCode3;
    }

    public void setPeDeptCode3(String peDeptCode3) {
        this.peDeptCode3 = peDeptCode3;
    }

    public String getPeDeptCode4() {
        return peDeptCode4;
    }

    public void setPeDeptCode4(String peDeptCode4) {
        this.peDeptCode4 = peDeptCode4;
    }

    public String getPeDeptCode5() {
        return peDeptCode5;
    }

    public void setPeDeptCode5(String peDeptCode5) {
        this.peDeptCode5 = peDeptCode5;
    }

    public void setPeDeptName(String peDeptName) {
        this.peDeptName = peDeptName;
    }

    public String getPeDeptName1() {
        return peDeptName1;
    }

    public void setPeDeptName1(String peDeptName1) {
        this.peDeptName1 = peDeptName1;
    }

    public String getPeDeptName2() {
        return peDeptName2;
    }

    public void setPeDeptName2(String peDeptName2) {
        this.peDeptName2 = peDeptName2;
    }

    public String getPeDeptName3() {
        return peDeptName3;
    }

    public void setPeDeptName3(String peDeptName3) {
        this.peDeptName3 = peDeptName3;
    }

    public String getPeDeptName4() {
        return peDeptName4;
    }

    public void setPeDeptName4(String peDeptName4) {
        this.peDeptName4 = peDeptName4;
    }

    public String getPeDeptName5() {
        return peDeptName5;
    }

    public void setPeDeptName5(String peDeptName5) {
        this.peDeptName5 = peDeptName5;
    }

    public String getClerkDeptName() {
        return clerkDeptName;
    }

    public void setClerkDeptName(String clerkDeptName) {
        this.clerkDeptName = clerkDeptName;
    }

    public String getClerkDeptName1() {
        return clerkDeptName1;
    }

    public void setClerkDeptName1(String clerkDeptName1) {
        this.clerkDeptName1 = clerkDeptName1;
    }

    public String getClerkDeptName2() {
        return clerkDeptName2;
    }

    public void setClerkDeptName2(String clerkDeptName2) {
        this.clerkDeptName2 = clerkDeptName2;
    }

    public String getClerkDeptName3() {
        return clerkDeptName3;
    }

    public void setClerkDeptName3(String clerkDeptName3) {
        this.clerkDeptName3 = clerkDeptName3;
    }

    public String getClerkDeptName4() {
        return clerkDeptName4;
    }

    public void setClerkDeptName4(String clerkDeptName4) {
        this.clerkDeptName4 = clerkDeptName4;
    }

    public String getClerkDeptName5() {
        return clerkDeptName5;
    }

    public void setClerkDeptName5(String clerkDeptName5) {
        this.clerkDeptName5 = clerkDeptName5;
    }

    public String getClerkCode1() {
        return clerkCode1;
    }

    public void setClerkCode1(String clerkCode1) {
        this.clerkCode1 = clerkCode1;
    }

    public String getClerkCode2() {
        return clerkCode2;
    }

    public void setClerkCode2(String clerkCode2) {
        this.clerkCode2 = clerkCode2;
    }

    public String getClerkCode3() {
        return clerkCode3;
    }

    public void setClerkCode3(String clerkCode3) {
        this.clerkCode3 = clerkCode3;
    }

    public String getClerkCode4() {
        return clerkCode4;
    }

    public void setClerkCode4(String clerkCode4) {
        this.clerkCode4 = clerkCode4;
    }

    public String getClerkCode5() {
        return clerkCode5;
    }

    public void setClerkCode5(String clerkCode5) {
        this.clerkCode5 = clerkCode5;
    }

    /**
     * 回款信息
     */
    private List<OrderPaybackEntity> orderPaybackList;
    /**
     * 发票信息
     */
    private List<OrderInvoiceEntity> orderInvoiceList;
    /**
     * 提成业务员信息
     */
    private List<OrderPercentageEntity> orderPercentageList;
    /**
     * 回款描述
     */
    private String payDescription;
    /**
     *回款金额
     */
    private BigDecimal payPayment;
    /**
     * 订单业绩大区名称
     */
    private String parentDeptName;
    /**
     * 客户兼职分钱
     */
    private BigDecimal cusPartTimeMoney;
    /**
     * 业主兼职分钱
     */
    private BigDecimal ownerPartTimeMoney;


    public String getcDeptCode() {
        return cDeptCode;
    }

    public void setcDeptCode(String cDeptCode) {
        this.cDeptCode = cDeptCode;
    }
    public String getClerkCode() {
        return clerkCode;
    }

    public void setClerkCode(String clerkCode) {
        this.clerkCode = clerkCode;
    }
    public BigDecimal getCusPartTimeMoney() {
        return cusPartTimeMoney;
    }

    public void setCusPartTimeMoney(BigDecimal cusPartTimeMoney) {
        this.cusPartTimeMoney = cusPartTimeMoney;
    }
    public BigDecimal getCusRebate() {
        return cusRebate;
    }

    public void setCusRebate(BigDecimal cusRebate) {
        this.cusRebate = cusRebate;
    }

    public BigDecimal getOwnerRebate() {
        return ownerRebate;
    }

    public void setOwnerRebate(BigDecimal ownerRebate) {
        this.ownerRebate = ownerRebate;
    }
    public List<OrderPaybackEntity> getOrderPaybackList() {
        return orderPaybackList;
    }

    public void setOrderPaybackList(List<OrderPaybackEntity> orderPaybackList) {
        this.orderPaybackList = orderPaybackList;
    }

    public String getPayBackTime() {
        return payBackTime;
    }

    public void setPayBackTime(String payBackTime) {
        this.payBackTime = payBackTime;
    }

    public String getPayDescription() {
        return payDescription;
    }

    public void setPayDescription(String payDescription) {
        this.payDescription = payDescription;
    }

    public BigDecimal getPayPayment() {
        return payPayment;
    }

    public void setPayPayment(BigDecimal payPayment) {
        this.payPayment = payPayment;
    }

    public String getCLeaderNumber() {
        return cLeaderNumber;
    }

    public String getClerkNumber() {
        return clerkNumber;
    }

    public String getClerkNumber1() {
        return clerkNumber1;
    }

    public String getClerkNumber2() {
        return clerkNumber2;
    }

    public String getClerkNumber3() {
        return clerkNumber3;
    }

    public String getClerkNumber4() {
        return clerkNumber4;
    }

    public String getClerkNumber5() {
        return clerkNumber5;
    }

    public void setCLeaderNumber(String cLeaderNumber) {
        this.cLeaderNumber = cLeaderNumber;
    }

    public void setClerkNumber(String clerkNumber) {
        this.clerkNumber = clerkNumber;
    }

    public void setClerkNumber1(String clerkNumber1) {
        this.clerkNumber1 = clerkNumber1;
    }

    public void setClerkNumber2(String clerkNumber2) {
        this.clerkNumber2 = clerkNumber2;
    }

    public void setClerkNumber3(String clerkNumber3) {
        this.clerkNumber3 = clerkNumber3;
    }

    public void setClerkNumber4(String clerkNumber4) {
        this.clerkNumber4 = clerkNumber4;
    }

    public void setClerkNumber5(String clerkNumber5) {
        this.clerkNumber5 = clerkNumber5;
    }

    public BigDecimal getOwnerPartTimeMoney() {
        return ownerPartTimeMoney;
    }

    public void setOwnerPartTimeMoney(BigDecimal ownerPartTimeMoney) {
        this.ownerPartTimeMoney = ownerPartTimeMoney;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public BigDecimal getReceivableCost() {
        return receivableCost;
    }

    public void setReceivableCost(BigDecimal receivableCost) {
        this.receivableCost = receivableCost;
    }

    public BigDecimal getReceivableCus() {
        return receivableCus;
    }

    public void setReceivableCus(BigDecimal receivableCus) {
        this.receivableCus = receivableCus;
    }

    public BigDecimal getReceivableHos() {
        return receivableHos;
    }

    public void setReceivableHos(BigDecimal receivableHos) {
        this.receivableHos = receivableHos;
    }

    public String getPaybackDescription() {
        return paybackDescription;
    }

    public void setPaybackDescription(String paybackDescription) {
        this.paybackDescription = paybackDescription;
    }
    public BigDecimal getSumPayment() {
        return sumPayment;
    }

    public void setSumPayment(BigDecimal sumPayment) {
        this.sumPayment = sumPayment;
    }

    public BigDecimal getBackMoney() {
        return backMoney;
    }

    public void setBackMoney(BigDecimal backMoney) {
        this.backMoney = backMoney;
    }

    public BigDecimal getRealBackMoney() {
        return realBackMoney;
    }

    public void setRealBackMoney(BigDecimal realBackMoney) {
        this.realBackMoney = realBackMoney;
    }

    public Date getRealBackTime() {
        return realBackTime;
    }

    public void setRealBackTime(Date realBackTime) {
        this.realBackTime = realBackTime;
    }

    public BigDecimal getSumTax() {
        return sumTax;
    }

    public void setSumTax(BigDecimal sumTax) {
        this.sumTax = sumTax;
    }

    public BigDecimal getActCommission() {
        return actCommission;
    }

    public void setActCommission(BigDecimal actCommission) {
        this.actCommission = actCommission;
    }

    public BigDecimal getComeCommission() {
        return comeCommission;
    }

    public void setComeCommission(BigDecimal comeCommission) {
        this.comeCommission = comeCommission;
    }

    public String getCDeptName() {
        return cDeptName;
    }

    public void setCDeptName(String cDeptName) {
        this.cDeptName = cDeptName;
    }

    public String getCLeaderName() {
        return cLeaderName;
    }

    public void setCLeaderName(String cLeaderName) {
        this.cLeaderName = cLeaderName;
    }
    public String getClerkName() {
        return clerkName;
    }

    public void setClerkName(String clerkName) {
        this.clerkName = clerkName;
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

    public BigDecimal getPerformance() {
        return performance;
    }

    public void setPerformance(BigDecimal performance) {
        this.performance = performance;
    }

    public String getClerkName1() {
        return clerkName1;
    }

    public void setClerkName1(String clerkName1) {
        this.clerkName1 = clerkName1;
    }

    public BigDecimal getPercentage1() {
        return percentage1;
    }

    public void setPercentage1(BigDecimal percentage1) {
        this.percentage1 = percentage1;
    }

    public BigDecimal getProfit1() {
        return profit1;
    }

    public void setProfit1(BigDecimal profit1) {
        this.profit1 = profit1;
    }

    public BigDecimal getPerformance1() {
        return performance1;
    }

    public void setPerformance1(BigDecimal performance1) {
        this.performance1 = performance1;
    }

    public String getClerkName2() {
        return clerkName2;
    }

    public void setClerkName2(String clerkName2) {
        this.clerkName2 = clerkName2;
    }

    public BigDecimal getPercentage2() {
        return percentage2;
    }

    public void setPercentage2(BigDecimal percentage2) {
        this.percentage2 = percentage2;
    }

    public BigDecimal getProfit2() {
        return profit2;
    }

    public void setProfit2(BigDecimal profit2) {
        this.profit2 = profit2;
    }

    public BigDecimal getPerformance2() {
        return performance2;
    }

    public void setPerformance2(BigDecimal performance2) {
        this.performance2 = performance2;
    }

    public String getClerkName3() {
        return clerkName3;
    }

    public void setClerkName3(String clerkName3) {
        this.clerkName3 = clerkName3;
    }

    public BigDecimal getPercentage3() {
        return percentage3;
    }

    public void setPercentage3(BigDecimal percentage3) {
        this.percentage3 = percentage3;
    }

    public BigDecimal getProfit3() {
        return profit3;
    }

    public void setProfit3(BigDecimal profit3) {
        this.profit3 = profit3;
    }

    public String getClerkName4() {
        return clerkName4;
    }

    public void setClerkName4(String clerkName4) {
        this.clerkName4 = clerkName4;
    }

    public BigDecimal getPercentage4() {
        return percentage4;
    }

    public void setPercentage4(BigDecimal percentage4) {
        this.percentage4 = percentage4;
    }

    public BigDecimal getProfit4() {
        return profit4;
    }

    public void setProfit4(BigDecimal profit4) {
        this.profit4 = profit4;
    }

    public BigDecimal getPerformance4() {
        return performance4;
    }

    public void setPerformance4(BigDecimal performance4) {
        this.performance4 = performance4;
    }

    public String getClerkName5() {
        return clerkName5;
    }

    public void setClerkName5(String clerkName5) {
        this.clerkName5 = clerkName5;
    }

    public BigDecimal getPercentage5() {
        return percentage5;
    }

    public void setPercentage5(BigDecimal percentage5) {
        this.percentage5 = percentage5;
    }

    public BigDecimal getProfit5() {
        return profit5;
    }

    public void setProfit5(BigDecimal profit5) {
        this.profit5 = profit5;
    }

    public BigDecimal getPerformance5() {
        return performance5;
    }

    public void setPerformance5(BigDecimal performance5) {
        this.performance5 = performance5;
    }

    public BigDecimal getPerformance3() {
        return performance3;
    }

    public void setPerformance3(BigDecimal performance3) {
        this.performance3 = performance3;
    }


    public List<OrderInvoiceEntity> getOrderInvoiceList() {
        return orderInvoiceList;
    }

    public void setOrderInvoiceList(List<OrderInvoiceEntity> orderInvoiceList) {
        this.orderInvoiceList = orderInvoiceList;
    }

    public List<OrderPercentageEntity> getOrderPercentageList() {
        return orderPercentageList;
    }

    public void setOrderPercentageList(List<OrderPercentageEntity> orderPercentageList) {
        this.orderPercentageList = orderPercentageList;
    }

    public String getParentDeptName() {
        return parentDeptName;
    }

    public void setParentDeptName(String parentDeptName) {
        this.parentDeptName = parentDeptName;
    }
}
