package com.kfwy.hkp.hos.house.dto;


import com.gniuu.framework.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * Created by hjh on 2018/12/11
 */
public class HousePriceDto extends BaseEntity {

    /**
     * 地区名称
     */
    private String name;

    /**
     * 地区平均价格
     */
    private BigDecimal averagePrice;

    /**
     * 地区最大价格
     */
    private BigDecimal maxPrice;
    /**
     * 地区最小价格
     */
    private BigDecimal minPrice;

    /**
     * 仓库类型
     */
    private Integer warehouseType;

    private String yearMonth;

    public Integer getWarehouseType() {
        return warehouseType;
    }

    public void setWarehouseType(Integer warehouseType) {
        this.warehouseType = warehouseType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAveragePrice() {
        return averagePrice.setScale(2, BigDecimal.ROUND_HALF_DOWN);
    }

    public void setAveragePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice.setScale(2, BigDecimal.ROUND_HALF_DOWN);
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public BigDecimal getMinPrice() {
        return minPrice.setScale(2, BigDecimal.ROUND_HALF_DOWN);
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }
}
