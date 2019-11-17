package com.kfwy.hkp.crm.customer.entity;

import com.gniuu.framework.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * @author liwensihan
 */
public class CustomerHouseEntity extends BaseEntity {
    private String cusCode;
    private String houseCode;
    private Integer followupType;
    private String followupCode;
    private BigDecimal acreage;
    private BigDecimal price;
    private String unit;
    private Integer areaSize;
    private Boolean isLocationAppropriate;
    private Boolean isFloorAppropriate;
    private Boolean isPowerAppropriate;
    private Boolean isEia;
    private Boolean isTax;
    private Boolean isAccommodation;
    private Boolean isTraffic;
    private Boolean isRent;
    private Boolean isPeriod;

    public String getCusCode() {
        return cusCode;
    }

    public void setCusCode(String cusCode) {
        this.cusCode = cusCode;
    }

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    public Integer getFollowupType() {
        return followupType;
    }

    public void setFollowupType(Integer followupType) {
        this.followupType = followupType;
    }

    public String getFollowupCode() {
        return followupCode;
    }

    public void setFollowupCode(String followupCode) {
        this.followupCode = followupCode;
    }

    public BigDecimal getAcreage() {
        return acreage;
    }

    public void setAcreage(BigDecimal acreage) {
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

    public Integer getAreaSize() {
        return areaSize;
    }

    public void setAreaSize(Integer areaSize) {
        this.areaSize = areaSize;
    }

    public Boolean getIsLocationAppropriate() {
        return isLocationAppropriate;
    }

    public void setIsLocationAppropriate(Boolean isLocationAppropriate) {
        this.isLocationAppropriate = isLocationAppropriate;
    }

    public Boolean getIsFloorAppropriate() {
        return isFloorAppropriate;
    }

    public void setIsFloorAppropriate(Boolean isFloorAppropriate) {
        this.isFloorAppropriate = isFloorAppropriate;
    }

    public Boolean getIsPowerAppropriate() {
        return isPowerAppropriate;
    }

    public void setIsPowerAppropriate(Boolean isPowerAppropriate) {
        this.isPowerAppropriate = isPowerAppropriate;
    }

    public Boolean getIsEia() {
        return isEia;
    }

    public void setIsEia(Boolean isEia) {
        this.isEia = isEia;
    }

    public Boolean getIsTax() {
        return isTax;
    }

    public void setIsTax(Boolean isTax) {
        this.isTax = isTax;
    }

    public Boolean getIsAccommodation() {
        return isAccommodation;
    }

    public void setIsAccommodation(Boolean isAccommodation) {
        this.isAccommodation = isAccommodation;
    }

    public Boolean getIsTraffic() {
        return isTraffic;
    }

    public void setIsTraffic(Boolean isTraffic) {
        this.isTraffic = isTraffic;
    }

    public Boolean getIsRent() {
        return isRent;
    }

    public void setRent(Boolean isRent) {
        this.isRent = isRent;
    }

    public Boolean getIsPeriod() {
        return isPeriod;
    }

    public void setIsPeriod(Boolean isPeriod) {
        this.isPeriod = isPeriod;
    }
}
