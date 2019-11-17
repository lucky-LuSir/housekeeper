package com.kfwy.hkp.sys.area.entity;

import com.gniuu.framework.entity.BaseEntity;

import java.util.List;

/**
 * 这个实体类主要是查询所有省市区街道
 */
public class AreaAllEntity extends BaseEntity {

    //省市区街道code
    private String areaCode;
    //省市区街道name
    private String name;
    //市区街道集合
    private List<AreaAllEntity> cities;


    public String getAreaCode() {
        return areaCode;
    }

    public String getName() {
        return name;
    }

    public List<AreaAllEntity> getCities() {
        return cities;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCities(List<AreaAllEntity> cities) {
        this.cities = cities;
    }
}
