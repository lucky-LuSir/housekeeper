package com.kfwy.hkp.cooperate.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.hkp.common.enums.ApplyType;
import com.kfwy.hkp.cooperate.enums.CooStatus;
import com.kfwy.hkp.crm.customer.entity.CustomerAreaEntity;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.crm.customer.enums.CustomerHouseType;
import com.kfwy.hkp.hos.house.entity.HouseEntity;
import com.kfwy.hkp.sys.user.entity.UserEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by zjp on 2018/8/15.
 */
public class CooperateEntity extends BaseEntity {

    /**
     * 合作编号
     */
    protected String cooCode;
    /**
     * 合作状态
     * @see CooStatus
     */
    protected String cooStatus;
    /**
     * 合作状态名
     */
    protected String cooStatusName;
    /**
     * 房源编码
     */
    protected String houseCode;
    protected HouseEntity houseEntity;
    /**
     * 客户编码
     */
    protected String cusCode;
    protected CustomerEntity customerEntity;
    /**
     * 合作申请人编码
     */
    protected String applyCode;
    /**
     * 申请的类型  申请合作的类型 （就是要与什么类型合作：1 客户 2 房源）
     */
    protected String applyType;

    protected String applyTypeName;
    /**
     * 接收人编码
     */
    protected String receiveCode;
    /**
     * 拒绝理由
     */
    protected String rejectReason;

    /**
     * 申请人姓名
     */
    protected String applyName;
    /**
     * 申请人手机号
     */
    protected String applyPhone;
    /**
     * 申请人头像
     */
    protected String applyImg;
    /**
     * 接受人姓名
     */
    protected String receiveName;
    /**
     * 接受人手机号
     */
    protected String receivePhone;
    /**
     * 接受人头像
     */
    protected String receiveImg;
    /**
     *房源经纪人姓名
     */
    protected String hosEmpName;
    /**
     * 房源经纪人手机号
     */
    protected String hosEmpPhone;
    /**
     * 房源经纪人头像
     */
    protected String hosEmpImg;
    /**
     * 客户经纪人姓名
     */
    protected String cusEmpName;
    /**
     * 客户经纪人手机号
     */
    protected String cusEmpPhone;
    /**
     * 客户经纪人头像
     */
    protected String cusEmpImg;

    /*********客户相关字段*********/

    /**
     * 客户姓名
     */
    protected String cusName;
    /**
     * 客户手机号
     */
    protected String cusPhone;
    /**
     * 客户入住时间
     */
    protected Date enterTime;
    /**
     * 客户行业性质
     */
    protected String industry;
    /**
     * 客户产品信息
     */
    protected String products;
    /**
     * 客户需求面积
     */
    protected BigDecimal needAcreage;
    /**
     * 客户需求类型(找房用途)
     */
    protected String houseType;
    /**
     * 客户需求类型名
     */
    protected String houseTypeName;
    /**
     * 客户需求描述
     */
    protected String description;
    /**
     * 需求区域
     */
    protected List<CustomerAreaEntity> customerAreaEntityList;

    /*********房源相关字段*********/

    /**
     * 房源标题
     */
    protected String houseName;
    /**
     * 房源价格
     */
    protected BigDecimal price;
    /**
     * 房源价格单位
     */
    protected String unit;
    /**
     * 房源面积
     */
    protected BigDecimal area;
    /**
     * 房源最大电量
     */
    protected Integer maxElectric;
    /**
     * 房源楼层高
     */
    protected BigDecimal layerHeight;
    /**
     * 房源是否可分割
     */
    protected Boolean hasCut;
    /**
     * 房源是否有产证
     */
    protected Boolean hasCertificate;
    /**
     * 房源是否可注册
     */
    protected Boolean hasRegistry;
    /**
     * 房源是否可环评
     */
    protected Boolean hasEia;
    /**
     * 房源业主名字
     */
    protected String houseownerName;
    /**
     * 房源业主手机号
     */
    protected String houseownerPhone;
    /**
     * 房源省份名字
     */
    protected String provinceName;
    /**
     * 房源城市名字
     */
    protected String cityName;
    /**
     * 房源区域名字
     */
    protected String regionName;
    /**
     * 房源街道名字
     */
    protected String streetName;
    /**
     * 房源位置详情
     */
    protected String detailAddress;

    /**
     * 通用人员信息,我收到的合作中是申请人的信息,我申请的合作中是收到人的信息
     */
    protected UserEntity cooUser;
    /**
     * 分成比例
     */
    protected BigDecimal dividePercentage;

    protected Boolean cooOpenFlag;

    public Boolean getCooOpenFlag() {
        return cooOpenFlag;
    }

    public void setCooOpenFlag(Boolean cooOpenFlag) {
        this.cooOpenFlag = cooOpenFlag;
    }

    public BigDecimal getDividePercentage() {
        return dividePercentage;
    }

    public void setDividePercentage(BigDecimal dividePercentage) {
        this.dividePercentage = dividePercentage;
    }

    public String getApplyTypeName() {
        return DictionaryStorage.get(ApplyType.getKey(),this.applyType).getName();
    }

    public void setApplyTypeName(String applyTypeName) {
        this.applyTypeName = applyTypeName;
    }

    public String getCooCode() {
        return cooCode;
    }

    public void setCooCode(String cooCode) {
        this.cooCode = cooCode;
    }

    public String getCooStatus() {
        return cooStatus;
    }

    public void setCooStatus(String cooStatus) {
        this.cooStatus = cooStatus;
    }

    public String getCooStatusName() {

        if (cooStatusName == null && cooStatus != null) {
            cooStatusName = DictionaryStorage.get(CooStatus.class.getName(), cooStatus).getName();
        }

        return cooStatusName;
    }

    public void setCooStatusName(String cooStatusName) {
        this.cooStatusName = cooStatusName;
    }

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    public HouseEntity getHouseEntity() {
        return houseEntity;
    }

    public void setHouseEntity(HouseEntity houseEntity) {
        this.houseEntity = houseEntity;
    }

    public String getCusCode() {
        return cusCode;
    }

    public void setCusCode(String cusCode) {
        this.cusCode = cusCode;
    }

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }

    public String getApplyCode() {
        return applyCode;
    }

    public void setApplyCode(String applyCode) {
        this.applyCode = applyCode;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public String getReceiveCode() {
        return receiveCode;
    }

    public void setReceiveCode(String receiveCode) {
        this.receiveCode = receiveCode;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public String getApplyPhone() {
        return applyPhone;
    }

    public void setApplyPhone(String applyPhone) {
        this.applyPhone = applyPhone;
    }

    public String getApplyImg() {
        return applyImg;
    }

    public void setApplyImg(String applyImg) {
        this.applyImg = applyImg;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }

    public String getReceiveImg() {
        return receiveImg;
    }

    public void setReceiveImg(String receiveImg) {
        this.receiveImg = receiveImg;
    }

    public String getHosEmpName() {
        return hosEmpName;
    }

    public void setHosEmpName(String hosEmpName) {
        this.hosEmpName = hosEmpName;
    }

    public String getHosEmpPhone() {
        return hosEmpPhone;
    }

    public void setHosEmpPhone(String hosEmpPhone) {
        this.hosEmpPhone = hosEmpPhone;
    }

    public String getHosEmpImg() {
        return hosEmpImg;
    }

    public void setHosEmpImg(String hosEmpImg) {
        this.hosEmpImg = hosEmpImg;
    }

    public String getCusEmpName() {
        return cusEmpName;
    }

    public void setCusEmpName(String cusEmpName) {
        this.cusEmpName = cusEmpName;
    }

    public String getCusEmpPhone() {
        return cusEmpPhone;
    }

    public void setCusEmpPhone(String cusEmpPhone) {
        this.cusEmpPhone = cusEmpPhone;
    }

    public String getCusEmpImg() {
        return cusEmpImg;
    }

    public void setCusEmpImg(String cusEmpImg) {
        this.cusEmpImg = cusEmpImg;
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

    public Date getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(Date enterTime) {
        this.enterTime = enterTime;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public BigDecimal getNeedAcreage() {
        return needAcreage;
    }

    public void setNeedAcreage(BigDecimal needAcreage) {
        this.needAcreage = needAcreage;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getHouseTypeName() {

        if (houseTypeName == null && houseType != null) {
            houseTypeName = DictionaryStorage.get(CustomerHouseType.class.getName(), houseType).getName();
        }

        return houseTypeName;
    }

    public void setHouseTypeName(String houseTypeName) {
        this.houseTypeName = houseTypeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public List<CustomerAreaEntity> getCustomerAreaEntityList() {
        return customerAreaEntityList;
    }

    public void setCustomerAreaEntityList(List<CustomerAreaEntity> customerAreaEntityList) {
        this.customerAreaEntityList = customerAreaEntityList;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public Integer getMaxElectric() {
        return maxElectric;
    }

    public void setMaxElectric(Integer maxElectric) {
        this.maxElectric = maxElectric;
    }

    public BigDecimal getLayerHeight() {
        return layerHeight;
    }

    public void setLayerHeight(BigDecimal layerHeight) {
        this.layerHeight = layerHeight;
    }

    public Boolean getHasCut() {
        return hasCut;
    }

    public void setHasCut(Boolean hasCut) {
        this.hasCut = hasCut;
    }

    public Boolean getHasCertificate() {
        return hasCertificate;
    }

    public void setHasCertificate(Boolean hasCertificate) {
        this.hasCertificate = hasCertificate;
    }

    public Boolean getHasRegistry() {
        return hasRegistry;
    }

    public void setHasRegistry(Boolean hasRegistry) {
        this.hasRegistry = hasRegistry;
    }

    public Boolean getHasEia() {
        return hasEia;
    }

    public void setHasEia(Boolean hasEia) {
        this.hasEia = hasEia;
    }

    public String getHouseownerName() {
        return houseownerName;
    }

    public void setHouseownerName(String houseownerName) {
        this.houseownerName = houseownerName;
    }

    public String getHouseownerPhone() {
        return houseownerPhone;
    }

    public void setHouseownerPhone(String houseownerPhone) {
        this.houseownerPhone = houseownerPhone;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public UserEntity getCooUser() {
        return cooUser;
    }

    public void setCooUser(UserEntity cooUser) {
        this.cooUser = cooUser;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }
}
