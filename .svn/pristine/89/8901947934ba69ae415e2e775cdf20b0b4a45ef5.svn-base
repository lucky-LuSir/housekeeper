package com.kfwy.hkp.crm.customer.entity;

import java.util.Date;
import java.util.List;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.hkp.crm.customer.enums.CustomerApplyHidOrPriStatus;
import com.kfwy.hkp.crm.customer.enums.CustomerApplyHidOrPriType;
import com.kfwy.hkp.crm.customer.enums.CustomerStatus;
import com.kfwy.hkp.crm.customer.enums.CustomerType;

/**
 * 客户申请隐藏或拉私表(THkpCrmCustomerApplyHidOrPri)实体类
 *
 * @author makejava
 * @since 2019-07-27 14:21:14
 */
public class CustomerApplyHidOrPriEntity extends BaseEntity {
    private static final long serialVersionUID = 702227193321208046L;

    protected Long id;
    //编码
    protected String cusApplyHopCode;
    //关联编码
    protected String ownerCode;
    //类型（1.隐藏,2.拉私）
    protected Integer type;

    protected String applyHidOrPriTypeName;

    //审核人编码
    protected String checkCode;
    //审核人姓名
    protected String checkName;
    //状态（1.待审核2.通过3.不通过）
    protected Integer status;
    protected String applyHidOrPriStatusName;
    //创建人
    protected String createCode;
    //创建人姓名
    protected String createName;
    //创建人部门
    protected String createDeptCode;
    //创建时间
    protected Date createTime;
    //最后修改时间
    protected Date lastUpdateTime;
    //是否删除
    protected Boolean isDeleted;
    //原因
    protected String reason;
    //失败原因
    protected String failureReasons;

    protected String cusName;
    protected String cusPhone;
    protected String industry;
    protected String products;
    protected String cusStatus;
    protected String cusStatusName;
    protected Integer cusType;
    protected String cusTypeName;

    protected String daikan;
    protected String dianhua;
    protected Boolean openFlag;
    protected String userImg;
    protected String cusSex;


    protected List<CustomerAreaEntity> customerAreaEntityList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCusApplyHopCode() {
        return cusApplyHopCode;
    }

    public void setCusApplyHopCode(String cusApplyHopCode) {
        this.cusApplyHopCode = cusApplyHopCode;
    }

    public String getOwnerCode() {
        return ownerCode;
    }

    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateCode() {
        return createCode;
    }

    public void setCreateCode(String createCode) {
        this.createCode = createCode;
    }

    @Override
    public String getCreateName() {
        return createName;
    }

    @Override
    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getCreateDeptCode() {
        return createDeptCode;
    }

    public void setCreateDeptCode(String createDeptCode) {
        this.createDeptCode = createDeptCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<CustomerAreaEntity> getCustomerAreaEntityList() {
        return customerAreaEntityList;
    }

    public void setCustomerAreaEntityList(List<CustomerAreaEntity> customerAreaEntityList) {
        this.customerAreaEntityList = customerAreaEntityList;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusPhone() {
        return cusPhone;
    }

    public void setCusPhone(String cusPhone) {
        this.cusPhone = cusPhone;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public String getDianhua() {
        return dianhua;
    }

    public void setDianhua(String dianhua) {
        this.dianhua = dianhua;
    }

    public String getDaikan() {
        return daikan;
    }

    public void setDaikan(String daikan) {
        this.daikan = daikan;
    }

    public String getCusStatus() {
        return cusStatus;
    }

    public void setCusStatus(String cusStatus) {
        this.cusStatus = cusStatus;
    }

    public Integer getCusType() {
        return cusType;
    }

    public void setCusType(Integer cusType) {
        this.cusType = cusType;
    }

    public String getFailureReasons() {
        return failureReasons;
    }

    public void setFailureReasons(String failureReasons) {
        this.failureReasons = failureReasons;
    }

    public Boolean getOpenFlag() {
        return openFlag;
    }

    public void setOpenFlag(Boolean openFlag) {
        this.openFlag = openFlag;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getCusSex() {
        return cusSex;
    }

    public void setCusSex(String cusSex) {
        this.cusSex = cusSex;
    }

    public String getApplyHidOrPriTypeName () {

        if (this.applyHidOrPriTypeName == null && null != this.type) {
            this.applyHidOrPriTypeName = DictionaryStorage.get (CustomerApplyHidOrPriType.class.getName (), String.valueOf (this.getType ())).getName ();
        }

        return applyHidOrPriTypeName;
    }

    public void setApplyHidOrPriTypeName (String applyHidOrPriTypeName) {
        this.applyHidOrPriTypeName = applyHidOrPriTypeName;
    }


    public String getApplyHidOrPriStatusName () {

        if (this.applyHidOrPriStatusName == null && null != this.status) {
            this.applyHidOrPriStatusName = DictionaryStorage.get (CustomerApplyHidOrPriStatus.class.getName (), String.valueOf (this.getStatus ())).getName ();
        }

        return applyHidOrPriStatusName;
    }

    public void setApplyHidOrPriStatusName (String applyHidOrPriStatusName) {
        this.applyHidOrPriStatusName = applyHidOrPriStatusName;
    }


    public String getCusTypeName () {

        if (this.cusTypeName == null && null != this.cusType) {
            this.cusTypeName = DictionaryStorage.get (CustomerType.class.getName (), String.valueOf (this.getCusType ())).getName ();
        }

        return cusTypeName;
    }

    public void setCusTypeName (String cusTypeName) {
        this.cusTypeName = cusTypeName;
    }


    public String getCusStatusName () {

        if (this.cusStatusName == null && null != this.cusStatus) {
            this.cusStatusName = DictionaryStorage.get (CustomerStatus.class.getName (), this.getCusStatus ()).getName ();
        }

        return cusStatusName;
    }

    public void setCusStatusName (String cusStatusName) {
        this.cusStatusName = cusStatusName;
    }

}