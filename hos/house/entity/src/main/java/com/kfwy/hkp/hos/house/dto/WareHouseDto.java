package com.kfwy.hkp.hos.house.dto;

import com.gniuu.framework.service.AbstractDto;
import com.kfwy.hkp.hos.house.entity.WarehouseEntity;

/**
 * Create By hjh on 2018/12/4
 */
public class WareHouseDto extends AbstractDto {

    private WarehouseEntity warehouseEntity;

    private int houseCount;

    private int cusCount;

    public WarehouseEntity getWarehouseEntity() {
        return warehouseEntity;
    }

    public void setWarehouseEntity(WarehouseEntity warehouseEntity) {
        this.warehouseEntity = warehouseEntity;
    }

    public int getHouseCount() {
        return houseCount;
    }

    public void setHouseCount(int houseCount) {
        this.houseCount = houseCount;
    }

    public int getCusCount() {
        return cusCount;
    }

    public void setCusCount(int cusCount) {
        this.cusCount = cusCount;
    }
}
