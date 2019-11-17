package com.kfwy.hkp.controller.hos.house.vo;

import cn.hutool.core.date.DateUtil;
import com.kfwy.hkp.common.utils.ParamUtil;
import com.kfwy.hkp.hos.house.entity.HouseEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author baiye
 */
public class HouseQueryVo extends HouseEntity {



    protected Date createTimeStart;

    protected Date createTimeEnd;


    public Date getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(Date createTimeStart) {
        if (createTimeStart!=null){
           this.createTimeStart =  DateUtil.beginOfDay(createTimeStart);
        }
    }

    public Date getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(Date createTimeEnd) {
        if (createTimeEnd!=null){
           this.createTimeEnd = DateUtil.endOfDay(DateUtil.date());
        }
    }



    public Map queryMap() throws IllegalAccessException {
        Map map = new HashMap(16);
        map.put("isDeleted",Boolean.FALSE);
        ParamUtil<HouseQueryVo> queryMap = new ParamUtil<HouseQueryVo>();
        map.putAll(queryMap.objectToMap(this,map));
        return map;
    }
}
