package com.kfwy.hkp.sys.quota.entity;

import com.gniuu.framework.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * Create By hjh on 2019/2/28
 */
public class QuotaAchievementEntity extends BaseEntity {

    /**
     * 指标时间
     */
    protected String qaTime;
    /**
     * 指标类型,1:部门指标,2:个人指标
     */
    protected Integer quotaType;
    /**
     * 所属者编码
     */
    protected String ownerCode;
    /**
     * 房源开发指标
     */
    protected Integer hosValue;
    /**
     * 签委托房源指标
     */
    protected Integer hosAuthValue;
    /**
     * 房源跟进指标
     */
    protected Integer hosFollowValue;
    /**
     * 客户开发指标
     */
    protected Integer cusValue;
    /**
     * 客户跟进指标
     */
    protected Integer cusFollowValue;
    /**
     * 有效客户指标
     */
    protected Integer cusEffectiveValue;
    /**
     * 兼职指标
     */
    protected Integer ptValue;
    /**
     * 客户带看指标
     */
    protected Integer seeValue;
    /**
     * 客户谈判
     */
    protected Integer negotiation;
    /**
     * 业主拜访（上门）指标
     */
    protected Integer ownerFollowValue;
    /**
     * 签约指标
     */
    protected Long signing;
    /**
     * 业绩指标
     */
    protected BigDecimal totalMoneyValue;

    public String getQaTime() {
        return qaTime;
    }

    public void setQaTime(String qaTime) {
        this.qaTime = qaTime;
    }

    public Integer getQuotaType() {
        return quotaType;
    }

    public void setQuotaType(Integer quotaType) {
        this.quotaType = quotaType;
    }

    @Override
    public String getOwnerCode() {
        return ownerCode;
    }

    @Override
    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode;
    }

    public Integer getHosValue() {
        return hosValue;
    }

    public void setHosValue(Integer hosValue) {
        this.hosValue = hosValue;
    }

    public Integer getHosAuthValue() {
        return hosAuthValue;
    }

    public void setHosAuthValue(Integer hosAuthValue) {
        this.hosAuthValue = hosAuthValue;
    }

    public Integer getHosFollowValue() {
        return hosFollowValue;
    }

    public void setHosFollowValue(Integer hosFollowValue) {
        this.hosFollowValue = hosFollowValue;
    }

    public Integer getCusValue() {
        return cusValue;
    }

    public void setCusValue(Integer cusValue) {
        this.cusValue = cusValue;
    }

    public Integer getCusFollowValue() {
        return cusFollowValue;
    }

    public void setCusFollowValue(Integer cusFollowValue) {
        this.cusFollowValue = cusFollowValue;
    }

    public Integer getCusEffectiveValue() {
        return cusEffectiveValue;
    }

    public void setCusEffectiveValue(Integer cusEffectiveValue) {
        this.cusEffectiveValue = cusEffectiveValue;
    }

    public Integer getPtValue() {
        return ptValue;
    }

    public void setPtValue(Integer ptValue) {
        this.ptValue = ptValue;
    }

    public Integer getSeeValue() {
        return seeValue;
    }

    public void setSeeValue(Integer seeValue) {
        this.seeValue = seeValue;
    }

    public Integer getNegotiation() {
        return negotiation;
    }

    public void setNegotiation(Integer negotiation) {
        this.negotiation = negotiation;
    }

    public Integer getOwnerFollowValue() {
        return ownerFollowValue;
    }

    public void setOwnerFollowValue(Integer ownerFollowValue) {
        this.ownerFollowValue = ownerFollowValue;
    }

    public Long getSigning() {
        return signing;
    }

    public void setSigning(Long signing) {
        this.signing = signing;
    }

    public BigDecimal getTotalMoneyValue() {
        return totalMoneyValue;
    }

    public void setTotalMoneyValue(BigDecimal totalMoneyValue) {
        this.totalMoneyValue = totalMoneyValue;
    }
}
