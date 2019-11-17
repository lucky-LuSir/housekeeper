package com.kfwy.hkp.controller.hos.house.vo;

import com.gniuu.framework.service.AbstractServiceRequest;
import com.kfwy.hkp.hos.house.entity.HouseMapEntity;

import java.util.Map;

/**
 * Create By hjh on 2018/12/3
 */
public class HouseMapServiceRequest extends AbstractServiceRequest<HouseMapEntity> {

    private String key;

    private String houseCode;

    private  Map<String, Object> param;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    public Map<String, Object> getParam() {
        return param;
    }

    public void setParam(Map<String, Object> param) {
        this.param = param;
    }
}
