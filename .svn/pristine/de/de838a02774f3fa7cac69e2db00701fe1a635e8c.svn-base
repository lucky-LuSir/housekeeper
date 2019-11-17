package com.kfwy.hkp.trade.order.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.kfwy.hkp.trade.order.enums.OrderUpdateApplyStatusType;

import java.util.List;

/**
 * 订单修改申请实体类
 */
public class OrderUpdateApplyEntity extends OrderEntity{

    /**
     * 订单修改申请code
     */
    protected String applyCode;

    /**
     * 订单修改申请状态
     */
    protected String applyStatus;
    protected String applyStatusName;

    /**
     * 审核理由
     * 一般是驳回理由
     */
    protected String auditReason;

    /**
     * 申请理由
     */
    protected String applyReason;

    /**
     * 要修改哪些字段  list形式
     */
    protected List<String> updateFieldsList;
    /**
     * 要修改哪些字段  z字符串形式 逗号隔开  存数据库
     */
    protected String updateFields;
    /**
     * 修改内容拼接
     */
    protected String updateContent;

    /**
     * 最后审批人
     */
    protected String lastUpdateName;

    /**
     * 分成人分成明细
     */
    protected List<OrderPercentageApplyEntity> orderPercentageApplyEntityList;
    /**
     * 客服分成明细
     */
    protected List<OrderPercentageApplyEntity> cusServicePercentageApplyEntityList;

    public String getAuditReason() {
        return auditReason;
    }

    public void setAuditReason(String auditReason) {
        this.auditReason = auditReason;
    }

    public String getApplyStatusName() {
        if(this.applyStatusName == null && this.applyStatus != null){
            this.applyStatusName = DictionaryStorage.get(OrderUpdateApplyStatusType.class.getName(), String.valueOf(this.applyStatus)).getName();

        }
        return applyStatusName;
    }

    public List<OrderPercentageApplyEntity> getOrderPercentageApplyEntityList() {
        return orderPercentageApplyEntityList;
    }

    public void setOrderPercentageApplyEntityList(List<OrderPercentageApplyEntity> orderPercentageApplyEntityList) {
        this.orderPercentageApplyEntityList = orderPercentageApplyEntityList;
    }

    public List<OrderPercentageApplyEntity> getCusServicePercentageApplyEntityList() {
        return cusServicePercentageApplyEntityList;
    }

    public void setCusServicePercentageApplyEntityList(List<OrderPercentageApplyEntity> cusServicePercentageApplyEntityList) {
        this.cusServicePercentageApplyEntityList = cusServicePercentageApplyEntityList;
    }

    public void setApplyStatusName(String applyStatusName) {
        this.applyStatusName = applyStatusName;
    }

    public String getApplyCode() {
        return applyCode;
    }

    public void setApplyCode(String applyCode) {
        this.applyCode = applyCode;
    }

    public String getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }

    public List<String> getUpdateFieldsList() {
        return updateFieldsList;
    }

    public void setUpdateFieldsList(List<String> updateFieldsList) {
        this.updateFieldsList = updateFieldsList;
    }

    public String getUpdateFields() {
        return updateFields;
    }

    public void setUpdateFields(String updateFields) {
        this.updateFields = updateFields;
    }

    public String getUpdateContent() {
        return updateContent;
    }

    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent;
    }
}
