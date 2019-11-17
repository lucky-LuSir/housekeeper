package com.kfwy.hkp.controller.hos.house.vo;

import com.gniuu.framework.service.AbstractServiceRequest;
import com.kfwy.hkp.hos.house.entity.HouseEntity;
import com.kfwy.hkp.hos.house.entity.HouseLocRegionEntity;
import com.kfwy.hkp.hos.house.entity.HouseLocationEntity;
import com.kfwy.hkp.hos.house.entity.HouseQueryEntity;

/**
 * Create By hjh on 2018/7/31
 */
public class HouseLocationTwoVersionServiceRequest extends AbstractServiceRequest<HouseLocationEntity> {

    private String keyWord;

    

    //--------------------------------------------------------
    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

}
