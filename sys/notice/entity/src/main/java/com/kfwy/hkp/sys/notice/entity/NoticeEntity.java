package com.kfwy.hkp.sys.notice.entity;


import com.gniuu.framework.entity.BaseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jupe on 2018/5/2.
 */
public class NoticeEntity extends BaseEntity {

    protected String noticeCode;
    /**
     * @see com.kfwy.hkp.sys.notice.enums.NoticeType
     */
    protected String noticeType;

    protected String bussinessCode;
    /**
     * @see com.kfwy.hkp.sys.notice.enums.NoticeOperateType
     */
    protected String bussinessType;

    protected String bussinessEmpName;

    protected String bussinessDeptName;

    protected String noticeTitle;

    protected String noticeContent;

    /**
     * 这两个字段是取自record表
     */
    protected Boolean hasRead;

    protected Date sendTime;

    protected String recordCode;
    //集合获客转换平台类型剩余时间
    protected double closeTime;

    protected String noticeMessage;

    public String getNoticeMessage() {
        return noticeMessage;
    }

    public void setNoticeMessage(String noticeMessage) {
        this.noticeMessage = noticeMessage;
    }

    public double getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(double closeTime) {
        this.closeTime = closeTime;
    }


    protected List<NoticeRecordEntity> noticeRecordEntityList = new ArrayList<NoticeRecordEntity>();

    public String getNoticeCode() {
        return noticeCode;
    }

    public void setNoticeCode(String noticeCode) {
        this.noticeCode = noticeCode;
    }

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    public String getBussinessCode() {
        return bussinessCode;
    }

    public void setBussinessCode(String bussinessCode) {
        this.bussinessCode = bussinessCode;
    }

    public String getBussinessType() {
        return bussinessType;
    }

    public void setBussinessType(String bussinessType) {
        this.bussinessType = bussinessType;
    }

    public String getBussinessEmpName() {
        return bussinessEmpName;
    }

    public void setBussinessEmpName(String bussinessEmpName) {
        this.bussinessEmpName = bussinessEmpName;
    }

    public String getBussinessDeptName() {
        return bussinessDeptName;
    }

    public void setBussinessDeptName(String bussinessDeptName) {
        this.bussinessDeptName = bussinessDeptName;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public Boolean getHasRead() {
        return hasRead;
    }

    public void setHasRead(Boolean hasRead) {
        this.hasRead = hasRead;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getRecordCode() {
        return recordCode;
    }

    public void setRecordCode(String recordCode) {
        this.recordCode = recordCode;
    }

    public List<NoticeRecordEntity> getNoticeRecordEntityList() {
        return noticeRecordEntityList;
    }

    public void setNoticeRecordEntityList(List<NoticeRecordEntity> noticeRecordEntityList) {
        this.noticeRecordEntityList = noticeRecordEntityList;
    }
}
