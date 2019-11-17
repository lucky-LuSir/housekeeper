package com.kfwy.hkp.crm.customer.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.hkp.hos.house.enums.HousePurposeType;
import com.kfwy.hkp.hos.house.enums.HouseStatus;
import com.kfwy.hkp.sys.file.entity.FileRelationEntity;

import java.math.BigDecimal;
import java.util.List;

/**
 * Create By hjh on 2018/8/30
 */
public class FollowupHouseEntity extends BaseEntity {

    protected String followupCode;

    protected String houseCode;

    protected String houseName;
    // 省名
    protected String provinceName;
    // 城市名称
    protected String cityName;
    // 区域名称
    protected String regionName;
    // 街道名称
    protected String streetName;
    // 面积
    protected BigDecimal area;
    // 价格
    protected BigDecimal price;
    // 价格单位 / 天 月 年
    protected String unit;
    // 最大电量
    protected Integer maxElectric;
    // 层高
    protected BigDecimal layerHeight;
    // 是否需要环评
    protected Boolean hasEia;
    // 是否可分割
    protected Boolean hasCut;
    // 是否有产证
    protected Boolean hasCertificate;
    // 是否可注册
    protected Boolean hasRegistry;
    // 是否可短租
    protected Boolean hasShortLease;
    // 是否有停车位
    protected Boolean hasParking;

    protected HouseLocation houseLocation;

    // 房源状态   2018-10-11加
    protected String houseStatus;
    protected String houseStatusName;

    // 房源用途 / 仓储 生产 仓储生产  2018-10-11加
    protected String housePurpose;
    protected String housePurposeName;

    protected String followupHouseCode;

    public List<FileRelationEntity> fileList;


    public HouseLocation getHouseLocation() {
        HouseLocation houseLocations = new HouseLocation();
        houseLocations.setProvinceName(this.getProvinceName());
        houseLocations.setCityName(this.getCityName());
        houseLocations.setRegionName(this.getRegionName());
        houseLocations.setStreetName(this.getStreetName());
        return houseLocations;
    }

    public void setHouseLocation(HouseLocation houseLocation) {
        this.houseLocation = houseLocation;
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

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
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

    public String getFollowupCode() {
        return followupCode;
    }

    public void setFollowupCode(String followupCode) {
        this.followupCode = followupCode;
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

    public String getHouseStatus() {
        return houseStatus;
    }

    public void setHouseStatus(String houseStatus) {
        this.houseStatus = houseStatus;
    }

    public String getHouseStatusName() {
        if (this.houseStatusName == null && null != this.houseStatus){
            this.houseStatusName = DictionaryStorage.get(HouseStatus.class.getName(),this.getHouseStatus()).getName();
        }
        return houseStatusName;
    }

    public void setHouseStatusName(String houseStatusName) {
        this.houseStatusName = houseStatusName;
    }

    public String getHousePurpose() {
        return housePurpose;
    }

    public void setHousePurpose(String housePurpose) {
        this.housePurpose = housePurpose;
    }

    public String getHousePurposeName() {
        if (this.housePurposeName == null && null != this.housePurpose){
            this.housePurposeName = DictionaryStorage.get(HousePurposeType.class.getName(),this.getHousePurpose()).getName();
        }
        return housePurposeName;
    }

    public void setHousePurposeName(String housePurposeName) {
        this.housePurposeName = housePurposeName;
    }

    public String getFollowupHouseCode() {
        return followupHouseCode;
    }

    public void setFollowupHouseCode(String followupHouseCode) {
        this.followupHouseCode = followupHouseCode;
    }

    public List<FileRelationEntity> getFileList() {
        return fileList;
    }

    public void setFileList(List<FileRelationEntity> fileList) {
        this.fileList = fileList;
    }
}
