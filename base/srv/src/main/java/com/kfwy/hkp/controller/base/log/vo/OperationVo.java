package com.kfwy.hkp.controller.base.log.vo;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.hkp.common.enums.OperationType;
import com.kfwy.hkp.common.utils.ParamUtil;
import com.kfwy.hkp.log.entity.OperationEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author baiye
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2019/1/19 14:02
 */
public class OperationVo extends OperationEntity {


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
        ParamUtil<OperationVo> queryMap = new ParamUtil<OperationVo>();
        map.putAll(queryMap.objectToMap(this,map));
        return map;
    }

}
