package com.kfwy.hkp.controller.base.log.vo;

import com.kfwy.hkp.common.utils.ParamUtil;
import com.kfwy.hkp.log.entity.BaseOperationEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class BaseOperationVo extends BaseOperationEntity {


    protected String keyword;

    protected Date createTimeStart;

    protected Date createTimeEnd;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Date getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(Date createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public Date getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(Date createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public Map queryMap() throws IllegalAccessException {
        Map map = new HashMap(16);
        map.put("isDeleted",Boolean.FALSE);
        ParamUtil<BaseOperationVo> queryMap = new ParamUtil<BaseOperationVo>();
        map.putAll(queryMap.objectToMap(this,map));
        return map;
    }

}
