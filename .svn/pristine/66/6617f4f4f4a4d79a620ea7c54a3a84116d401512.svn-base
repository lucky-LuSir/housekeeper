package com.kfwy.hkp.trade.order.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.hkp.trade.order.enums.OrderInvoiceType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author:ChenJie Created by kfwy_it_009 on 2018/11/11.
 */
public class OrderInvoiceEntity  extends BaseEntity {

    /**
     * 订单编号
     */
    @NotBlank(message = "订单编码为空")
    protected String orderCode;

    /**
     * 订单开票编码
     */
    protected String orderInvoiceCode;

    /**
     * 申请者
     */
    protected String applyCode;
    protected String applyName;
    /**
     * 申请者所属大区
     */
    protected String parentDeptCode;
    protected String parentDeptName;
    /**
     * 申请者所属部门
     */
    protected String deptCode;
    protected String deptName;
    /**
     * 开票公司电话
     */
    @NotBlank(message = "联系方式为空")
    protected String phone;
    /**
     * 社会统一信用代码（纳税人识别号）
     */
    @NotBlank(message = "纳税人识别号为空")
    protected String creditCode;
    /**
     * 开票公司地址
     */
    @NotBlank(message = "地址为空")
    protected String address;
    /**
     * 开票公司银行支行信息
     */
    @NotBlank(message = "银行支行信息为空")
    protected String bankBranch;
    /**
     * 银行账号
     */
    @NotBlank(message = "银行账号为空")
    protected String bankCard;

    /**
     * 发票号
     */
    protected String invoiceCode;

    /**
     * 订单开票类型
     * @see com.kfwy.hkp.trade.order.enums.OrderInvoiceType
     */
    @NotNull(message = "开票类型为空")
    protected String invoiceType;
    protected String invoiceTypeName;
    /**
     * 金额
     */
    protected BigDecimal invoiceUnitAmount;

    /**
     * 税率
     */
    @NotNull(message = "税率为空")
    protected Integer invoiceTaxRate;

    /**
     * 税额
     */
    protected BigDecimal invoiceTax;

    /**
     * 发票总额
     */
    @NotNull(message = "开票总额为空")
    protected BigDecimal invoiceTotalPrice;

    /**
     * 总额大写
     */
    protected String invoiceUpperCase;

    /**
     * 开票日期
     */
    protected Date invoiceTime;
    /**
     * 收件人名称
     */
    @NotBlank(message = "收件人名称为空")
    protected String consigneeName;
    /**
     * 收件人地址
     */
    @NotBlank(message = "收件人地址为空")
    protected String consigneeAddress;
    /**
     * 收件人电话
     */
    @NotBlank(message = "收件人电话为空")
    protected String consigneePhone;

    /**
     *
     * @return
     */
    protected Boolean hasDeductions;

    public Boolean getHasDeductions() {
        return hasDeductions;
    }

    public void setHasDeductions(Boolean hasDeductions) {
        this.hasDeductions = hasDeductions;
    }

    public String getApplyCode() {
        return applyCode;
    }

    public void setApplyCode(String applyCode) {
        this.applyCode = applyCode;
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public String getParentDeptName() {
        return parentDeptName;
    }

    public void setParentDeptName(String parentDeptName) {
        this.parentDeptName = parentDeptName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderInvoiceCode() {
        return orderInvoiceCode;
    }

    public void setOrderInvoiceCode(String orderInvoiceCode) {
        this.orderInvoiceCode = orderInvoiceCode;
    }

    public String getParentDeptCode() {
        return parentDeptCode;
    }

    public void setParentDeptCode(String parentDeptCode) {
        this.parentDeptCode = parentDeptCode;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public BigDecimal getInvoiceUnitAmount() {
        return invoiceUnitAmount;
    }

    public void setInvoiceUnitAmount(BigDecimal invoiceUnitAmount) {
        this.invoiceUnitAmount = invoiceUnitAmount;
    }

    public Integer getInvoiceTaxRate() {
        return invoiceTaxRate;
    }

    public void setInvoiceTaxRate(Integer invoiceTaxRate) {
        this.invoiceTaxRate = invoiceTaxRate;
    }

    public BigDecimal getInvoiceTax() {
        return invoiceTax;
    }

    public void setInvoiceTax(BigDecimal invoiceTax) {
        this.invoiceTax = invoiceTax;
    }

    public BigDecimal getInvoiceTotalPrice() {
        return invoiceTotalPrice;
    }

    public void setInvoiceTotalPrice(BigDecimal invoiceTotalPrice) {
        this.invoiceTotalPrice = invoiceTotalPrice;
    }

    public String getInvoiceUpperCase() {
        return invoiceUpperCase;
    }

    public void setInvoiceUpperCase(String invoiceUpperCase) {
        this.invoiceUpperCase = invoiceUpperCase;
    }

    public Date getInvoiceTime() {
        return invoiceTime;
    }

    public void setInvoiceTime(Date invoiceTime) {
        this.invoiceTime = invoiceTime;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    public String getConsigneePhone() {
        return consigneePhone;
    }

    public void setConsigneePhone(String consigneePhone) {
        this.consigneePhone = consigneePhone;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceTypeName() {
        if(invoiceType != null && invoiceTypeName ==null){
            invoiceTypeName = DictionaryStorage.get(OrderInvoiceType.getKey(),invoiceType).getName();
        }
        return invoiceTypeName;
    }

    public void setInvoiceTypeName(String invoiceTypeName) {
        this.invoiceTypeName = invoiceTypeName;
    }
}
