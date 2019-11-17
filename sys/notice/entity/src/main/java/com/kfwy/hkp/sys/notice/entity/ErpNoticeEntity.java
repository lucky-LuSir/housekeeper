package com.kfwy.hkp.sys.notice.entity;

import com.gniuu.framework.entity.BaseEntity;

import java.util.Date;

/**
 * @Author:ChenJie Created by kfwy_it_009 on 2018/12/20.
 */
public class ErpNoticeEntity  extends BaseEntity {
    /**
     * 消息编码
     */
    private String ncCode;
    /**
     * 消息设备
     */
    private Integer ncDevice;
    /**
     * 消息标题
     */
    private String title;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 消息类型
     */
    private Integer ncType;
    /**
     * 消息事件
     */
    private String action;
    /**
     * 是否阅读
     */
    private Boolean hasRead;
    /**
     * 是否是所有人员或者部分人员或者个人人员
     */
    private Integer hasAll;
    /**
     * 组编码
     */
    private String groupCode;
    /**
     * 通知时间
     */
    private Date sendTime;
    /**
     * 是否弹框显示
     */
    private Boolean hasBox;
    /**
     * 是否滚动播出
     */
    private Boolean hasRoll;

    /**
     * 是否推到送移动办公app
     */
    private Boolean hasPush;

    public String getNcCode() {
        return ncCode;
    }

    public void setNcCode(String ncCode) {
        this.ncCode = ncCode;
    }

    public Integer getNcDevice() {
        return ncDevice;
    }

    public void setNcDevice(Integer ncDevice) {
        this.ncDevice = ncDevice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getNcType() {
        return ncType;
    }

    public void setNcType(Integer ncType) {
        this.ncType = ncType;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Boolean getHasRead() {
        return hasRead;
    }

    public void setHasRead(Boolean hasRead) {
        this.hasRead = hasRead;
    }

    public Integer getHasAll() {
        return hasAll;
    }

    public void setHasAll(Integer hasAll) {
        this.hasAll = hasAll;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Boolean getHasBox() {
        return hasBox;
    }

    public void setHasBox(Boolean hasBox) {
        this.hasBox = hasBox;
    }

    public Boolean getHasRoll() {
        return hasRoll;
    }

    public void setHasRoll(Boolean hasRoll) {
        this.hasRoll = hasRoll;
    }

    public Boolean getHasPush() {
        return hasPush;
    }

    public void setHasPush(Boolean hasPush) {
        this.hasPush = hasPush;
    }
}
