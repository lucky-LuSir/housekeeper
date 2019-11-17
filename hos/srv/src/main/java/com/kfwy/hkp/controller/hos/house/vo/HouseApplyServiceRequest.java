package com.kfwy.hkp.controller.hos.house.vo;

import com.gniuu.framework.service.AbstractServiceRequest;
import com.kfwy.hkp.hos.house.entity.HouseApplyEntity;

/**
 * Create By hjh on 2018/8/10
 */
public class HouseApplyServiceRequest extends AbstractServiceRequest<HouseApplyEntity> {

    // 区域编码
    private String region;

    private String houseCode;

    private String queryType;

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
