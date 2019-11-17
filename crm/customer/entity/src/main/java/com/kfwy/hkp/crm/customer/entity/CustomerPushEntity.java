package com.kfwy.hkp.crm.customer.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.hkp.crm.customer.enums.CustomerPushType;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/10/25 15:44
 * 推送及接收编码，推送的时候可以选择推送给个人或者部门
 */
public class CustomerPushEntity extends BaseEntity {

    /**
     * 推荐人的编码
     */
    private String pushCode;
    /**
     * 推送者姓名
     */
    private String pushName;

    /**
     * 推荐类型：部门或者个人
     *
     * @see CustomerPushType
     */
    private String pushType;

    /**
     * 推荐类型名称
     */
    private String pushTypeName;

    /**
     * 接收人的编码
     */
    private String receiveCode;
    /**
     * 接收者姓名
     */
    private String receiveName;

    /**
     * 接收部门编码
     */
    private String receiveDeptCode;

    /**
     * 接收部门编码
     */
    private String receiveDeptName;

    /**
     * 推荐的客户编码
     */
    private String cusCode;

    /**
     * 推荐的客户信息
     */
    private CustomerEntity customer;


    private String queryType;
    /**
     * 是否公开
     */
    private boolean pushOpenFlag;

    /**
     * 推送留言
     */
    private String pushMessage;

    public String getPushMessage() {
        return pushMessage;
    }

    public void setPushMessage(String pushMessage) {
        this.pushMessage = pushMessage;
    }

    public boolean isPushOpenFlag() {
        return pushOpenFlag;
    }

    public void setPushOpenFlag(boolean pushOpenFlag) {
        this.pushOpenFlag = pushOpenFlag;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public String getPushName() {
        return pushName;
    }

    public void setPushName(String pushName) {
        this.pushName = pushName;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getReceiveDeptName() {
        return receiveDeptName;
    }

    public void setReceiveDeptName(String receiveDeptName) {
        this.receiveDeptName = receiveDeptName;
    }

    public String getPushCode() {
        return pushCode;
    }

    public void setPushCode(String pushCode) {
        this.pushCode = pushCode;
    }

    public String getPushType() {
        return pushType;
    }

    public void setPushType(String pushType) {
        this.pushType = pushType;
    }

    public String getPushTypeName() {
        if (this.pushType != null && this.pushType.equals("集中获客")) {
            return "集中获客";
        }
        return DictionaryStorage.get(CustomerPushType.getKey(), this.getPushType()).getName();
    }

    public void setPushTypeName(String pushTypeName) {
        this.pushTypeName = pushTypeName;
    }

    public String getReceiveCode() {
        return receiveCode;
    }

    public void setReceiveCode(String receiveCode) {
        this.receiveCode = receiveCode;
    }

    public String getReceiveDeptCode() {
        return receiveDeptCode;
    }

    public void setReceiveDeptCode(String receiveDeptCode) {
        this.receiveDeptCode = receiveDeptCode;
    }

    public String getCusCode() {
        return cusCode;
    }

    public void setCusCode(String cusCode) {
        this.cusCode = cusCode;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }
}
