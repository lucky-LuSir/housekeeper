package com.kfwy.hkp.hos.house.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.hkp.sys.file.entity.FileRelationEntity;

import java.math.BigDecimal;
import java.util.List;
import com.kfwy.hkp.hos.house.enums.HouseStatus;
import com.kfwy.hkp.hos.house.enums.RepairReturn;

/**
 * Create By hjh on 2018/7/27
 */
public class HouseLocationEntity extends BaseEntity {

    
    // 位置编码
    protected String locationCode;
    // 地址别名
    protected String locationAlias;
    // 省编码
    protected String province;
    // 省名
    protected String provinceName;
    // 城市编码
    protected String city;
    // 城市名称
    protected String cityName;
    // 区域编码
    protected String region;
    // 区域名称
    protected String regionName;
    // 街道编码
    protected String street;
    // 街道名称
    protected String streetName;
    // 详细地址
    protected String detailAddress;

    // 门牌号  v2.8.0 暂不用 0531
    protected String doorNumber;
    // 经度
    protected BigDecimal longitude;
    // 纬度
    protected BigDecimal latitude;

    // 交通配套  v2.8.0 暂不用 0531
    protected String trafficFacilities;
    //社区
    protected String community;
    //社区名称
    protected String communityName;

    //文件  v2.8.0 暂不用 0531
    protected List<FileRelationEntity> fileList;

    //多边形区域  v2.8.0 暂不用 0531
    protected List<HouseLocRegionEntity> locRegionList;

    //是否绘制过多边形区域
    protected boolean hasLocRegion;

    /**
     * 房源状态
     * 专门用于返回
     *
     * @see HouseStatus 热租中hotRenting
     * @see HouseStatus   已成交HasLease
     */
    protected String allHouseStatus;
    protected String allHouseStatusName;


    /**
     * 前端补全状态
     * 专门用于返回
     *
     * @see RepairReturn   要补全needRepair
     * @see RepairReturn   不用补全notRepair
     */
    protected String repairReturn;
    protected String repairReturnName;
    //------------------------------------------


    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getLocationAlias() {
        return locationAlias;
    }

    public void setLocationAlias(String locationAlias) {
        this.locationAlias = locationAlias;
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

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getDoorNumber() {
        return doorNumber;
    }

    public void setDoorNumber(String doorNumber) {
        this.doorNumber = doorNumber;
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

    public String getTrafficFacilities() {
        return trafficFacilities;
    }

    public void setTrafficFacilities(String trafficFacilities) {
        this.trafficFacilities = trafficFacilities;
    }

    public List<FileRelationEntity> getFileList() {
        return fileList;
    }

    public void setFileList(List<FileRelationEntity> fileList) {
        this.fileList = fileList;
    }

    public List<HouseLocRegionEntity> getLocRegionList() {
        return locRegionList;
    }

    public void setLocRegionList(List<HouseLocRegionEntity> locRegionList) {
        this.locRegionList = locRegionList;
    }

    public boolean isHasLocRegion() {
        return hasLocRegion;
    }

    public void setHasLocRegion(boolean hasLocRegion) {
        this.hasLocRegion = hasLocRegion;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getAllHouseStatus() {
        return allHouseStatus;
    }

    public void setAllHouseStatus(String allHouseStatus) {
        this.allHouseStatus = allHouseStatus;
    }

    public String getAllHouseStatusName() {
        if (this.allHouseStatusName == null && null != this.allHouseStatus) {
            this.allHouseStatusName = DictionaryStorage.get(HouseStatus.class.getName(), this.getAllHouseStatus()).getName();
        }
        return allHouseStatusName;
    }

    public void setAllHouseStatusName(String allHouseStatusName) {
        this.allHouseStatusName = allHouseStatusName;
    }

    public String getRepairReturn() {
        return repairReturn;
    }

    public void setRepairReturn(String repairReturn) {
        this.repairReturn = repairReturn;
    }

    public String getRepairReturnName() {
        if (this.repairReturnName == null && null != this.repairReturn) {
            this.repairReturnName = DictionaryStorage.get(RepairReturn.class.getName(), this.getRepairReturn()).getName();
        }
        return repairReturnName;
    }

    public void setRepairReturnName(String repairReturnName) {
        this.repairReturnName = repairReturnName;
    }
}
