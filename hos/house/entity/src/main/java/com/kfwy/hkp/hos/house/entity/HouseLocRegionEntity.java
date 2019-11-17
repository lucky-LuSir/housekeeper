package com.kfwy.hkp.hos.house.entity;

import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.hkp.sys.file.entity.FileRelationEntity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * Create By xpp on 2018/7/27
 */
public class HouseLocRegionEntity extends BaseEntity {


    //区域批次编码,区域唯一名字code
    private String regionBatchCode;
    // 位置编码
    private String locationCode;
    // 地址别名
    private String locationAlias;
    //点顺序
    @NotNull
    @Min(1)
    private Integer num;
    // 经度
    @NotNull
    private BigDecimal longitude;
    // 纬度
    @NotNull
    private BigDecimal latitude;

    /*--------------字段分割线--------------*/


    public String getRegionBatchCode() {
        return regionBatchCode;
    }

    public void setRegionBatchCode(String regionBatchCode) {
        this.regionBatchCode = regionBatchCode;
    }

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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
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
}
