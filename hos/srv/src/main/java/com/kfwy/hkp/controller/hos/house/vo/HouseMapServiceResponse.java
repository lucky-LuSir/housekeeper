package com.kfwy.hkp.controller.hos.house.vo;

import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.AbstractServiceResponse;
import com.kfwy.hkp.hos.house.dto.HousePriceDto;
import com.kfwy.hkp.hos.house.dto.WareHouseDto;
import com.kfwy.hkp.hos.house.entity.HouseMapEntity;
import com.kfwy.hkp.hos.house.entity.HousePriceEntity;
import com.kfwy.hkp.hos.house.entity.WarehouseEntity;

import java.util.List;
import java.util.Map;

/**
 * Create By hjh on 2018/12/3
 */
public class HouseMapServiceResponse extends AbstractServiceResponse {

    private WareHouseDto wareHouseDto;

    private List<HouseMapEntity> houseEntities;

    private PageResult<WarehouseEntity> pageResult;

    private List<HousePriceDto> priceDtos;

    private HousePriceEntity priceEntity;

    private Map<String, Object> entitys;

    public WareHouseDto getWareHouseDto() {
        return wareHouseDto;
    }

    public void setWareHouseDto(WareHouseDto wareHouseDto) {
        this.wareHouseDto = wareHouseDto;
    }

    public List<HouseMapEntity> getHouseEntities() {
        return houseEntities;
    }

    public void setHouseEntities(List<HouseMapEntity> houseEntities) {
        this.houseEntities = houseEntities;
    }

    public PageResult<WarehouseEntity> getPageResult() {
        return pageResult;
    }

    public void setPageResult(PageResult<WarehouseEntity> pageResult) {
        this.pageResult = pageResult;
    }

    public List<HousePriceDto> getPriceDtos() {
        return priceDtos;
    }

    public void setPriceDtos(List<HousePriceDto> priceDtos) {
        this.priceDtos = priceDtos;
    }

    public HousePriceEntity getPriceEntity() {
        return priceEntity;
    }

    public void setPriceEntity(HousePriceEntity priceEntity) {
        this.priceEntity = priceEntity;
    }

    public Map<String, Object> getEntitys() {
        return entitys;
    }

    public void setEntitys(Map<String, Object> entitys) {
        this.entitys = entitys;
    }
}
