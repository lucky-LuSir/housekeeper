package com.kfwy.hkp.controller.hos.house.vo;

import cn.hutool.core.date.DateUtil;
import com.kfwy.hkp.common.utils.ParamUtil;
import com.kfwy.hkp.hos.house.entity.HouseEntity;
import com.kfwy.hkp.sys.file.entity.FileRelationEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author baiye
 */
public class HouseCreateVo extends HouseEntity {

    /**
     * 房源
     */
    protected List<HouseEntity> subHosList;


    //-----------------------------------------------------
    public List<HouseEntity> getSubHosList() {
        return subHosList;
    }

    public void setSubHosList(List<HouseEntity> subHosList) {
        this.subHosList = subHosList;
    }
}
