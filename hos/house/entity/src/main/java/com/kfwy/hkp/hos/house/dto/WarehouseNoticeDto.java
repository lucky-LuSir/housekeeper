package com.kfwy.hkp.hos.house.dto;


import com.gniuu.framework.service.AbstractDto;
import com.kfwy.hkp.hos.house.entity.HouseEntity;

/**
 * Created by jupe on 2018/5/9.
 */
public class WarehouseNoticeDto extends AbstractDto {

    private HouseEntity houseEntity;

    private String type;

    private String createCode;

    private String updateCode;

    public HouseEntity getHouseEntity() {
        return houseEntity;
    }

    public void setHouseEntity(HouseEntity houseEntity) {
        this.houseEntity = houseEntity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreateCode() {
        return createCode;
    }

    public void setCreateCode(String createCode) {
        this.createCode = createCode;
    }

    public String getUpdateCode() {
        return updateCode;
    }

    public void setUpdateCode(String updateCode) {
        this.updateCode = updateCode;
    }
}
