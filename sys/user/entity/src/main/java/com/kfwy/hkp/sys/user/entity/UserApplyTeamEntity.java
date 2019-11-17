package com.kfwy.hkp.sys.user.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.hkp.sys.user.enums.ApplyCategory;

/**
 * @Description 用户加入或脱离团队申请
 * @Auth xpp
 * @Date 2019/5/7
 * @Version 1.0
 */
public class UserApplyTeamEntity extends BaseEntity {

    /**
     * 申请记录唯一编码
     */
    private String applyRecordCode;

    /**
     * 申请人编码
     */
    private String applyPersonCode;

    /**
     * 申请人名字
     */
    private String applyPersonName;

    /**
     * 申请人头像
     */
    private String applyPersonImg;

    /**
     * 申请人部门编码(团队编码)
     */
    private String applyDeptCode;

    /**
     * 申请人部门名字(团队编码)
     */
    private String applyDeptName;

    /**
     * 接收人编码
     */
    private String receivePersonCode;

    /**
     * 接收人名字
     */
    private String receivePersonName;

    /**
     * 接收人头像
     */
    private String receivePersonImg;

    /**
     * 接收人部门编码(团队编码)
     */
    private String receiveDeptCode;

    /**
     * 接收人部门名字(团队编码)
     */
    private String receiveDeptName;

    /**
     * 状态:已申请,已共享,已拒绝
     * @see ApplyCategory
     *
     */
    private String applyState;
    private String applyStateName;

    /**
     * 类别:加入join 离开 leave
     * 只用于查询的时候返回,数据库中不存
     */
    private String itemType;

    //-------------------字段分割线----------------


    public String getApplyRecordCode() {
        return applyRecordCode;
    }

    public void setApplyRecordCode(String applyRecordCode) {
        this.applyRecordCode = applyRecordCode;
    }

    public String getApplyPersonCode() {
        return applyPersonCode;
    }

    public void setApplyPersonCode(String applyPersonCode) {
        this.applyPersonCode = applyPersonCode;
    }

    public String getApplyPersonName() {
        return applyPersonName;
    }

    public void setApplyPersonName(String applyPersonName) {
        this.applyPersonName = applyPersonName;
    }

    public String getApplyPersonImg() {
        return applyPersonImg;
    }

    public void setApplyPersonImg(String applyPersonImg) {
        this.applyPersonImg = applyPersonImg;
    }

    public String getApplyDeptCode() {
        return applyDeptCode;
    }

    public void setApplyDeptCode(String applyDeptCode) {
        this.applyDeptCode = applyDeptCode;
    }

    public String getApplyDeptName() {
        return applyDeptName;
    }

    public void setApplyDeptName(String applyDeptName) {
        this.applyDeptName = applyDeptName;
    }

    public String getReceivePersonCode() {
        return receivePersonCode;
    }

    public void setReceivePersonCode(String receivePersonCode) {
        this.receivePersonCode = receivePersonCode;
    }

    public String getReceivePersonName() {
        return receivePersonName;
    }

    public void setReceivePersonName(String receivePersonName) {
        this.receivePersonName = receivePersonName;
    }

    public String getReceivePersonImg() {
        return receivePersonImg;
    }

    public void setReceivePersonImg(String receivePersonImg) {
        this.receivePersonImg = receivePersonImg;
    }

    public String getReceiveDeptCode() {
        return receiveDeptCode;
    }

    public void setReceiveDeptCode(String receiveDeptCode) {
        this.receiveDeptCode = receiveDeptCode;
    }

    public String getReceiveDeptName() {
        return receiveDeptName;
    }

    public void setReceiveDeptName(String receiveDeptName) {
        this.receiveDeptName = receiveDeptName;
    }

    public String getApplyState() {
        return applyState;
    }

    public void setApplyState(String applyState) {
        this.applyState = applyState;
    }

    public String getApplyStateName() {
        if (this.applyStateName == null && null != this.applyState) {
            this.applyStateName = DictionaryStorage.get(ApplyCategory.class.getName(), this.getApplyState()).getName();
        }
        return applyStateName;
    }

    public void setApplyStateName(String applyStateName) {
        this.applyStateName = applyStateName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
}
