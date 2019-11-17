package com.kfwy.hkp.bi.count.dto;

import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.hkp.trade.order.entity.OrderInvoiceEntity;
import com.kfwy.hkp.trade.order.entity.OrderPaybackEntity;
import com.kfwy.hkp.trade.order.entity.OrderPercentageEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
public class PersonnelReportDto extends BaseEntity {


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
     * 汇款描述
     */
    private String paybackDescription;
    /**
     * 回款说明
     */
    private String payRemark;
    /**
     * 回款时间
     */
    private Date payBackTime;
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
     * 剔除后实际业绩(实收佣金-开票税金)
     */
    private BigDecimal comeCommission;
    /**
     * 成交所属部门
     */
    private String dealDeptName;



    private String dealDeptCode;

    private String clerkCode;



    /**
     * 部门领导
     */
    private String dealLeaderName;
    /**
     * 部门领导工号
     */
    private String dealLeaderNumber;
    /**
     * 开单人员姓名
     */
    private String clerkName;



    /**
     * 开单人员部门

     */
    private String clerkDeptName;
    /**
     * 开单工号
     */
    private String clerkNumber;
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
     * 业务员1业绩
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
     * 业务员提成比例
     */
    private BigDecimal percentage2;
    /**
     * 业务员提成金额
     */
    private BigDecimal profit2;
    /**
     * 业务员2业绩
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
    /**
     * 开票时间
     */
    private Date invoiceTime;
    /**
     * 发票号
     */
    private String invoiceCode;
    /**
     * 税率
     */
    private Integer invoiceTaxRate;
    /**
     * 发票总额（发票金额+税额）
     */
    private BigDecimal invoiceTotalPrice;
    /**
     * 税额
     */
    private BigDecimal invoiceTax;
    /**
     * 发票金额
     */
    private BigDecimal invoiceUnitAmount;

    private Date invoiceTime1;
    private String invoiceCode1;
    private Integer invoiceTaxRate1;
    private BigDecimal invoiceUnitAmount1;
    private BigDecimal invoiceTax1;
    private BigDecimal invoiceTotalPrice1;

    private Date invoiceTime2;
    private String invoiceCode2;
    private Integer invoiceTaxRate2;
    private BigDecimal invoiceUnitAmount2;
    private BigDecimal invoiceTax2;
    private BigDecimal invoiceTotalPrice2;


    private Date invoiceTime3;
    private String invoiceCode3;
    private Integer invoiceTaxRate3;
    private BigDecimal invoiceUnitAmount3;
    private BigDecimal invoiceTax3;
    private BigDecimal invoiceTotalPrice3;


    private Date invoiceTime4;
    private String invoiceCode4;
    private Integer invoiceTaxRate4;
    private BigDecimal invoiceUnitAmount4;
    private BigDecimal invoiceTax4;
    private BigDecimal invoiceTotalPrice4;


    private Date invoiceTime5;
    private String invoiceCode5;
    private Integer invoiceTaxRate5;
    private BigDecimal invoiceUnitAmount5;
    private BigDecimal invoiceTax5;
    private BigDecimal invoiceTotalPrice5;


    private Date invoiceTime6;
    private String invoiceCode6;
    private Integer invoiceTaxRate6;
    private BigDecimal invoiceUnitAmount6;
    private BigDecimal invoiceTax6;
    private BigDecimal invoiceTotalPrice6;

    private Date invoiceTime7;
    private String invoiceCode7;
    private Integer invoiceTaxRate7;
    private BigDecimal invoiceUnitAmount7;
    private BigDecimal invoiceTax7;
    private BigDecimal invoiceTotalPrice7;

    private String clerkPeDeptName;
    private String clerkPeDeptCode;

    public String getClerkPeDeptName() {
        return clerkPeDeptName;
    }

    public void setClerkPeDeptName(String clerkPeDeptName) {
        this.clerkPeDeptName = clerkPeDeptName;
    }

    public String getClerkPeDeptCode() {
        return clerkPeDeptCode;
    }

    public void setClerkPeDeptCode(String clerkPeDeptCode) {
        this.clerkPeDeptCode = clerkPeDeptCode;
    }

    private String clerkDeptName1;
    private String clerkDeptName2;
    private String clerkDeptName3;
    private String clerkDeptName4;
    private String clerkDeptName5;
    private String clerkPeDeptName1;
    private String clerkPeDeptName2;
    private String clerkPeDeptName3;
    private String clerkPeDeptName4;
    private String clerkPeDeptName5;

    private String clerkPeDeptCode1;
    private String clerkPeDeptCode2;
    private String clerkPeDeptCode3;
    private String clerkPeDeptCode4;
    private String clerkPeDeptCode5;

    public String getClerkPeDeptName1() {
        return clerkPeDeptName1;
    }

    public void setClerkPeDeptName1(String clerkPeDeptName1) {
        this.clerkPeDeptName1 = clerkPeDeptName1;
    }

    public String getClerkPeDeptName2() {
        return clerkPeDeptName2;
    }

    public void setClerkPeDeptName2(String clerkPeDeptName2) {
        this.clerkPeDeptName2 = clerkPeDeptName2;
    }

    public String getClerkPeDeptName3() {
        return clerkPeDeptName3;
    }

    public void setClerkPeDeptName3(String clerkPeDeptName3) {
        this.clerkPeDeptName3 = clerkPeDeptName3;
    }

    public String getClerkPeDeptName4() {
        return clerkPeDeptName4;
    }

    public void setClerkPeDeptName4(String clerkPeDeptName4) {
        this.clerkPeDeptName4 = clerkPeDeptName4;
    }

    public String getClerkPeDeptName5() {
        return clerkPeDeptName5;
    }

    public void setClerkPeDeptName5(String clerkPeDeptName5) {
        this.clerkPeDeptName5 = clerkPeDeptName5;
    }

    public String getClerkPeDeptCode1() {
        return clerkPeDeptCode1;
    }

    public void setClerkPeDeptCode1(String clerkPeDeptCode1) {
        this.clerkPeDeptCode1 = clerkPeDeptCode1;
    }

    public String getClerkPeDeptCode2() {
        return clerkPeDeptCode2;
    }

    public void setClerkPeDeptCode2(String clerkPeDeptCode2) {
        this.clerkPeDeptCode2 = clerkPeDeptCode2;
    }

    public String getClerkPeDeptCode3() {
        return clerkPeDeptCode3;
    }

    public void setClerkPeDeptCode3(String clerkPeDeptCode3) {
        this.clerkPeDeptCode3 = clerkPeDeptCode3;
    }

    public String getClerkPeDeptCode4() {
        return clerkPeDeptCode4;
    }

    public void setClerkPeDeptCode4(String clerkPeDeptCode4) {
        this.clerkPeDeptCode4 = clerkPeDeptCode4;
    }

    public String getClerkPeDeptCode5() {
        return clerkPeDeptCode5;
    }

    public void setClerkPeDeptCode5(String clerkPeDeptCode5) {
        this.clerkPeDeptCode5 = clerkPeDeptCode5;
    }

    public String getPayRemark() {
        return payRemark;
    }

    public void setPayRemark(String payRemark) {
        this.payRemark = payRemark;
    }

    public String getClerkDeptName() {
        return clerkDeptName;
    }

    public void setClerkDeptName(String clerkDeptName) {
        this.clerkDeptName = clerkDeptName;
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

    public Date getPayBackTime() {
        return payBackTime;
    }

    public void setPayBackTime(Date payBackTime) {
        this.payBackTime = payBackTime;
    }

    public BigDecimal getInvoiceTotalPrice() {
        return invoiceTotalPrice;
    }

    public void setInvoiceTotalPrice(BigDecimal invoiceTotalPrice) {
        this.invoiceTotalPrice = invoiceTotalPrice;
    }

    public BigDecimal getInvoiceTotalPrice1() {
        return invoiceTotalPrice1;
    }

    public void setInvoiceTotalPrice1(BigDecimal invoiceTotalPrice1) {
        this.invoiceTotalPrice1 = invoiceTotalPrice1;
    }

    public BigDecimal getInvoiceTotalPrice2() {
        return invoiceTotalPrice2;
    }

    public void setInvoiceTotalPrice2(BigDecimal invoiceTotalPrice2) {
        this.invoiceTotalPrice2 = invoiceTotalPrice2;
    }

    public BigDecimal getInvoiceTotalPrice3() {
        return invoiceTotalPrice3;
    }

    public void setInvoiceTotalPrice3(BigDecimal invoiceTotalPrice3) {
        this.invoiceTotalPrice3 = invoiceTotalPrice3;
    }

    public BigDecimal getInvoiceTotalPrice4() {
        return invoiceTotalPrice4;
    }

    public void setInvoiceTotalPrice4(BigDecimal invoiceTotalPrice4) {
        this.invoiceTotalPrice4 = invoiceTotalPrice4;
    }

    public BigDecimal getInvoiceTotalPrice5() {
        return invoiceTotalPrice5;
    }

    public void setInvoiceTotalPrice5(BigDecimal invoiceTotalPrice5) {
        this.invoiceTotalPrice5 = invoiceTotalPrice5;
    }

    public BigDecimal getInvoiceTotalPrice6() {
        return invoiceTotalPrice6;
    }

    public void setInvoiceTotalPrice6(BigDecimal invoiceTotalPrice6) {
        this.invoiceTotalPrice6 = invoiceTotalPrice6;
    }

    public BigDecimal getInvoiceTotalPrice7() {
        return invoiceTotalPrice7;
    }

    public void setInvoiceTotalPrice7(BigDecimal invoiceTotalPrice7) {
        this.invoiceTotalPrice7 = invoiceTotalPrice7;
    }

    public Date getInvoiceTime() {
        return invoiceTime;
    }

    public void setInvoiceTime(Date invoiceTime) {
        this.invoiceTime = invoiceTime;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public Integer getInvoiceTaxRate() {
        return invoiceTaxRate;
    }

    public void setInvoiceTaxRate(Integer invoiceTaxRate) {
        this.invoiceTaxRate = invoiceTaxRate;
    }

    public BigDecimal getInvoiceUnitAmount() {
        return invoiceUnitAmount;
    }

    public void setInvoiceUnitAmount(BigDecimal invoiceUnitAmount) {
        this.invoiceUnitAmount = invoiceUnitAmount;
    }

    public BigDecimal getInvoiceTax() {
        return invoiceTax;
    }

    public void setInvoiceTax(BigDecimal invoiceTax) {
        this.invoiceTax = invoiceTax;
    }

    public Date getInvoiceTime1() {
        return invoiceTime1;
    }

    public void setInvoiceTime1(Date invoiceTime1) {
        this.invoiceTime1 = invoiceTime1;
    }

    public String getInvoiceCode1() {
        return invoiceCode1;
    }

    public void setInvoiceCode1(String invoiceCode1) {
        this.invoiceCode1 = invoiceCode1;
    }

    public Integer getInvoiceTaxRate1() {
        return invoiceTaxRate1;
    }

    public void setInvoiceTaxRate1(Integer invoiceTaxRate1) {
        this.invoiceTaxRate1 = invoiceTaxRate1;
    }

    public BigDecimal getInvoiceUnitAmount1() {
        return invoiceUnitAmount1;
    }

    public void setInvoiceUnitAmount1(BigDecimal invoiceUnitAmount1) {
        this.invoiceUnitAmount1 = invoiceUnitAmount1;
    }

    public BigDecimal getInvoiceTax1() {
        return invoiceTax1;
    }

    public void setInvoiceTax1(BigDecimal invoiceTax1) {
        this.invoiceTax1 = invoiceTax1;
    }

    public Date getInvoiceTime2() {
        return invoiceTime2;
    }

    public void setInvoiceTime2(Date invoiceTime2) {
        this.invoiceTime2 = invoiceTime2;
    }

    public String getInvoiceCode2() {
        return invoiceCode2;
    }

    public void setInvoiceCode2(String invoiceCode2) {
        this.invoiceCode2 = invoiceCode2;
    }

    public Integer getInvoiceTaxRate2() {
        return invoiceTaxRate2;
    }

    public void setInvoiceTaxRate2(Integer invoiceTaxRate2) {
        this.invoiceTaxRate2 = invoiceTaxRate2;
    }

    public BigDecimal getInvoiceUnitAmount2() {
        return invoiceUnitAmount2;
    }

    public void setInvoiceUnitAmount2(BigDecimal invoiceUnitAmount2) {
        this.invoiceUnitAmount2 = invoiceUnitAmount2;
    }

    public BigDecimal getInvoiceTax2() {
        return invoiceTax2;
    }

    public void setInvoiceTax2(BigDecimal invoiceTax2) {
        this.invoiceTax2 = invoiceTax2;
    }

    public Date getInvoiceTime3() {
        return invoiceTime3;
    }

    public void setInvoiceTime3(Date invoiceTime3) {
        this.invoiceTime3 = invoiceTime3;
    }

    public String getInvoiceCode3() {
        return invoiceCode3;
    }

    public void setInvoiceCode3(String invoiceCode3) {
        this.invoiceCode3 = invoiceCode3;
    }

    public Integer getInvoiceTaxRate3() {
        return invoiceTaxRate3;
    }

    public void setInvoiceTaxRate3(Integer invoiceTaxRate3) {
        this.invoiceTaxRate3 = invoiceTaxRate3;
    }

    public BigDecimal getInvoiceUnitAmount3() {
        return invoiceUnitAmount3;
    }

    public void setInvoiceUnitAmount3(BigDecimal invoiceUnitAmount3) {
        this.invoiceUnitAmount3 = invoiceUnitAmount3;
    }

    public BigDecimal getInvoiceTax3() {
        return invoiceTax3;
    }

    public void setInvoiceTax3(BigDecimal invoiceTax3) {
        this.invoiceTax3 = invoiceTax3;
    }

    public Date getInvoiceTime4() {
        return invoiceTime4;
    }

    public void setInvoiceTime4(Date invoiceTime4) {
        this.invoiceTime4 = invoiceTime4;
    }

    public String getInvoiceCode4() {
        return invoiceCode4;
    }

    public void setInvoiceCode4(String invoiceCode4) {
        this.invoiceCode4 = invoiceCode4;
    }

    public Integer getInvoiceTaxRate4() {
        return invoiceTaxRate4;
    }

    public void setInvoiceTaxRate4(Integer invoiceTaxRate4) {
        this.invoiceTaxRate4 = invoiceTaxRate4;
    }

    public BigDecimal getInvoiceUnitAmount4() {
        return invoiceUnitAmount4;
    }

    public void setInvoiceUnitAmount4(BigDecimal invoiceUnitAmount4) {
        this.invoiceUnitAmount4 = invoiceUnitAmount4;
    }

    public BigDecimal getInvoiceTax4() {
        return invoiceTax4;
    }

    public void setInvoiceTax4(BigDecimal invoiceTax4) {
        this.invoiceTax4 = invoiceTax4;
    }

    public Date getInvoiceTime5() {
        return invoiceTime5;
    }

    public void setInvoiceTime5(Date invoiceTime5) {
        this.invoiceTime5 = invoiceTime5;
    }

    public String getInvoiceCode5() {
        return invoiceCode5;
    }

    public void setInvoiceCode5(String invoiceCode5) {
        this.invoiceCode5 = invoiceCode5;
    }

    public Integer getInvoiceTaxRate5() {
        return invoiceTaxRate5;
    }

    public void setInvoiceTaxRate5(Integer invoiceTaxRate5) {
        this.invoiceTaxRate5 = invoiceTaxRate5;
    }

    public BigDecimal getInvoiceUnitAmount5() {
        return invoiceUnitAmount5;
    }

    public void setInvoiceUnitAmount5(BigDecimal invoiceUnitAmount5) {
        this.invoiceUnitAmount5 = invoiceUnitAmount5;
    }

    public BigDecimal getInvoiceTax5() {
        return invoiceTax5;
    }

    public void setInvoiceTax5(BigDecimal invoiceTax5) {
        this.invoiceTax5 = invoiceTax5;
    }

    public Date getInvoiceTime6() {
        return invoiceTime6;
    }

    public void setInvoiceTime6(Date invoiceTime6) {
        this.invoiceTime6 = invoiceTime6;
    }

    public String getInvoiceCode6() {
        return invoiceCode6;
    }

    public void setInvoiceCode6(String invoiceCode6) {
        this.invoiceCode6 = invoiceCode6;
    }

    public Integer getInvoiceTaxRate6() {
        return invoiceTaxRate6;
    }

    public void setInvoiceTaxRate6(Integer invoiceTaxRate6) {
        this.invoiceTaxRate6 = invoiceTaxRate6;
    }

    public BigDecimal getInvoiceUnitAmount6() {
        return invoiceUnitAmount6;
    }

    public void setInvoiceUnitAmount6(BigDecimal invoiceUnitAmount6) {
        this.invoiceUnitAmount6 = invoiceUnitAmount6;
    }

    public BigDecimal getInvoiceTax6() {
        return invoiceTax6;
    }

    public void setInvoiceTax6(BigDecimal invoiceTax6) {
        this.invoiceTax6 = invoiceTax6;
    }

    public Date getInvoiceTime7() {
        return invoiceTime7;
    }

    public void setInvoiceTime7(Date invoiceTime7) {
        this.invoiceTime7 = invoiceTime7;
    }

    public String getInvoiceCode7() {
        return invoiceCode7;
    }

    public void setInvoiceCode7(String invoiceCode7) {
        this.invoiceCode7 = invoiceCode7;
    }

    public Integer getInvoiceTaxRate7() {
        return invoiceTaxRate7;
    }

    public void setInvoiceTaxRate7(Integer invoiceTaxRate7) {
        this.invoiceTaxRate7 = invoiceTaxRate7;
    }

    public BigDecimal getInvoiceUnitAmount7() {
        return invoiceUnitAmount7;
    }

    public void setInvoiceUnitAmount7(BigDecimal invoiceUnitAmount7) {
        this.invoiceUnitAmount7 = invoiceUnitAmount7;
    }

    public BigDecimal getInvoiceTax7() {
        return invoiceTax7;
    }

    public void setInvoiceTax7(BigDecimal invoiceTax7) {
        this.invoiceTax7 = invoiceTax7;
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

    public String getDealDeptName() {
        return dealDeptName;
    }

    public void setDealDeptName(String dealDeptName) {
        this.dealDeptName = dealDeptName;
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
    public String getDealDeptCode() {
        return dealDeptCode;
    }

    public void setDealDeptCode(String dealDeptCode) {
        this.dealDeptCode = dealDeptCode;
    }

    public String getDealLeaderName() {
        return dealLeaderName;
    }

    public void setDealLeaderName(String dealLeaderName) {
        this.dealLeaderName = dealLeaderName;
    }

    public String getDealLeaderNumber() {
        return dealLeaderNumber;
    }

    public void setDealLeaderNumber(String dealLeaderNumber) {
        this.dealLeaderNumber = dealLeaderNumber;
    }

    public void setClerkDeptName2(String clerkDeptName2) {
        this.clerkDeptName2 = clerkDeptName2;
    }

    public void setClerkDeptName1(String clerkDeptName1) {
        this.clerkDeptName1 = clerkDeptName1;
    }

    public void setClerkDeptName3(String clerkDeptName3) {
        this.clerkDeptName3 = clerkDeptName3;
    }

    public void setClerkDeptName4(String clerkDeptName4) {
        this.clerkDeptName4 = clerkDeptName4;
    }

    public void setClerkDeptName5(String clerkDeptName5) {
        this.clerkDeptName5 = clerkDeptName5;
    }
    public String getClerkDeptName2() {
        return clerkDeptName2;
    }

    public String getClerkDeptName1() {
        return clerkDeptName1;
    }

    public String getClerkDeptName3() {
        return clerkDeptName3;
    }

    public String getClerkDeptName4() {
        return clerkDeptName4;
    }

    public String getClerkDeptName5() {
        return clerkDeptName5;
    }
}
