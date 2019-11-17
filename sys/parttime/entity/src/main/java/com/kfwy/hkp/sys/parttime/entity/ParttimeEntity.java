package com.kfwy.hkp.sys.parttime.entity;

import com.gniuu.framework.common.enums.SexType;
import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;

import javax.xml.crypto.Data;
import java.util.Date;

/**
 * Create By hjh on 2018/11/6
 */
public class ParttimeEntity extends BaseEntity {
    /**
     * 编码
     */
    protected String ptCode;

    /**
     * 姓名
     */
    protected String ptName;

    /**
     * 性别
     */
    protected String sex;

    /**
     * 性别
     */
    protected String sexName;

    /**
     * 电话号码
     */
    protected String ptPhone;

    public Boolean getWeixin() {
        return isWeixin;
    }

    public void setWeixin(Boolean weixin) {
        isWeixin = weixin;
    }

    /**

     * 职位
     */
    protected String positions;

    /**
     * 职业
     */
    protected String profession;

    /*
    *  公司名称
    * */
    protected String company;

    /*
    *  所属者编码
    * */
    protected String ownerCode;

    /*
    *  所属者名称
    * */
    protected String ownerName;

    /*
    *  所属者电话
    * */
    protected String ownerPhone;

    /*
    *  所属者部门编码
    * */
    protected String ownerDeptCode;

    /*
    *  所属者部门名称
    * */
    protected String ownerDeptName;

    /*
    *  备注
    * */
    protected String remark;

    /**
     * 密码
     */
    protected String password;
    /**
     * 密码
     */
    protected String orgPassword;

    /**
     * 用户来源（1.移动端2.后台3.小程序）
     */
    protected String fromType;

    /**
     * 是否微信
     */
    protected Boolean isWeixin;
    /**
     *
     */
    protected String wxOpenId;
    /**
     * unionId
     */
    protected String wxUnionId;

    public String getPtCode() {
        return ptCode;
    }

    public void setPtCode(String ptCode) {
        this.ptCode = ptCode;
    }

    public String getPtName() {
        return ptName;
    }

    public void setPtName(String ptName) {
        this.ptName = ptName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSexName() {
        if (sexName == null && sex != null) {
            sexName = DictionaryStorage.get(SexType.getKey(), this.sex).getName();
        }
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public String getPtPhone() {
        return ptPhone;
    }

    public void setPtPhone(String ptPhone) {
        this.ptPhone = ptPhone;
    }

    public String getPositions() {
        return positions;
    }

    public void setPositions(String positions) {
        this.positions = positions;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String getOwnerCode() {
        return ownerCode;
    }

    @Override
    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode;
    }

    @Override
    public String getOwnerName() {
        return ownerName;
    }

    @Override
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    @Override
    public String getOwnerDeptCode() {
        return ownerDeptCode;
    }

    @Override
    public void setOwnerDeptCode(String ownerDeptCode) {
        this.ownerDeptCode = ownerDeptCode;
    }

    @Override
    public String getOwnerDeptName() {
        return ownerDeptName;
    }

    @Override
    public void setOwnerDeptName(String ownerDeptName) {
        this.ownerDeptName = ownerDeptName;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOrgPassword() {
        return orgPassword;
    }

    public void setOrgPassword(String orgPassword) {
        this.orgPassword = orgPassword;
    }

    public String getFromType() {
        return fromType;
    }

    public void setFromType(String fromType) {
        this.fromType = fromType;
    }

    public Boolean getIsWeixin() {
        return isWeixin;
    }

    public void setIsWeixin(Boolean isWeixin) {
        isWeixin = isWeixin;
    }

    public String getWxOpenId() {
        return wxOpenId;
    }

    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }

    public String getWxUnionId() {
        return wxUnionId;
    }

    public void setWxUnionId(String wxUnionId) {
        this.wxUnionId = wxUnionId;
    }
}
