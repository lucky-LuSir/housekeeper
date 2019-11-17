package com.kfwy.hkp.base;

public class FocusCusConfig {

    public static FocusCusConfig focusCusConfig=null;

    /**
     * 房源数量占比
     */
    private Double houseCountRate;
    /**
     * 新增客户数量占比
     */
    private Double addCusCountRate;
    /**
     * 新增房源数量占比
     */
    private Double addHosCountRate;
    /**
     * 客户电话拨打次数占比
     */
    private Double callPhoneCountRate;
    /**
     * 客户跟进次数占比
     */
    private Double followupCount;
    /**
     * 新员工入职时间区间
     */
    private Integer entryTimeRange;

    private Integer entryTimeMoreThanRange;

    /**
     * 集中获客转平台时间
     */
    private String focusToPlatformTime;
    /**
     * 集中获客评价分数取值时间
     */
    private Integer focusMatchTime;
    /**
     * 取值时间单位
     */
    private String afterFormat;
    /**
     * 检查集中获客数量的时间段
     */
    private Integer checkFocusCusCountTime;
    /**
     * 检查集中获客数量上限
     */
    private Integer focusCusCount;
    /**
     * 集中获客总人数
     */
    private Integer focusCusAllCount;
    /**
     * 延時推送间隔时间(分钟)
     */
    private Integer delayNoticeTime;
    /**
     * 给新人的固定名额
     */
    private Integer focusCusCountForNewUser;
    /**
     * 每人一天集中获客数量上限
     */
    private Integer oneDayFocusCusCount;


    private String strategy;

    //平均算法时间区间
    private Integer averageCusRange;

    public Integer getAverageCusRange () {
        if (averageCusRange==null){
            averageCusRange = 7;
        }
        return averageCusRange;
    }

    public void setAverageCusRange (Integer averageCusRange) {
        this.averageCusRange = averageCusRange;
    }

    public String getStrategy () {
        if (strategy == null){
            strategy = "focusCusScoreMatchStrategy";
        }
        return strategy;
    }

    public void setStrategy (String strategy) {
        this.strategy = strategy;
    }

    public Integer getEntryTimeMoreThanRange () {
        return entryTimeMoreThanRange;
    }

    public void setEntryTimeMoreThanRange (Integer entryTimeMoreThanRange) {
        this.entryTimeMoreThanRange = entryTimeMoreThanRange;
    }
    public Integer getOneDayFocusCusCount () {
        return oneDayFocusCusCount;
    }

    public void setOneDayFocusCusCount (Integer oneDayFocusCusCount) {
        this.oneDayFocusCusCount = oneDayFocusCusCount;
    }

    public Integer getCheckOneDayFocusCusCountTime () {
        return checkOneDayFocusCusCountTime;
    }

    public void setCheckOneDayFocusCusCountTime (Integer checkOneDayFocusCusCountTime) {
        this.checkOneDayFocusCusCountTime = checkOneDayFocusCusCountTime;
    }

    /**
     * 检查每天的集中获客上限时间段
     */
    private Integer checkOneDayFocusCusCountTime;

    public Integer getDelayNoticeTime () {
        if (delayNoticeTime==null){
            delayNoticeTime = 20;
        }
        return delayNoticeTime;
    }

    public void setDelayNoticeTime (Integer delayNoticeTime) {
        this.delayNoticeTime = delayNoticeTime;
    }

    public Integer getFocusCusCountForNewUser () {
        return focusCusCountForNewUser;
    }

    public void setFocusCusCountForNewUser (Integer focusCusCountForNewUser) {
        this.focusCusCountForNewUser = focusCusCountForNewUser;
    }
    public Integer getFocusCusAllCount () {
        return focusCusAllCount;
    }

    public void setFocusCusAllCount (Integer focusCusAllCount) {
        this.focusCusAllCount = focusCusAllCount;
    }

    public Integer getFocusCusCount () {
        return focusCusCount;
    }

    public void setFocusCusCount (Integer focusCusCount) {
        this.focusCusCount = focusCusCount;
    }

    public Integer getCheckFocusCusCountTime () {
        return checkFocusCusCountTime;
    }

    public void setCheckFocusCusCountTime (Integer checkFocusCusCountTime) {
        this.checkFocusCusCountTime = checkFocusCusCountTime;
    }

    public String getAfterFormat () {
        return afterFormat;
    }

    public void setAfterFormat (String afterFormat) {
        this.afterFormat = afterFormat;
    }

    public Integer getFocusMatchTime () {
        return focusMatchTime;
    }

    public void setFocusMatchTime (Integer focusMatchTime) {
        this.focusMatchTime = focusMatchTime;
    }

    public String getFocusToPlatformTime () {
        return focusToPlatformTime;
    }

    public void setFocusToPlatformTime (String focusToPlatformTime) {
        this.focusToPlatformTime = focusToPlatformTime;
    }

    public Integer getEntryTimeRange () {
        return entryTimeRange;
    }

    public void setEntryTimeRange (Integer entryTimeRange) {
        this.entryTimeRange = entryTimeRange;
    }

    public Double getHouseCountRate() {
        return houseCountRate;
    }

    public void setHouseCountRate(Double houseCountRate) {
        this.houseCountRate = houseCountRate;
    }

    public Double getAddCusCountRate() {
        return addCusCountRate;
    }

    public void setAddCusCountRate(Double addCusCountRate) {
        this.addCusCountRate = addCusCountRate;
    }

    public Double getAddHosCountRate() {
        return addHosCountRate;
    }

    public void setAddHosCountRate(Double addHosCountRate) {
        this.addHosCountRate = addHosCountRate;
    }

    public Double getCallPhoneCountRate() {
        return callPhoneCountRate;
    }

    public void setCallPhoneCountRate(Double callPhoneCountRate) {
        this.callPhoneCountRate = callPhoneCountRate;
    }

    public Double getFollowupCount() {
        return followupCount;
    }

    public void setFollowupCount(Double followupCount) {
        this.followupCount = followupCount;
    }

}
