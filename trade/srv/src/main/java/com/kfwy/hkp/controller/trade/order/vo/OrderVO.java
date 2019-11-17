package com.kfwy.hkp.controller.trade.order.vo;

import com.gniuu.framework.common.context.CurrentContext;
import com.kfwy.hkp.common.utils.ParamUtil;
import com.kfwy.hkp.trade.order.entity.OrderEntity;
import com.kfwy.hkp.trade.order.enums.OrderQueryType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author liwensihan
 * @date 2019/3/19
 */
public class OrderVO extends OrderEntity {



    /**
     * 查询类型： 1 我的成交  2 我的分成 3 我的创建
     */
    protected String queryType;

    /**
     * 业主电话
     */
    protected String houseownerPhone;
    /**
     * 省份
     */
    protected String province;

    /**
     * 城市
     */
    protected String city;

    /**
     * 区域
     */
    protected String region;

    /**
     * 街道
     */
    protected String street;

    /**
     * 社区
     */
    protected String community;

    /**
     * 房源地址
     */
    protected String detailAddress;
    /**
     * 房源标题
     */
    /**
     * 房源开始面积
     */
    protected BigDecimal acreageStart;
    /**
     * 房源结束面积
     */
    protected BigDecimal acreageEnd;

    /**
     * 关键字查询，包括订单编号，房源编号，客户编号
     */
    protected String keyword;

    protected List<Date> orderCreateTimeStartAndEnd;

    protected List<Date> expectPaymentTimeStartAndEnd;

    protected Date createTimeStart;

    protected Date createTimeEnd;

    @Override
    public String getHouseownerPhone() {
        return houseownerPhone;
    }

    @Override
    public void setHouseownerPhone(String houseownerPhone) {
        this.houseownerPhone = houseownerPhone;
    }

    @Override
    public String getDetailAddress() {
        return detailAddress;
    }

    @Override
    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public Date getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(Date createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public Date getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(Date createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }


    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }


    public BigDecimal getAcreageStart() {
        return acreageStart;
    }

    public void setAcreageStart(BigDecimal acreageStart) {
        this.acreageStart = acreageStart;
    }

    public BigDecimal getAcreageEnd() {
        return acreageEnd;
    }

    public void setAcreageEnd(BigDecimal acreageEnd) {
        this.acreageEnd = acreageEnd;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<Date> getOrderCreateTimeStartAndEnd() {



        return orderCreateTimeStartAndEnd;
    }

    public void setOrderCreateTimeStartAndEnd(List<Date> orderCreateTimeStartAndEnd) {
        this.orderCreateTimeStartAndEnd = orderCreateTimeStartAndEnd;
    }

    public List<Date> getExpectPaymentTimeStartAndEnd() {
        return expectPaymentTimeStartAndEnd;
    }

    public void setExpectPaymentTimeStartAndEnd(List<Date> expectPaymentTimeStartAndEnd) {
        this.expectPaymentTimeStartAndEnd = expectPaymentTimeStartAndEnd;
    }

    /**
     * 设置订单开始结束时间区间
     * @param param
     * @return
     */
    public void putOrderCreateTimeStartAndEnd(Map param){
        if (orderCreateTimeStartAndEnd!=null&& orderCreateTimeStartAndEnd.size()>0){
            param.put("createTimeStart",orderCreateTimeStartAndEnd.get(0));
            param.put("createTimeEnd",orderCreateTimeStartAndEnd.get(1));
        }
    }

    /**
     * 预计付款时间区间
     * @param param
     */
    public void putExpectPaymentTimeStartAndEnd(Map param){
        if (expectPaymentTimeStartAndEnd!=null && expectPaymentTimeStartAndEnd.size()>0){
            param.put("expectPaymentTimeStart",expectPaymentTimeStartAndEnd.get(0));
            param.put("expectPaymentTimeEnd",expectPaymentTimeStartAndEnd.get(1));
        }
    }

    /**
     * 查询类型
     * @param param
     */
    public void putQueryType(Map param){
        // 查询类型: 1 我的成交  2 我的分成 3 我的创建
        if (queryType!=null){
            if (OrderQueryType.MY_ORDER.equals(queryType)) {
                param.put("empCode", CurrentContext.getUserCode());
            } else if (OrderQueryType.MY_PERCENTAGE.equals(queryType)) {
                param.put("peEmpCode", CurrentContext.getUserCode());
            } else if (OrderQueryType.MY_CREATE.equals(queryType)) {
                param.put("createCode", CurrentContext.getUserCode());
            }
        }
    }

    /**
     * 设置地址
     * @param param
     */
    public void putAddress(Map param){
        if (detailAddress!=null){
            param.put("address", detailAddress);
        }
    }

    @Override
    public String toString() {
        return "OrderVO{" +
                "queryType='" + queryType + '\'' +
                ", houseownerPhone='" + houseownerPhone + '\'' +
                ", province='" + province + '\'' +
                ", orderCode='" + orderCode + '\'' +
                ", city='" + city + '\'' +
                ", orderTime=" + orderTime +
                ", region='" + region + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", street='" + street + '\'' +
                ", community='" + community + '\'' +
                ", orderStatusName='" + orderStatusName + '\'' +
                ", detailAddress='" + detailAddress + '\'' +
                ", empCode='" + empCode + '\'' +
                ", empName='" + empName + '\'' +
                ", acreageStart=" + acreageStart +
                ", houseCode='" + houseCode + '\'' +
                ", acreageEnd=" + acreageEnd +
                ", houseownerPhone='" + houseownerPhone + '\'' +
                ", keyword='" + keyword + '\'' +
                ", cusCode='" + cusCode + '\'' +
                ", orderCreateTimeStartAndEnd=" + orderCreateTimeStartAndEnd +
                ", cusPhone='" + cusPhone + '\'' +
                ", expectPaymentTimeStartAndEnd=" + expectPaymentTimeStartAndEnd +
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
                ", communityName='" + communityName + '\'' +
                ", detailAddress='" + detailAddress + '\'' +
                ", parDeptName='" + parDeptName + '\'' +
                ", leaderName='" + leaderName + '\'' +
                ", paybackSum=" + paybackSum +
                ", ivInvoiceTax=" + ivInvoiceTax +
                ", houseType='" + houseType + '\'' +
                ", regioName='" + regioName + '\'' +
                ", houseownerType='" + houseownerType + '\'' +
                ", houseownerName='" + houseownerName + '\'' +
                ", houseownerCompany='" + houseownerCompany + '\'' +
                ", cusCompany='" + cusCompany + '\'' +
                ", perPercentage=" + perPercentage +
                ", perEmpName='" + perEmpName + '\'' +
                ", perWorkNumner='" + perWorkNumner + '\'' +
                ", perDeptName='" + perDeptName + '\'' +
                ", perProfit=" + perProfit +
                ", id=" + id +
                ", cpyCode='" + cpyCode + '\'' +
                ", cpyName='" + cpyName + '\'' +
                ", ownerCode='" + ownerCode + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", ownerDeptCode='" + ownerDeptCode + '\'' +
                ", ownerDeptName='" + ownerDeptName + '\'' +
                ", createCode='" + createCode + '\'' +
                ", createName='" + createName + '\'' +
                ", createDeptCode='" + createDeptCode + '\'' +
                ", createDeptName='" + createDeptName + '\'' +
                ", lastUpdateCode='" + lastUpdateCode + '\'' +
                ", lastUpdateName='" + lastUpdateName + '\'' +
                ", createTime=" + createTime +
                ", lastUpdateTime=" + lastUpdateTime +
                ", isDeleted=" + isDeleted +
                ", remark='" + remark + '\'' +
                '}';
    }

    public Map queryMap() throws IllegalAccessException {
        Map param = new HashMap(16);
        ParamUtil<OrderVO> queryMap = new ParamUtil<OrderVO>();
        param.putAll(queryMap.objectToMap(this,param));
        putOrderCreateTimeStartAndEnd(param);
        putExpectPaymentTimeStartAndEnd(param);
        putQueryType(param);
        putAddress(param);

        param.put("isDeleted",Boolean.FALSE);
        //计算我的分成总数
        param.put("empPercentageCode", CurrentContext.getUserCode());

        return param;
    }
}
