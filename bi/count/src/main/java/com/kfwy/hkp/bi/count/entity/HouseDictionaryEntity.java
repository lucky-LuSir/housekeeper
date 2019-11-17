package com.kfwy.hkp.bi.count.entity;

import com.gniuu.framework.entity.BaseEntity;

/**
 * Create By hjh on 2019/2/25
 */
public class HouseDictionaryEntity extends BaseEntity{

    // 显示名称
    private String showName;
    // 市场园区总量
    private Long marketParkTotal;
    // 园区总量
    private Long parkTotal;
    // 待出租园区
    private Long parkHotRent;
    // 园区已出租
    private Long parkRented;

    // 市场房源总量
    private Long marketHouseTotal;
    // 房源总量
    private Long houseTotal;
    // 待出租房源
    private Long houseHotRent;
    // 房源已出租
    private Long houseRented;

    // 市场房源面积总量
    private Float marketHouseAreaTotal;
    // 房源面积总量
    private Float houseAreaTotal;
    // 待出租房源面积
    private Float houseAreaHotRent;
    // 已出租房源面积
    private Float houseAreaRented;

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public Long getMarketParkTotal() {
        return marketParkTotal;
    }

    public void setMarketParkTotal(Long marketParkTotal) {
        this.marketParkTotal = marketParkTotal;
    }

    public Long getParkTotal() {
        return parkTotal;
    }

    public void setParkTotal(Long parkTotal) {
        this.parkTotal = parkTotal;
    }

    public Long getParkHotRent() {
        return parkHotRent;
    }

    public void setParkHotRent(Long parkHotRent) {
        this.parkHotRent = parkHotRent;
    }

    public Long getParkRented() {
        return parkRented;
    }

    public void setParkRented(Long parkRented) {
        this.parkRented = parkRented;
    }

    public Long getMarketHouseTotal() {
        return marketHouseTotal;
    }

    public void setMarketHouseTotal(Long marketHouseTotal) {
        this.marketHouseTotal = marketHouseTotal;
    }

    public Long getHouseTotal() {
        return houseTotal;
    }

    public void setHouseTotal(Long houseTotal) {
        this.houseTotal = houseTotal;
    }

    public Long getHouseHotRent() {
        return houseHotRent;
    }

    public void setHouseHotRent(Long houseHotRent) {
        this.houseHotRent = houseHotRent;
    }

    public Long getHouseRented() {
        return houseRented;
    }

    public void setHouseRented(Long houseRented) {
        this.houseRented = houseRented;
    }

    public Float getMarketHouseAreaTotal() {
        return marketHouseAreaTotal;
    }

    public void setMarketHouseAreaTotal(Float marketHouseAreaTotal) {
        this.marketHouseAreaTotal = marketHouseAreaTotal;
    }

    public Float getHouseAreaTotal() {
        return houseAreaTotal;
    }

    public void setHouseAreaTotal(Float houseAreaTotal) {
        this.houseAreaTotal = houseAreaTotal;
    }

    public Float getHouseAreaHotRent() {
        return houseAreaHotRent;
    }

    public void setHouseAreaHotRent(Float houseAreaHotRent) {
        this.houseAreaHotRent = houseAreaHotRent;
    }

    public Float getHouseAreaRented() {
        return houseAreaRented;
    }

    public void setHouseAreaRented(Float houseAreaRented) {
        this.houseAreaRented = houseAreaRented;
    }
}
