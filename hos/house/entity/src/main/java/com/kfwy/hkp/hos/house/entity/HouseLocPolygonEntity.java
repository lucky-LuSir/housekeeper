package com.kfwy.hkp.hos.house.entity;

import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.hkp.sys.file.entity.FileRelationEntity;

import java.math.BigDecimal;
import java.util.List;

/**
 * Create By xpp on 2018/7/27
 */
public class HouseLocPolygonEntity extends BaseEntity {


    //多边形序号
    private Integer polygonNum;
    // 位置编码
    private String locationCode;
    // 地址别名
    private String locationAlias;

    // 位置点 经度
    private BigDecimal longitude;
    // 位置点 纬度
    private BigDecimal latitude;
    //一个多边形包含的点列表
    private List<HouseLocRegionEntity> pointRegionList;

    /*--------------字段分割线--------------*/

    public Integer getPolygonNum() {
        return polygonNum;
    }

    public void setPolygonNum(Integer polygonNum) {
        this.polygonNum = polygonNum;
    }

    public List<HouseLocRegionEntity> getPointRegionList() {
        return pointRegionList;
    }

    public void setPointRegionList(List<HouseLocRegionEntity> pointRegionList) {
        this.pointRegionList = pointRegionList;
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
