package com.kfwy.hkp.crm.customer.entity;

import com.gniuu.framework.common.enums.SexType;
import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.hkp.common.dic.FireLevelType;
import com.kfwy.hkp.common.enums.DivideType;
import com.kfwy.hkp.crm.customer.enums.*;
import com.kfwy.hkp.sys.file.entity.FileRelationEntity;
import com.kfwy.hkp.sys.user.entity.UserEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 容联云接通记录
 *
 * @author liwensihan
 */
public class RonglianPhoneNoteEntity extends BaseEntity {

    //主叫
    private String callNo;
    //被叫
    private String calledNo;
    //转接前通话的CallSheetID
    private String refCallSheetId;
    //通话记录ID
    private String callSheetId;
    //通话ID
    private String callId;
    //通话类型：dialout外呼通话,normal普通来电,transfer呼入转接,dialTransfer外呼转接
    private String callType;
    //通话振铃时间
    private Date ring;
    //被叫振铃开始时间
    private Date ringingDate;
    //被叫振铃开始时间（呼入是按座席振铃的时间,外呼按客户振铃的时间）
    private Date ringingTimestamp;
    //通话接通时间（双方开始通话的时间,如果被叫没接听的话为空
    private Date begin;
    //通话结束时间
    private Date end;
    //来电进入技能组时间
    private Date queueTime;
    //处理坐席的工号
    private String agent;
    //通话进入的技能组名称
    private String queue;
    //接听状态：dealing（已接）,notDeal（振铃未接听）,leak（ivr放弃）,queueLeak（排队放弃）,blackList（黑名单）,voicemail（留言）,limit（并发限制）
    private String state;
    //事件状态：Ring, Ringing, Link, Hangup(Unlink也当成Hangup处理
    private String callState;
    //目标号码的省
    private String province;
    //目标号码的市
    private String district;
    //通话在系统中选择的按键菜单,10004@0。格式为：按键菜单的节点编号@选择的菜单按键。如果为多级菜单则为：10004@0-10005@1。
    private String ivrkey;

    public RonglianPhoneNoteEntity (String callNo, String calledNo, String refCallSheetId, String callSheetId, String callId, String callType, Date ring, Date ringingDate, Date ringingTimestamp, Date begin, Date end, Date queueTime, String agent, String queue, String state, String callState, String province, String district, String ivrkey) {
        this.callNo = callNo;
        this.calledNo = calledNo;
        this.refCallSheetId = refCallSheetId;
        this.callSheetId = callSheetId;
        this.callId = callId;
        this.callType = callType;
        this.ring = ring;
        this.ringingDate = ringingDate;
        this.ringingTimestamp = ringingTimestamp;
        this.begin = begin;
        this.end = end;
        this.queueTime = queueTime;
        this.agent = agent;
        this.queue = queue;
        this.state = state;
        this.callState = callState;
        this.province = province;
        this.district = district;
        this.ivrkey = ivrkey;
    }

    public RonglianPhoneNoteEntity () {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCallNo() {
        return callNo;
    }

    public void setCallNo(String callNo) {
        this.callNo = callNo;
    }

    public String getCalledNo() {
        return calledNo;
    }

    public void setCalledNo(String calledNo) {
        this.calledNo = calledNo;
    }

    public String getRefCallSheetId() {
        return refCallSheetId;
    }

    public void setRefCallSheetId(String refCallSheetId) {
        this.refCallSheetId = refCallSheetId;
    }

    public String getCallSheetId() {
        return callSheetId;
    }

    public void setCallSheetId(String callSheetId) {
        this.callSheetId = callSheetId;
    }

    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public Date getRing() {
        return ring;
    }

    public void setRing(Date ring) {
        this.ring = ring;
    }

    public Date getRingingDate() {
        return ringingDate;
    }

    public void setRingingDate(Date ringingDate) {
        this.ringingDate = ringingDate;
    }

    public Date getRingingTimestamp() {
        return ringingTimestamp;
    }

    public void setRingingTimestamp(Date ringingTimestamp) {
        this.ringingTimestamp = ringingTimestamp;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Date getQueueTime() {
        return queueTime;
    }

    public void setQueueTime(Date queueTime) {
        this.queueTime = queueTime;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCallState() {
        return callState;
    }

    public void setCallState(String callState) {
        this.callState = callState;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getIvrkey() {
        return ivrkey;
    }

    public void setIvrkey(String ivrkey) {
        this.ivrkey = ivrkey;
    }

}
