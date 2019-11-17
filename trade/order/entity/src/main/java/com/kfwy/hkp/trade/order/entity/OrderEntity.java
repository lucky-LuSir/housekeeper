package com.kfwy.hkp.trade.order.entity;

import com.gniuu.framework.common.enums.SexType;
import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.hkp.sys.file.entity.FileRelationEntity;
import com.kfwy.hkp.trade.order.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author zjp
 * @date 2018/8/15
 */
public class OrderEntity extends BaseEntity {

    /**
     * 订单编号
     */
    protected String orderCode;
    /**
     * 订单日期
     */
    protected Date orderTime;
    /**
     * 订单状态
     */
    protected String orderStatus;
    /**
     * 订单状态名字
     */
    protected String orderStatusName;
    /**
     * 成交人编号
     */
    protected String empCode;
    /**
     * 成交人名字
     */
    protected String empName;
    /**
     * 成交部门编码
     */
    protected String deptCode;
    /**
     * 成交部门名称
     */
    protected String deptName;
    /**
     * 房源编码
     */
    protected String houseCode;
    /**
     * 业主手机号
     */
    protected String houseownerPhone;
    /**
     * 客户编码
     */
    protected String cusCode;
    /**
     * 客户手机号
     */
    protected String cusPhone;
    /**
     * 出租面积
     */
    protected BigDecimal acreage;
    /**
     * 出租价格
     */
    protected BigDecimal price;
    /**
     * 价格单位
     */
    protected String unit;
    /**
     * 合同开始时间
     */
    protected Date contractStart;
    /**
     * 合同结束时间
     */
    protected Date contractEnd;
    /**
     * 交月份数
     */
    protected Integer monthCount;
    /**
     * 押金
     */
    protected BigDecimal deposit;
    /**
     * 业主佣金
     */
    protected BigDecimal receivableHos;
    /**
     * 客户佣金
     */
    protected BigDecimal receivableCus;
    /**
     * 佣金总计
     */
    protected BigDecimal commission;

    //09月28日增加返佣字段

    /**
     * 客户返佣
     */
    protected BigDecimal cusRebate;

    /**
     * 业主返佣
     */
    protected BigDecimal ownerRebate;

    /**
     * 客户兼职分钱
     */
    protected BigDecimal cusParttimeMoney;

    /**
     * 业主兼职分钱
     */
    protected BigDecimal ownerParttimeMoney;

    /**
     * 实际可分佣金总额
     */
    protected BigDecimal actualCommision;

    /**
     * 实际分成佣金总额
     */
    protected BigDecimal actualBranchCommision;

    /**
     * 实际分成佣金总额
     */
    protected BigDecimal actualCusServCommision;


    /**
     * 每月租金
     */
    protected BigDecimal monthRent;

    /**
     * 客户佣金确认书
     */
    protected String cusImageName;
    /**
     * 客户佣金确认书编号
     */
    protected String cusCommissionNum;
    /**
     * 业主佣金确认书
     */
    protected String ownerImageName;
    /**
     * 业主佣金确认书编号
     */
    protected String ownerCommissionNum;
    /**
     * 预计收款时间
     */
    protected Date expectPaymentTime;

    /**
     * 收佣凭证
     */
    protected List<FileRelationEntity> fileRelationEntityList;
    /**
     * 分成人分成明细
     */
    protected List<OrderPercentageEntity> orderPercentageEntityList;
    /**
     * 客服分成明细
     */
    protected List<OrderPercentageEntity> cusServicePercentageEntityList;



    /**
     * 当前人的分成总数
     */
    protected BigDecimal empPercentageTotal;
    /**
     * 客户名字
     */
    protected String cusName;
    /**
     * 客户性别
     */
    protected String cusSex;
    /**
     * 客户性别名
     */
    protected String cusSexName;
    /**
     * 客户经纪人
     */
    protected String cusEmpName;
    /**
     * 房源名字
     */
    protected String houseName;
    /**
     * 房源经纪人
     */
    protected String hosEmpName;
    /**
     * 房源省份名字
     */
    protected String provinceName;
    /**
     * 房源城市名字
     */
    protected String cityName;
    /**
     * 房源区域名字
     */
    protected String regionName;
    /**
     * 房源街道名字
     */
    protected String streetName;

    protected String communityName;



    /**
     * 房源位置详情
     */
    protected String detailAddress;

    /**
     * 2019/1/11  chenjie 订单统计导出所需字段
     */
    /**
     * 大区名称
     */
    protected String parDeptName;
    /**
     * 部门领导
     */
    protected String leaderName;
    /**
     * 回款
     */
    protected BigDecimal paybackSum;
    /**
     * 税金
     */
    protected BigDecimal ivInvoiceTax;
    /**
     * 房源类型
     */
    protected String houseType;
    /**
     * 房源地址
     */
    protected String regioName;
    /**
     * 业主类型
     */
    protected String houseownerType;
    /**
     * 业主姓名
     */
    protected String houseownerName;
    /**
     * 业主公司
     */
    protected String houseownerCompany;
    /**
     * 客户公司
     */
    protected String cusCompany;

    /**
     * 2019/01/14 订单分成导出所需字段
     */
    protected BigDecimal perPercentage;
    /**
     * 分成人名1
     */
    protected String perEmpName1;
    /**
     * 分成人工号1
     */
    protected String perWorkNumner1;
    /**
     * 分成人部门1
     */
    protected String perDeptName1;
    /**
     * 分成金额1
     */
    protected BigDecimal perProfit1;

    protected BigDecimal perPercentage1;

    /**
     * 分成人名2
     */
    protected String perEmpName2;
    /**
     * 分成人工号2
     */
    protected String perWorkNumner2;
    /**
     * 分成人部门2
     */
    protected String perDeptName2;
    /**
     * 分成金额2
     */
    protected BigDecimal perProfit2;

    protected BigDecimal perPercentage2;

    /**
     * 分成人名3
     */
    protected String perEmpName3;
    /**
     * 分成人工号3
     */
    protected String perWorkNumner3;
    /**
     * 分成人部门3
     */
    protected String perDeptName3;
    /**
     * 分成金额3
     */
    protected BigDecimal perProfit3;

    protected BigDecimal perPercentage3;

    /**
     * 分成人名4
     */
    protected String perEmpName4;
    /**
     * 分成人工号4
     */
    protected String perWorkNumner4;
    /**
     * 分成人部门4
     */
    protected String perDeptName4;
    /**
     * 分成金额4
     */
    protected BigDecimal perProfit4;

    protected BigDecimal perPercentage4;

    /**
     * 分成人名5
     */
    protected String perEmpName5;
    /**
     * 分成人工号5
     */
    protected String perWorkNumner5;
    /**
     * 分成人部门5
     */
    protected String perDeptName5;
    /**
     * 分成金额5
     */
    protected BigDecimal perProfit5;

    protected BigDecimal perPercentage5;
    /**
     * 是否收回佣金确认书
     */
    protected Boolean hasCommissionConfirm;

    /**
     * 分成人名
     */
    protected String perEmpName;
    /**
     * 分成人工号
     */
    protected String perWorkNumner;
    /**
     * 分成人部门
     */
    protected String perDeptName;
    /**
     * 分成金额
     */
    protected BigDecimal perProfit;

    protected String updateType;
    protected String oldValue;
    protected String newValue;
    protected String banlance;

    /**
     * 是否有协助分成
     */
    protected Boolean assistFlag;
    /**
     * 协助分成
     */
    protected List<OrderPercentageEntity> assistEntityList;


    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getPerWorkNumner() {
        return perWorkNumner;
    }

    public void setPerWorkNumner(String perWorkNumner) {
        this.perWorkNumner = perWorkNumner;
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

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatusName() {

        if (orderStatusName == null && orderStatus != null) {
            orderStatusName = DictionaryStorage.get(OrderStatus.class.getName(), orderStatus).getName();
        }

        return orderStatusName;
    }

    public void setOrderStatusName(String orderStatusName) {
        this.orderStatusName = orderStatusName;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmpName() {
        return empName;
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

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    public String getHouseownerPhone() {
        return houseownerPhone;
    }

    public void setHouseownerPhone(String houseownerPhone) {
        this.houseownerPhone = houseownerPhone;
    }

    public String getCusCode() {
        return cusCode;
    }

    public void setCusCode(String cusCode) {
        this.cusCode = cusCode;
    }

    public String getCusPhone() {
        return cusPhone;
    }

    public void setCusPhone(String cusPhone) {
        this.cusPhone = cusPhone;
    }

    public BigDecimal getAcreage() {
        return acreage;
    }

    public void setAcreage(BigDecimal acreage) {
        this.acreage = acreage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Date getContractStart() {
        return contractStart;
    }

    public void setContractStart(Date contractStart) {
        this.contractStart = contractStart;
    }

    public Date getContractEnd() {
        return contractEnd;
    }

    public void setContractEnd(Date contractEnd) {
        this.contractEnd = contractEnd;
    }

    public Integer getMonthCount() {
        return monthCount;
    }

    public void setMonthCount(Integer monthCount) {
        this.monthCount = monthCount;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public BigDecimal getReceivableHos() {
        return receivableHos;
    }

    public void setReceivableHos(BigDecimal receivableHos) {
        this.receivableHos = receivableHos;
    }

    public BigDecimal getReceivableCus() {
        return receivableCus;
    }

    public void setReceivableCus(BigDecimal receivableCus) {
        this.receivableCus = receivableCus;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public List<FileRelationEntity> getFileRelationEntityList() {
        return fileRelationEntityList;
    }

    public void setFileRelationEntityList(List<FileRelationEntity> fileRelationEntityList) {
        this.fileRelationEntityList = fileRelationEntityList;
    }

    public List<OrderPercentageEntity> getOrderPercentageEntityList() {
        return orderPercentageEntityList;
    }

    public void setOrderPercentageEntityList(List<OrderPercentageEntity> orderPercentageEntityList) {
        this.orderPercentageEntityList = orderPercentageEntityList;
    }

    public BigDecimal getEmpPercentageTotal() {
        return empPercentageTotal;
    }

    public void setEmpPercentageTotal(BigDecimal empPercentageTotal) {
        this.empPercentageTotal = empPercentageTotal;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusSex() {
        return cusSex;
    }

    public void setCusSex(String cusSex) {
        this.cusSex = cusSex;
    }

    public String getCusSexName() {

        if (cusSexName == null && cusSex != null) {
            cusSexName = DictionaryStorage.get(SexType.getKey(), this.cusSex).getName();
        }

        return cusSexName;
    }

    public void setCusSexName(String cusSexName) {
        this.cusSexName = cusSexName;
    }

    public String getCusEmpName() {
        return cusEmpName;
    }

    public void setCusEmpName(String cusEmpName) {
        this.cusEmpName = cusEmpName;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getHosEmpName() {
        return hosEmpName;
    }

    public void setHosEmpName(String hosEmpName) {
        this.hosEmpName = hosEmpName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
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

    public BigDecimal getCusParttimeMoney() {
        return cusParttimeMoney;
    }

    public void setCusParttimeMoney(BigDecimal cusParttimeMoney) {
        this.cusParttimeMoney = cusParttimeMoney;
    }

    public BigDecimal getOwnerParttimeMoney() {
        return ownerParttimeMoney;
    }

    public void setOwnerParttimeMoney(BigDecimal ownerParttimeMoney) {
        this.ownerParttimeMoney = ownerParttimeMoney;
    }

    public BigDecimal getActualCommision() {
        return actualCommision;
    }

    public void setActualCommision(BigDecimal actualCommision) {
        this.actualCommision = actualCommision;
    }

    public BigDecimal getMonthRent() {
        return monthRent;
    }

    public void setMonthRent(BigDecimal monthRent) {
        this.monthRent = monthRent;
    }

    public BigDecimal getActualBranchCommision() {
        return actualBranchCommision;
    }

    public void setActualBranchCommision(BigDecimal actualBranchCommision) {
        this.actualBranchCommision = actualBranchCommision;
    }

    public List<OrderPercentageEntity> getCusServicePercentageEntityList() {
        return cusServicePercentageEntityList;
    }

    public void setCusServicePercentageEntityList(List<OrderPercentageEntity> cusServicePercentageEntityList) {
        this.cusServicePercentageEntityList = cusServicePercentageEntityList;
    }

    public BigDecimal getActualCusServCommision() {
        return actualCusServCommision;
    }

    public void setActualCusServCommision(BigDecimal actualCusServCommision) {
        this.actualCusServCommision = actualCusServCommision;
    }

    public String getCusImageName() {
        return cusImageName;
    }

    public void setCusImageName(String cusImageName) {
        this.cusImageName = cusImageName;
    }

    public String getCusCommissionNum() {
        return cusCommissionNum;
    }

    public void setCusCommissionNum(String cusCommissionNum) {
        this.cusCommissionNum = cusCommissionNum;
    }

    public String getOwnerImageName() {
        return ownerImageName;
    }

    public void setOwnerImageName(String ownerImageName) {
        this.ownerImageName = ownerImageName;
    }

    public String getOwnerCommissionNum() {
        return ownerCommissionNum;
    }

    public void setOwnerCommissionNum(String ownerCommissionNum) {
        this.ownerCommissionNum = ownerCommissionNum;
    }

    public Date getExpectPaymentTime() {
        return expectPaymentTime;
    }

    public void setExpectPaymentTime(Date expectPaymentTime) {
        this.expectPaymentTime = expectPaymentTime;
    }

    public String getParDeptName() {
        return parDeptName;
    }

    public void setParDeptName(String parDeptName) {
        this.parDeptName = parDeptName;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public BigDecimal getPaybackSum() {
        return paybackSum;
    }

    public void setPaybackSum(BigDecimal paybackSum) {
        this.paybackSum = paybackSum;
    }

    public BigDecimal getIvInvoiceTax() {
        return ivInvoiceTax;
    }

    public void setIvInvoiceTax(BigDecimal ivInvoiceTax) {
        this.ivInvoiceTax = ivInvoiceTax;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getRegioName() {
        return regioName;
    }

    public void setRegioName(String regioName) {
        this.regioName = regioName;
    }

    public String getHouseownerType() {
        return houseownerType;
    }

    public void setHouseownerType(String houseownerType) {
        this.houseownerType = houseownerType;
    }

    public String getHouseownerName() {
        return houseownerName;
    }

    public void setHouseownerName(String houseownerName) {
        this.houseownerName = houseownerName;
    }

    public String getHouseownerCompany() {
        return houseownerCompany;
    }

    public void setHouseownerCompany(String houseownerCompany) {
        this.houseownerCompany = houseownerCompany;
    }

    public String getCusCompany() {
        return cusCompany;
    }

    public void setCusCompany(String cusCompany) {
        this.cusCompany = cusCompany;
    }

    public BigDecimal getPerPercentage() {
        return perPercentage;
    }

    public void setPerPercentage(BigDecimal perPercentage) {
        this.perPercentage = perPercentage;
    }

    public String getPerEmpName() {
        return perEmpName;
    }

    public void setPerEmpName(String perEmpName) {
        this.perEmpName = perEmpName;
    }

    public String getPerDeptName() {
        return perDeptName;
    }

    public void setPerDeptName(String perDeptName) {
        this.perDeptName = perDeptName;
    }

    public BigDecimal getPerProfit() {
        return perProfit;
    }

    public void setPerProfit(BigDecimal perProfit) {
        this.perProfit = perProfit;
    }

    public Boolean getHasCommissionConfirm() {
        return hasCommissionConfirm;
    }

    public void setHasCommissionConfirm(Boolean hasCommissionConfirm) {
        this.hasCommissionConfirm = hasCommissionConfirm;
    }

    public String getUpdateType() {
        return updateType;
    }

    public void setUpdateType(String updateType) {
        this.updateType = updateType;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public String getBanlance() {
        return banlance;
    }

    public void setBanlance(String banlance) {
        this.banlance = banlance;
    }

    public String getPerEmpName1() {
        return perEmpName1;
    }

    public void setPerEmpName1(String perEmpName1) {
        this.perEmpName1 = perEmpName1;
    }

    public String getPerWorkNumner1() {
        return perWorkNumner1;
    }

    public void setPerWorkNumner1(String perWorkNumner1) {
        this.perWorkNumner1 = perWorkNumner1;
    }

    public String getPerDeptName1() {
        return perDeptName1;
    }

    public void setPerDeptName1(String perDeptName1) {
        this.perDeptName1 = perDeptName1;
    }

    public BigDecimal getPerProfit1() {
        return perProfit1;
    }

    public void setPerProfit1(BigDecimal perProfit1) {
        this.perProfit1 = perProfit1;
    }

    public String getPerEmpName2() {
        return perEmpName2;
    }

    public void setPerEmpName2(String perEmpName2) {
        this.perEmpName2 = perEmpName2;
    }

    public String getPerWorkNumner2() {
        return perWorkNumner2;
    }

    public void setPerWorkNumner2(String perWorkNumner2) {
        this.perWorkNumner2 = perWorkNumner2;
    }

    public String getPerDeptName2() {
        return perDeptName2;
    }

    public void setPerDeptName2(String perDeptName2) {
        this.perDeptName2 = perDeptName2;
    }

    public BigDecimal getPerProfit2() {
        return perProfit2;
    }

    public void setPerProfit2(BigDecimal perProfit2) {
        this.perProfit2 = perProfit2;
    }

    public String getPerEmpName3() {
        return perEmpName3;
    }

    public void setPerEmpName3(String perEmpName3) {
        this.perEmpName3 = perEmpName3;
    }

    public String getPerWorkNumner3() {
        return perWorkNumner3;
    }

    public void setPerWorkNumner3(String perWorkNumner3) {
        this.perWorkNumner3 = perWorkNumner3;
    }

    public String getPerDeptName3() {
        return perDeptName3;
    }

    public void setPerDeptName3(String perDeptName3) {
        this.perDeptName3 = perDeptName3;
    }

    public BigDecimal getPerProfit3() {
        return perProfit3;
    }

    public void setPerProfit3(BigDecimal perProfit3) {
        this.perProfit3 = perProfit3;
    }

    public String getPerEmpName4() {
        return perEmpName4;
    }

    public void setPerEmpName4(String perEmpName4) {
        this.perEmpName4 = perEmpName4;
    }

    public String getPerWorkNumner4() {
        return perWorkNumner4;
    }

    public void setPerWorkNumner4(String perWorkNumner4) {
        this.perWorkNumner4 = perWorkNumner4;
    }

    public String getPerDeptName4() {
        return perDeptName4;
    }

    public void setPerDeptName4(String perDeptName4) {
        this.perDeptName4 = perDeptName4;
    }

    public BigDecimal getPerProfit4() {
        return perProfit4;
    }

    public void setPerProfit4(BigDecimal perProfit4) {
        this.perProfit4 = perProfit4;
    }

    public String getPerEmpName5() {
        return perEmpName5;
    }

    public void setPerEmpName5(String perEmpName5) {
        this.perEmpName5 = perEmpName5;
    }

    public String getPerWorkNumner5() {
        return perWorkNumner5;
    }

    public void setPerWorkNumner5(String perWorkNumner5) {
        this.perWorkNumner5 = perWorkNumner5;
    }

    public String getPerDeptName5() {
        return perDeptName5;
    }

    public void setPerDeptName5(String perDeptName5) {
        this.perDeptName5 = perDeptName5;
    }

    public BigDecimal getPerProfit5() {
        return perProfit5;
    }

    public void setPerProfit5(BigDecimal perProfit5) {
        this.perProfit5 = perProfit5;
    }

    public BigDecimal getPerPercentage1() {
        return perPercentage1;
    }

    public void setPerPercentage1(BigDecimal perPercentage1) {
        this.perPercentage1 = perPercentage1;
    }

    public BigDecimal getPerPercentage2() {
        return perPercentage2;
    }

    public void setPerPercentage2(BigDecimal perPercentage2) {
        this.perPercentage2 = perPercentage2;
    }

    public BigDecimal getPerPercentage3() {
        return perPercentage3;
    }

    public void setPerPercentage3(BigDecimal perPercentage3) {
        this.perPercentage3 = perPercentage3;
    }

    public BigDecimal getPerPercentage4() {
        return perPercentage4;
    }

    public void setPerPercentage4(BigDecimal perPercentage4) {
        this.perPercentage4 = perPercentage4;
    }

    public BigDecimal getPerPercentage5() {
        return perPercentage5;
    }

    public void setPerPercentage5(BigDecimal perPercentage5) {
        this.perPercentage5 = perPercentage5;
    }

    public Boolean getAssistFlag() {
        return assistFlag;
    }

    public void setAssistFlag(Boolean assistFlag) {
        this.assistFlag = assistFlag;
    }

    public List<OrderPercentageEntity> getAssistEntityList() {
        return assistEntityList;
    }

    public void setAssistEntityList(List<OrderPercentageEntity> assistEntityList) {
        this.assistEntityList = assistEntityList;
    }

    @Override
    public String toString() {
        return "订单tostring的OrderEntity{" +
                "orderCode='" + orderCode + '\'' +
                ", orderTime=" + orderTime +
                ", orderStatus='" + orderStatus + '\'' +
                ", orderStatusName='" + orderStatusName + '\'' +
                ", empCode='" + empCode + '\'' +
                ", empName='" + empName + '\'' +
                ", houseCode='" + houseCode + '\'' +
                ", houseownerPhone='" + houseownerPhone + '\'' +
                ", cusCode='" + cusCode + '\'' +
                ", cusPhone='" + cusPhone + '\'' +
                ", acreage=" + acreage +
                ", price=" + price +
                ", unit='" + unit + '\'' +
                ", contractStart=" + contractStart +
                ", contractEnd=" + contractEnd +
                ", monthCount=" + monthCount +
                ", deposit=" + deposit +
                ", receivableHos=" + receivableHos +
                ", receivableCus=" + receivableCus +
                ", commission=" + commission +
                ", cusRebate=" + cusRebate +
                ", ownerRebate=" + ownerRebate +
                ", cusParttimeMoney=" + cusParttimeMoney +
                ", ownerParttimeMoney=" + ownerParttimeMoney +
                ", actualCommision=" + actualCommision +
                ", actualBranchCommision=" + actualBranchCommision +
                ", actualCusServCommision=" + actualCusServCommision +
                ", monthRent=" + monthRent +
                ", cusImageName='" + cusImageName + '\'' +
                ", cusCommissionNum='" + cusCommissionNum + '\'' +
                ", ownerImageName='" + ownerImageName + '\'' +
                ", ownerCommissionNum='" + ownerCommissionNum + '\'' +
                ", expectPaymentTime=" + expectPaymentTime +
                ", fileRelationEntityList=" + fileRelationEntityList +
                ", orderPercentageEntityList=" + orderPercentageEntityList +
                ", cusServicePercentageEntityList=" + cusServicePercentageEntityList +
                ", empPercentageTotal=" + empPercentageTotal +
                ", cusName='" + cusName + '\'' +
                ", cusSex='" + cusSex + '\'' +
                ", cusSexName='" + cusSexName + '\'' +
                ", cusEmpName='" + cusEmpName + '\'' +
                ", houseName='" + houseName + '\'' +
                ", hosEmpName='" + hosEmpName + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", regionName='" + regionName + '\'' +
                ", streetName='" + streetName + '\'' +
                ", detailAddress='" + detailAddress + '\'' +
                '}';
    }
}
