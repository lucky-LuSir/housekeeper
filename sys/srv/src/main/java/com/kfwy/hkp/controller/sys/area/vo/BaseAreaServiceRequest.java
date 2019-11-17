package com.kfwy.hkp.controller.sys.area.vo;

import com.gniuu.framework.service.AbstractServiceRequest;
import com.kfwy.hkp.sys.area.entity.BaseAreaEntity;

/**
 * Created by xpp on 2018/6/11.
 */
public class BaseAreaServiceRequest extends AbstractServiceRequest<BaseAreaEntity> {

    int enableFlag;
    private BaseAreaEntity provinceObj;
    private BaseAreaEntity cityObj;
    private BaseAreaEntity regionObj;


    public int getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(int enableFlag) {
        this.enableFlag = enableFlag;
    }

    public BaseAreaEntity getProvinceObj() {
        return provinceObj;
    }

    public void setProvinceObj(BaseAreaEntity provinceObj) {
        this.provinceObj = provinceObj;
    }

    public BaseAreaEntity getCityObj() {
        return cityObj;
    }

    public void setCityObj(BaseAreaEntity cityObj) {
        this.cityObj = cityObj;
    }

    public BaseAreaEntity getRegionObj() {
        return regionObj;
    }

    public void setRegionObj(BaseAreaEntity regionObj) {
        this.regionObj = regionObj;
    }

}
