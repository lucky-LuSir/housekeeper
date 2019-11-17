package com.kfwy.hkp.hos.house.entity;

import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.hkp.sys.file.entity.FileRelationEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by davidcun on 2015-12-03
 * 仓库实体信息表
 *
 * @author davidcun
 */
public class WarehouseEntity extends BaseEntity {

    /**
     * 仓库编码
     */
    private String whCode;
    /**
     * 别名
     */
    private String code;
    /**
     * 别名
     */
    private String houseCode;
    /**
     * 别名
     */
    private String houseName;
    /**
     */
    private String houseType;
    /**
     * 仓库名称
     */
    private String whName;

    private String name;
    /**
     * 省
     */
    private String province;
    /**
     * 省份名称
     */
    private String provinceName;
    /**
     * 市
     */
    private String city;
    /**
     * 城市名称
     */
    private String cityName;
    /**
     * 区
     */
    private String region;
    /**
     * 区域名字
     */
    private String regionName;
    /**
     * 街道
     */
    private String street;
    /**
     * 街道名称
     */
    private String streetName;
    /**
     * 地址
     */
    private String address;

    /**
     * 面积-->可用面积
     */
    private Float acreage;
    /**
     * 单价
     */
    private BigDecimal price;
    /**
     * 单位
     */
    private String unit;
    /**
     * 层高，5.3 米
     */
    private Float layerHeight;
    /**
     * 层数，5层
     */
    private Integer layerCount;
    /**
     * 电压
     */
    private String voltage;
    /**
     * 装修类型
     */
    private Integer decorationType;
    /**
     * 仓库结构
     */
    private String warehouseStructure;
    /**
     * 仓库结构
     */
    private String houseStructure;

    private String houseStructureName;
    /**
     * 是否有货台
     */

    private Boolean hasPlatform;
    /**
     * 是否有货梯
     */
    private Boolean hasElevator;
    /**
     * 是否有停车位
     */
    private Boolean hasPark;
    /**
     * 是否有产证
     */
    private Boolean hasCertificate;
    /**
     * 是否可以提供公司注册
     */
    private Boolean canRegistryCompany;
    /**
     * 行业限制
     */
    private String tradeRestriction;
    /**
     * 业主（客户）编号
     */
    private String ownCode;
    /**
     * 业主名称
     */
    private String ownName;
    /**
     * 业主电话
     */
    private String ownPhone;
    /**
     * 业主类型
     */
    private Integer ownProp;

    /**
     * 业主所属公司
     */
    private String ownCompany;

    /**
     * 部门编码
     */
    private String deptCode;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 业务员编码
     */
    private String empCode;
    /**
     * 业务员姓名
     */
    private String empName;
    /**
     * 业务员电话
     */
    private String empPhone;
    /**
     * 最少租期，按照月算
     */
    private Integer lessLease;
    /**
     * 最多租期
     */
    private Integer moreLease;
    /**
     * 描述，介绍
     */
    private String description;
    /**
     * 仓库类型，普通仓库、冷链仓库、高台仓库、危险品仓库
     */
    private Integer warehouseType;

    private String warehouseTypeName;

    private String warehouseMOType;

    /**
     * 经度
     */
    private BigDecimal longitude;
    /**
     * 纬度
     */
    private BigDecimal latitude;


    /**
     * 外部图片主图
     */
    private String picCode;


    /**
     * 是否短租
     */
    private Boolean isShortRent;
    /**
     * 客户头像地址
     */
    private String empUrl;
    /**
     * 物业费，元/㎡/月
     */
    private BigDecimal propertyFee;
    /**
     * 可入住时间
     */
    private Date enterTime;
    /**
     * 当前所在第几层
     */
    private String nowLayer;


    /**
     * 是否可环评
     */
    private Boolean hasEia;

    /**
     * 是否可分割
     */
    private Boolean hasCut;
    /**
     * 消防等级
     */
    private String fireLevel;


    /**********************************库房表添加 基本信息 交通配套 适合行业 房源特色  字段 20171010****************************************/

    private String basicInfo;

    /**
     * 交通配套
     */
    private String trafficFacilities;

    private String forInsdustry;

    private String  features;

    private Boolean hasCrane;
    /**
     * 是否又行车
     */
    private Boolean hasInstallCrane;
    /**
     * 是否是接手房源
     */
    private Boolean hasHandover;
    /**
     * 接手时间
     */
    private Date handoverTime;
    /**
     * 总面积
     */
    private Float totalArea;
    /**
     * 物业公司
     */
    private String propertyCompany;
    /**
     * 最大通行车辆
     */
    private String maxPassCar;
    /**
     * 货梯规格
     */
    private String elevatorStandard;
    /**
     * 货梯数量
     */
    private Integer elevatorNumber;
    /**
     * 有无卫生间
     */
    private Boolean hasBathroom;
    /**
     * 有无办公区域
     */
    private Boolean hasOfficeArea;
    /**
     * 有无排污证
     */
    private Boolean hasDischargeSewage;
    /**
     * 最小分割面积
     */
    private Float minAcreage;
    /**
     * 货梯开门
     */
    private String elevatorDoor;

    // 房源文件
    private List<FileRelationEntity> fileImages;

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
     * 特色编码
     */
    protected String houseNumber;

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Boolean getLeaseNegotiable() {
        return leaseNegotiable;
    }

    public void setLeaseNegotiable(Boolean leaseNegotiable) {
        leaseNegotiable = leaseNegotiable;
    }

    public Integer getBearing() {
        return bearing;
    }

    public void setBearing(Integer bearing) {
        this.bearing = bearing;
    }

    public Integer getOfTheArea() {
        return ofTheArea;
    }

    public void setOfTheArea(Integer ofTheArea) {
        this.ofTheArea = ofTheArea;
    }

    public String getWhCode() {
        return whCode;
    }

    public void setWhCode(String whCode) {
        this.whCode = whCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getWhName() {
        return whName;
    }

    public void setWhName(String whName) {
        this.whName = whName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getAcreage() {
        return acreage;
    }

    public void setAcreage(Float acreage) {
        this.acreage = acreage;
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

    public Float getLayerHeight() {
        return layerHeight;
    }

    public void setLayerHeight(Float layerHeight) {
        this.layerHeight = layerHeight;
    }

    public Integer getLayerCount() {
        return layerCount;
    }

    public void setLayerCount(Integer layerCount) {
        this.layerCount = layerCount;
    }

    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    public Integer getDecorationType() {
        return decorationType;
    }

    public void setDecorationType(Integer decorationType) {
        this.decorationType = decorationType;
    }

    public String getWarehouseStructure() {
        return warehouseStructure;
    }

    public void setWarehouseStructure(String warehouseStructure) {
        this.warehouseStructure = warehouseStructure;
    }

    public String getHouseStructure() {
        return houseStructure;
    }

    public void setHouseStructure(String houseStructure) {
        this.houseStructure = houseStructure;
    }

    public String getHouseStructureName() {
        return houseStructureName;
    }

    public void setHouseStructureName(String houseStructureName) {
        this.houseStructureName = houseStructureName;
    }

    public Boolean getHasPlatform() {
        return hasPlatform;
    }

    public void setHasPlatform(Boolean hasPlatform) {
        this.hasPlatform = hasPlatform;
    }

    public Boolean getHasElevator() {
        return hasElevator;
    }

    public void setHasElevator(Boolean hasElevator) {
        this.hasElevator = hasElevator;
    }

    public Boolean getHasPark() {
        return hasPark;
    }

    public void setHasPark(Boolean hasPark) {
        this.hasPark = hasPark;
    }

    public Boolean getHasCertificate() {
        return hasCertificate;
    }

    public void setHasCertificate(Boolean hasCertificate) {
        this.hasCertificate = hasCertificate;
    }

    public Boolean getCanRegistryCompany() {
        return canRegistryCompany;
    }

    public void setCanRegistryCompany(Boolean canRegistryCompany) {
        this.canRegistryCompany = canRegistryCompany;
    }

    public String getTradeRestriction() {
        return tradeRestriction;
    }

    public void setTradeRestriction(String tradeRestriction) {
        this.tradeRestriction = tradeRestriction;
    }

    public String getOwnCode() {
        return ownCode;
    }

    public void setOwnCode(String ownCode) {
        this.ownCode = ownCode;
    }

    public String getOwnName() {
        return ownName;
    }

    public void setOwnName(String ownName) {
        this.ownName = ownName;
    }

    public String getOwnPhone() {
        return ownPhone;
    }

    public void setOwnPhone(String ownPhone) {
        this.ownPhone = ownPhone;
    }

    public Integer getOwnProp() {
        return ownProp;
    }

    public void setOwnProp(Integer ownProp) {
        this.ownProp = ownProp;
    }

    public String getOwnCompany() {
        return ownCompany;
    }

    public void setOwnCompany(String ownCompany) {
        this.ownCompany = ownCompany;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
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

    public String getEmpPhone() {
        return empPhone;
    }

    public void setEmpPhone(String empPhone) {
        this.empPhone = empPhone;
    }

    public Integer getLessLease() {
        return lessLease;
    }

    public void setLessLease(Integer lessLease) {
        this.lessLease = lessLease;
    }

    public Integer getMoreLease() {
        return moreLease;
    }

    public void setMoreLease(Integer moreLease) {
        this.moreLease = moreLease;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getWarehouseType() {
        return warehouseType;
    }

    public void setWarehouseType(Integer warehouseType) {
        this.warehouseType = warehouseType;
    }

    public String getWarehouseTypeName() {
        return warehouseTypeName;
    }

    public void setWarehouseTypeName(String warehouseTypeName) {
        this.warehouseTypeName = warehouseTypeName;
    }

    public String getWarehouseMOType() {
        return warehouseMOType;
    }

    public void setWarehouseMOType(String warehouseMOType) {
        this.warehouseMOType = warehouseMOType;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getPicCode() {
        return picCode;
    }

    public void setPicCode(String picCode) {
        this.picCode = picCode;
    }

    public Boolean getShortRent() {
        return isShortRent;
    }

    public void setShortRent(Boolean shortRent) {
        isShortRent = shortRent;
    }

    public String getEmpUrl() {
        return empUrl;
    }

    public void setEmpUrl(String empUrl) {
        this.empUrl = empUrl;
    }

    public BigDecimal getPropertyFee() {
        return propertyFee;
    }

    public void setPropertyFee(BigDecimal propertyFee) {
        this.propertyFee = propertyFee;
    }

    public Date getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(Date enterTime) {
        this.enterTime = enterTime;
    }

    public String getNowLayer() {
        return nowLayer;
    }

    public void setNowLayer(String nowLayer) {
        this.nowLayer = nowLayer;
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

    public String getFireLevel() {
        return fireLevel;
    }

    public void setFireLevel(String fireLevel) {
        this.fireLevel = fireLevel;
    }

    public String getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(String basicInfo) {
        this.basicInfo = basicInfo;
    }

    public String getTrafficFacilities() {
        return trafficFacilities;
    }

    public void setTrafficFacilities(String trafficFacilities) {
        this.trafficFacilities = trafficFacilities;
    }

    public String getForInsdustry() {
        return forInsdustry;
    }

    public void setForInsdustry(String forInsdustry) {
        this.forInsdustry = forInsdustry;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public Boolean getHasCrane() {
        return hasCrane;
    }

    public void setHasCrane(Boolean hasCrane) {
        this.hasCrane = hasCrane;
    }

    public Boolean getHasInstallCrane() {
        return hasInstallCrane;
    }

    public void setHasInstallCrane(Boolean hasInstallCrane) {
        this.hasInstallCrane = hasInstallCrane;
    }

    public Boolean getHasHandover() {
        return hasHandover;
    }

    public void setHasHandover(Boolean hasHandover) {
        this.hasHandover = hasHandover;
    }

    public Date getHandoverTime() {
        return handoverTime;
    }

    public void setHandoverTime(Date handoverTime) {
        this.handoverTime = handoverTime;
    }

    public Float getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(Float totalArea) {
        this.totalArea = totalArea;
    }

    public String getPropertyCompany() {
        return propertyCompany;
    }

    public void setPropertyCompany(String propertyCompany) {
        this.propertyCompany = propertyCompany;
    }

    public String getMaxPassCar() {
        return maxPassCar;
    }

    public void setMaxPassCar(String maxPassCar) {
        this.maxPassCar = maxPassCar;
    }

    public String getElevatorStandard() {
        return elevatorStandard;
    }

    public void setElevatorStandard(String elevatorStandard) {
        this.elevatorStandard = elevatorStandard;
    }

    public Integer getElevatorNumber() {
        return elevatorNumber;
    }

    public void setElevatorNumber(Integer elevatorNumber) {
        this.elevatorNumber = elevatorNumber;
    }

    public Boolean getHasBathroom() {
        return hasBathroom;
    }

    public void setHasBathroom(Boolean hasBathroom) {
        this.hasBathroom = hasBathroom;
    }

    public Boolean getHasOfficeArea() {
        return hasOfficeArea;
    }

    public void setHasOfficeArea(Boolean hasOfficeArea) {
        this.hasOfficeArea = hasOfficeArea;
    }

    public Boolean getHasDischargeSewage() {
        return hasDischargeSewage;
    }

    public void setHasDischargeSewage(Boolean hasDischargeSewage) {
        this.hasDischargeSewage = hasDischargeSewage;
    }

    public Float getMinAcreage() {
        return minAcreage;
    }

    public void setMinAcreage(Float minAcreage) {
        this.minAcreage = minAcreage;
    }

    public String getElevatorDoor() {
        return elevatorDoor;
    }

    public void setElevatorDoor(String elevatorDoor) {
        this.elevatorDoor = elevatorDoor;
    }

    public List<FileRelationEntity> getFileImages() {
        return fileImages;
    }

    public void setFileImages(List<FileRelationEntity> fileImages) {
        this.fileImages = fileImages;
    }
}
