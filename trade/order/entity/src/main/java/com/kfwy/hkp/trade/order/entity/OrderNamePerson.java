package com.kfwy.hkp.trade.order.entity;

/**
 * @Description  中间实体,用于记录系统自动分配分成名称
 * @Auth xpp
 * @Date 2019/8/7
 * @Version 1.0
 */
public class OrderNamePerson {


    // 个人获客奖励 OrderPercentageType.CUSPERSONFEN
    private String acquireCusReward;

    // 个人 公司
    private String psfTypeName;


    /*
     *  客户类
     * */

    // 1客户推送 OrderPercentageType.CUSPUSHFEN
    private String cusPushCode;
    private String cusPushName;


    // 2获客分成 OrderPercentageType.CUSTOMER
    private String acquireCusCode;
    private String acquireCusName;



    /*
     *  带看成交
     * */

    // 3带看 OrderPercentageType.ASSIST_FOLLOW
    private String followupSeeCode;
    private String followupSeeName;

    // 4成交 OrderPercentageType.DEAL
    private String orderDealCode;
    private String orderDealName;

    /*
     *   房源类
     * */

    // 5房源开发 OrderPercentageType.HOUSE
    private String houseDevelopCode;
    private String houseDevelopName;

    // 6房源委托  OrderPercentageType.PROXY
    private String houseEntrustCode;
    private String houseEntrustName;

    //----------------------------------------------------


    public String getAcquireCusReward() {
        return acquireCusReward;
    }

    public void setAcquireCusReward(String acquireCusReward) {
        this.acquireCusReward = acquireCusReward;
    }

    public String getPsfTypeName() {
        return psfTypeName;
    }

    public void setPsfTypeName(String psfTypeName) {
        this.psfTypeName = psfTypeName;
    }

    public String getCusPushCode() {
        return cusPushCode;
    }

    public void setCusPushCode(String cusPushCode) {
        this.cusPushCode = cusPushCode;
    }

    public String getCusPushName() {
        return cusPushName;
    }

    public void setCusPushName(String cusPushName) {
        this.cusPushName = cusPushName;
    }

    public String getAcquireCusCode() {
        return acquireCusCode;
    }

    public void setAcquireCusCode(String acquireCusCode) {
        this.acquireCusCode = acquireCusCode;
    }

    public String getAcquireCusName() {
        return acquireCusName;
    }

    public void setAcquireCusName(String acquireCusName) {
        this.acquireCusName = acquireCusName;
    }

    public String getFollowupSeeCode() {
        return followupSeeCode;
    }

    public void setFollowupSeeCode(String followupSeeCode) {
        this.followupSeeCode = followupSeeCode;
    }

    public String getFollowupSeeName() {
        return followupSeeName;
    }

    public void setFollowupSeeName(String followupSeeName) {
        this.followupSeeName = followupSeeName;
    }

    public String getOrderDealCode() {
        return orderDealCode;
    }

    public void setOrderDealCode(String orderDealCode) {
        this.orderDealCode = orderDealCode;
    }

    public String getOrderDealName() {
        return orderDealName;
    }

    public void setOrderDealName(String orderDealName) {
        this.orderDealName = orderDealName;
    }

    public String getHouseDevelopCode() {
        return houseDevelopCode;
    }

    public void setHouseDevelopCode(String houseDevelopCode) {
        this.houseDevelopCode = houseDevelopCode;
    }

    public String getHouseDevelopName() {
        return houseDevelopName;
    }

    public void setHouseDevelopName(String houseDevelopName) {
        this.houseDevelopName = houseDevelopName;
    }

    public String getHouseEntrustCode() {
        return houseEntrustCode;
    }

    public void setHouseEntrustCode(String houseEntrustCode) {
        this.houseEntrustCode = houseEntrustCode;
    }

    public String getHouseEntrustName() {
        return houseEntrustName;
    }

    public void setHouseEntrustName(String houseEntrustName) {
        this.houseEntrustName = houseEntrustName;
    }
}
