package com.kfwy.hkp.controller.crm.customer.vo;

import com.gniuu.framework.dic.DictionaryStorage;
import com.kfwy.hkp.common.utils.ParamUtil;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.crm.customer.enums.CusSortType;
import com.kfwy.hkp.crm.customer.enums.CustomerNotFollowupType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zjp on 2018/8/11.
 */
public class CustomerVo extends CustomerEntity{



    /**
     * 查询类型 :  1 我的 2 经纪人 3 平台 4 收藏
     */

    protected String queryType;

    /**
     * 客户未跟进天数筛选枚举
     */
    protected Integer customerNotFollowupType;

    /**
     * 客户未跟进天数
     */
    protected Integer customerNotFollowupDay;

    /**
     * 需求面积start
     */
    protected BigDecimal needAcreageStart;

    /**
     *  需求面积end
     */
    protected BigDecimal needAcreageEnd;


    /**
     * 需求电量start
     */
    protected BigDecimal needVoltageStart;

    /**
     * 需求电量end
     */
    protected BigDecimal needVoltageEnd;

    /**
     * 要求价格start
     */
    protected BigDecimal needPriceStart;

    /**
     * 要求价格end
     */
    protected BigDecimal needPriceEnd;

    /**
     * 入住时间start
     */
    protected Date enterTimeStart;

    /**
     * 入住时间end
     */
    protected Date enterTimeEnd;

    protected Date createTimeStart;

    protected Date createTimeEnd;

    public Date getCreateTimeStart () {
        return createTimeStart;
    }

    public void setCreateTimeStart (Date createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public Date getCreateTimeEnd () {
        return createTimeEnd;
    }

    public void setCreateTimeEnd (Date createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

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
     * 预约ID
     */
    protected Integer bespeakId;

    /**
     * 委托ID
     */
    public Integer entrustId;

    /**
     * 兼职推荐ID
     */
    protected Integer recommendId;
    protected String cusSortType;
    protected String reason;


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

    public BigDecimal getNeedAcreageStart() {
        return needAcreageStart;
    }

    public void setNeedAcreageStart(BigDecimal needAcreageStart) {
        this.needAcreageStart = needAcreageStart;
    }

    public BigDecimal getNeedAcreageEnd() {
        return needAcreageEnd;
    }

    public void setNeedAcreageEnd(BigDecimal needAcreageEnd) {
        this.needAcreageEnd = needAcreageEnd;
    }

    public BigDecimal getNeedVoltageStart() {
        return needVoltageStart;
    }

    public void setNeedVoltageStart(BigDecimal needVoltageStart) {
        this.needVoltageStart = needVoltageStart;
    }

    public BigDecimal getNeedVoltageEnd() {
        return needVoltageEnd;
    }

    public void setNeedVoltageEnd(BigDecimal needVoltageEnd) {
        this.needVoltageEnd = needVoltageEnd;
    }

    public BigDecimal getNeedPriceStart() {
        return needPriceStart;
    }

    public void setNeedPriceStart(BigDecimal needPriceStart) {
        this.needPriceStart = needPriceStart;
    }

    public BigDecimal getNeedPriceEnd() {
        return needPriceEnd;
    }

    public void setNeedPriceEnd(BigDecimal needPriceEnd) {
        this.needPriceEnd = needPriceEnd;
    }

    public Date getEnterTimeStart() {
        return enterTimeStart;
    }

    public void setEnterTimeStart(Date enterTimeStart) {
        this.enterTimeStart = enterTimeStart;
    }

    public Date getEnterTimeEnd() {
        return enterTimeEnd;
    }

    public void setEnterTimeEnd(Date enterTimeEnd) {
        this.enterTimeEnd = enterTimeEnd;
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

    public Integer getBespeakId() {
        return bespeakId;
    }

    public void setBespeakId(Integer bespeakId) {
        this.bespeakId = bespeakId;
    }

    public Integer getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(Integer entrustId) {
        this.entrustId = entrustId;
    }

    public Integer getRecommendId() {
        return recommendId;
    }

    public void setRecommendId(Integer recommendId) {
        this.recommendId = recommendId;
    }

    public Integer getCustomerNotFollowupDay() {
        return customerNotFollowupDay;
    }

    public void setCustomerNotFollowupDay(Integer customerNotFollowupDay) {
        this.customerNotFollowupDay = customerNotFollowupDay;
    }

    public Integer getCustomerNotFollowupType() {
        return customerNotFollowupType;
    }

    public void setCustomerNotFollowupType(Integer customerNotFollowupType) {
        this.customerNotFollowupType = customerNotFollowupType;
    }

    public String getCusSortType() {
        return cusSortType;
    }

    public void setCusSortType(String cusSortType) {
        this.cusSortType = cusSortType;
    }

    public String getReason() {
        if(reason==null){
            reason="";
        }
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Map queryMap() throws IllegalAccessException {
        Map map = new HashMap(16);
        map.put("isDeleted",Boolean.FALSE);
        ParamUtil<CustomerVo> queryMap = new ParamUtil<CustomerVo>();
        map.putAll(queryMap.objectToMap(this,map));
        return map;
    }

}
