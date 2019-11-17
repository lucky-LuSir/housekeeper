package com.kfwy.hkp.crm.customer.entity;

import com.gniuu.framework.entity.BaseEntity;

import java.util.Date;

/**
 * 容联云小号话单记录
 *
 * @author liwensihan
 */
public class RonlianPhoneLittleNoteEntity extends BaseEntity {

    public RonlianPhoneLittleNoteEntity () {
    }

    public RonlianPhoneLittleNoteEntity (Date alertingTime, String called, String recorderId, String callerArea, Date beginTime, String calledArea, String caller, String calledShow, Date connectTime, String result, Date releaseTime, String account, String mappingId, String appId, String callDuration, String userData, Date answerTime, String smallNumberType, String calldisplay, String recordFileUrl) {
        this.alertingTime = alertingTime;
        this.called = called;
        this.recorderId = recorderId;
        this.callerArea = callerArea;
        this.beginTime = beginTime;
        this.calledArea = calledArea;
        this.caller = caller;
        this.calledShow = calledShow;
        this.connectTime = connectTime;
        this.result = result;
        this.releaseTime = releaseTime;
        this.account = account;
        this.mappingId = mappingId;
        this.appId = appId;
        this.callDuration = callDuration;
        this.userData = userData;
        this.answerTime = answerTime;
        this.smallNumberType = smallNumberType;
        this.calldisplay = calldisplay;
        this.recordFileUrl = recordFileUrl;
    }

    //被叫振铃时间,格式为YYYY-MM-DD HH:mm:ss
    private Date alertingTime;
    //被叫真实号码
    private String called;
    //企业本次通话唯一标识id
    private String recorderId;
    //主叫归属地
    private String callerArea;
    //主叫拨通虚拟号码时刻 ，格式为YYYY-MM-DD HH:mm:ss
    private Date beginTime;
    //被叫归属地
    private String calledArea;
    //主叫号码
    private String caller;
    //中间号,小号
    private String calledShow;
    //被叫接通时刻,格式为YYYY-MM-DD HH:mm:ss
    private Date connectTime;
    //通话状态，通话状态的取值请查通话状态区分（0 成功 2无应答 9呼叫失败 11主叫号码与中间号没有关联
    private String result;
    //通话结束时刻,格式为YYYY-MM-DD HH:mm:ss
    private Date releaseTime;
    //帐号编号
    private String account;
    //绑定关系唯一Id
    private String mappingId;
    //应用id
    private String appId;
    //本次通话的时长，单位为秒
    private String callDuration;
    //用户自定义数据
    private String userData;
    //接通时间
    private Date answerTime;
    //小号类型
    private String smallNumberType;
    //0显真实号 1 不显真实号 2强制不显真实号
    private String calldisplay;
    //通话录音地址
    private String recordFileUrl;


    public Date getAlertingTime () {
        return alertingTime;
    }

    public void setAlertingTime (Date alertingTime) {
        this.alertingTime = alertingTime;
    }

    public String getCalled() {
        return called;
    }

    public void setCalled(String called) {
        this.called = called;
    }

    public String getRecorderId() {
        return recorderId;
    }

    public void setRecorderId(String recorderId) {
        this.recorderId = recorderId;
    }

    public String getCallerArea() {
        return callerArea;
    }

    public void setCallerArea(String callerArea) {
        this.callerArea = callerArea;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public String getCalledArea() {
        return calledArea;
    }

    public void setCalledArea(String calledArea) {
        this.calledArea = calledArea;
    }

    public String getCaller() {
        return caller;
    }

    public void setCaller(String caller) {
        this.caller = caller;
    }

    public String getCalledShow() {
        return calledShow;
    }

    public void setCalledShow(String calledShow) {
        this.calledShow = calledShow;
    }

    public Date getConnectTime() {
        return connectTime;
    }

    public void setConnectTime(Date connectTime) {
        this.connectTime = connectTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getReleaseTime () {
        return releaseTime;
    }

    public void setReleaseTime (Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getMappingId() {
        return mappingId;
    }

    public void setMappingId(String mappingId) {
        this.mappingId = mappingId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getCallDuration() {
        return callDuration;
    }

    public void setCallDuration(String callDuration) {
        this.callDuration = callDuration;
    }

    public String getUserData() {
        return userData;
    }

    public void setUserData(String userData) {
        this.userData = userData;
    }

    public Date getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Date answerTime) {
        this.answerTime = answerTime;
    }

    public String getSmallNumberType() {
        return smallNumberType;
    }

    public void setSmallNumberType(String smallNumberType) {
        this.smallNumberType = smallNumberType;
    }

    public String getCalldisplay() {
        return calldisplay;
    }

    public void setCalldisplay(String calldisplay) {
        this.calldisplay = calldisplay;
    }

    public String getRecordFileUrl() {
        return recordFileUrl;
    }

    public void setRecordFileUrl(String recordFileUrl) {
        this.recordFileUrl = recordFileUrl;
    }
}
