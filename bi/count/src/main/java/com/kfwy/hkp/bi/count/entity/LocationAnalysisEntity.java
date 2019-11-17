package com.kfwy.hkp.bi.count.entity;

import com.gniuu.framework.entity.BaseEntity;

/**
 * @Description LocationAnalysisEntity
 * @Author xpp
 * @Date 2019/2/25 下午2:40
 * @Version 1.0
 **/
public class LocationAnalysisEntity extends BaseEntity {

    //位置别名
    private String locationAlias;
    //字段名
    private String wordName;
    //热租中
    private String heatRent;
    //已出租
    private String alreadyRent;
    //将到期
    private String willExpire;

    //end 字段


    public String getLocationAlias() {
        return locationAlias;
    }

    public void setLocationAlias(String locationAlias) {
        this.locationAlias = locationAlias;
    }

    public String getWordName() {
        return wordName;
    }

    public void setWordName(String wordName) {
        this.wordName = wordName;
    }

    public String getHeatRent() {
        return heatRent;
    }

    public void setHeatRent(String heatRent) {
        this.heatRent = heatRent;
    }

    public String getAlreadyRent() {
        return alreadyRent;
    }

    public void setAlreadyRent(String alreadyRent) {
        this.alreadyRent = alreadyRent;
    }

    public String getWillExpire() {
        return willExpire;
    }

    public void setWillExpire(String willExpire) {
        this.willExpire = willExpire;
    }
}
