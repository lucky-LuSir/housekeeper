package com.kfwy.hkp.hos.house.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.hkp.hos.house.enums.*;

import java.math.BigDecimal;
import java.util.*;
public class HouseQueryEntity extends BaseEntity {


    /**
     * 未跟进天数
     */
    protected String orderFlag;

    protected Integer daysNotFollowup;

    protected String daysNotFollowupType;

    protected String daysNotFollowupTypeName;
    /**
     * 查询类型
     */
    protected String queryType;
    /**
     * 房源编号
     */
    protected String houseCode;
    /**
     * 房源名
     */
    protected String houseName;
    /**
     * 房源编号
     */
    protected String houseNumber;

    /**
     * 房源类型
     */
    protected String houseType;
    /**
     * 房源状态
     */
    protected String houseStatus;
    /**
     * 房源用途
     */
    protected String housePurpose;
    /**
     * 员工编号
     */
    protected String empCode;
    /**
     * 员工名
     */
    protected String empName;
    /**
     * 业主电话
     */
    protected String ownerPhone;
    /**
     * 消防等级
     */
    protected String fireLevel;
    /**
     * 是否有环评
     */
    protected Boolean hasEia;
    /**
     * 是否可分割
     */
    protected Boolean hasCut;
    /**
     * 是否有产证
     */
    protected Boolean hasCertificate;
    /**
     * 是否可注册
     */
    protected Boolean hasRegistry;
    /**
     * 是否可以短租
     */
    protected Boolean hasShortLease;
    /**
     * 是否有停车场
     */
    protected Boolean hasParking;

    /**
     * 是否有行车
     */
    protected Boolean hasInstallCrane;

    /**
     * 最小面积
     */
    protected BigDecimal minArea;
    /**
     * 最大面积
     */
    protected BigDecimal maxArea;
    /**
     * 最少租金
     */
    protected BigDecimal minPrice;
    /**
     * 最大租金
     */
    protected BigDecimal maxPrice;
    /**
     * 最小电量
     */
    protected Integer minElectric;
    /**
     * 最大电量
     */
    protected Integer maxElectric;

    /**
     * 地址信息
     */
    protected String province;
    /**
     * 城市编号
     */
    protected String city;
    /**
     * 区域编号
     */
    protected String region;
    /**
     * 街道编号
     */
    protected String street;
    /**
     * 社区编号
     */
    protected String community;
    /**
     * 承重
     */
    protected Integer bearing;
    /**
     * 租期可协商
     */
    protected Boolean leaseNegotiable;
    /**
     * 起租面积
     */
    protected Integer ofTheArea;
    /**
     * 详细地址
     */
    protected String detailAddress;
    /**
     * 模糊搜索
     */
    protected String keyword;
    /**
     * 创建人编码
     */
    protected String createCode;
    /**
     * 创建部门编号
     */
    protected String createDeptCode;
    /**
     * 创建开始时间
     */
    protected Date createTimeStart;
    /**
     * 创建结束时间
     */
    protected Date createTimeEnd;
    /**
     * 业主姓名
     */
    protected String houseOwnerName;

    /**
     * 业主类型
     */
    protected String houseownerType;
    /**
     * 层高
     */
    protected BigDecimal layerHeight;
    /**
     * 所在楼层
     */
    protected Integer whereLayer;
    /**
     * 楼层类型  1：1楼   2其他
     */
    protected String floorFlag;
    /**
     * 最早入住时间
     */
    protected Date startEnterTime;
    /**
     * 最晚入住时间
     */
    protected Date endEnterTime;
    /**
     * 所属公司
     */
    protected String companyName;
    /**
     * 行业性质(适合行业)
     */
    protected String forInsdustry;

    // 房源位置编码
    protected String locationCode;
    /**
     * 是否删除
     */
    protected Boolean isDeleted;

    public String getHouseStyle() {
        return houseStyle;
    }

    public void setHouseStyle(String houseStyle) {
        this.houseStyle = houseStyle;
    }

    /**
     * 房源类型
     */
    protected String houseStyle;

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getOrderFlag() {
        return orderFlag;
    }

    public void setOrderFlag(String orderFlag) {
        this.orderFlag = orderFlag;
    }

    public String getForInsdustry() {
        return forInsdustry;
    }

    public void setForInsdustry(String forInsdustry) {
        this.forInsdustry = forInsdustry;
    }

    public Integer getDaysNotFollowup() {
        return daysNotFollowup;
    }

    public void setDaysNotFollowup(Integer daysNotFollowup) {
        this.daysNotFollowup = daysNotFollowup;
    }

    public String getDaysNotFollowupType() {
        return daysNotFollowupType;
    }

    public void setDaysNotFollowupType(String daysNotFollowupType) {
        this.daysNotFollowupType = daysNotFollowupType;
    }

    public String getDaysNotFollowupTypeName() {
        if (this.daysNotFollowupTypeName == null && null != this.daysNotFollowupTypeName) {
            this.daysNotFollowupTypeName = DictionaryStorage.get(DaysNotFollowupType.class.getName(), this.getDaysNotFollowupType()).getName();
        }
        return daysNotFollowupTypeName;
    }

    public void setDaysNotFollowupTypeName(String daysNotFollowupTypeName) {
        this.daysNotFollowupTypeName = daysNotFollowupTypeName;
    }

    public String getHouseOwnerName() {
        return houseOwnerName;
    }

    public void setHouseOwnerName(String houseOwnerName) {
        this.houseOwnerName = houseOwnerName;
    }

    public String getHouseownerType() {
        return houseownerType;
    }

    public void setHouseownerType(String houseownerType) {
        this.houseownerType = houseownerType;
    }


    public BigDecimal getLayerHeight() {
        return layerHeight;
    }

    public void setLayerHeight(BigDecimal layerHeight) {
        this.layerHeight = layerHeight;
    }

    public Integer getWhereLayer() {
        return whereLayer;
    }

    public void setWhereLayer(Integer whereLayer) {
        this.whereLayer = whereLayer;
    }

    public Date getStartEnterTime() {
        return startEnterTime;
    }

    public void setStartEnterTime(Date startEnterTime) {
        this.startEnterTime = startEnterTime;
    }

    public Date getEndEnterTime() {
        return endEnterTime;
    }

    public void setEndEnterTime(Date endEnterTime) {
        this.endEnterTime = endEnterTime;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getHouseStatus() {
        return houseStatus;
    }

    public void setHouseStatus(String houseStatus) {
        this.houseStatus = houseStatus;
    }

    public String getHousePurpose() {
        return housePurpose;
    }

    public void setHousePurpose(String housePurpose) {
        this.housePurpose = housePurpose;
    }

    public String getFireLevel() {
        return fireLevel;
    }

    public void setFireLevel(String fireLevel) {
        this.fireLevel = fireLevel;
    }

    public Boolean getHasEia() {
        return hasEia;
    }

    public void setHasEia(Boolean hasEia) {
        this.hasEia = hasEia;
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

    public Boolean getHasShortLease() {
        return hasShortLease;
    }

    public void setHasShortLease(Boolean hasShortLease) {
        this.hasShortLease = hasShortLease;
    }

    public Boolean getHasParking() {
        return hasParking;
    }

    public void setHasParking(Boolean hasParking) {
        this.hasParking = hasParking;
    }

    public BigDecimal getMinArea() {
        return minArea;
    }

    public void setMinArea(BigDecimal minArea) {
        this.minArea = minArea;
    }

    public BigDecimal getMaxArea() {
        return maxArea;
    }

    public void setMaxArea(BigDecimal maxArea) {
        this.maxArea = maxArea;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Integer getMinElectric() {
        return minElectric;
    }

    public void setMinElectric(Integer minElectric) {
        this.minElectric = minElectric;
    }

    public Integer getMaxElectric() {
        return maxElectric;
    }

    public void setMaxElectric(Integer maxElectric) {
        this.maxElectric = maxElectric;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String getCreateCode() {
        return createCode;
    }

    @Override
    public void setCreateCode(String createCode) {
        this.createCode = createCode;
    }

    @Override
    public String getCreateDeptCode() {
        return createDeptCode;
    }

    @Override
    public void setCreateDeptCode(String createDeptCode) {
        this.createDeptCode = createDeptCode;
    }

    public Date getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(Date createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public Date getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(Date createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public Integer getBearing() {
        return bearing;
    }

    public void setBearing(Integer bearing) {
        this.bearing = bearing;
    }

    public Boolean getLeaseNegotiable() {
        return leaseNegotiable;
    }

    public void setLeaseNegotiable(Boolean leaseNegotiable) {
        this.leaseNegotiable = leaseNegotiable;
    }

    public Integer getOfTheArea() {
        return ofTheArea;
    }

    public void setOfTheArea(Integer ofTheArea) {
        this.ofTheArea = ofTheArea;
    }

    public String getFloorFlag() {
        return floorFlag;
    }

    public void setFloorFlag(String floorFlag) {
        this.floorFlag = floorFlag;
    }

    public Boolean getHasInstallCrane() {
        return hasInstallCrane;
    }

    public void setHasInstallCrane(Boolean hasInstallCrane) {
        this.hasInstallCrane = hasInstallCrane;
    }




}
