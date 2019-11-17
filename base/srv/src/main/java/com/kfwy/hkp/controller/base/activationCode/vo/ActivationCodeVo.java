package com.kfwy.hkp.controller.base.activationCode.vo;

import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.hkp.common.utils.ParamUtil;

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
public class ActivationCodeVo extends BaseEntity {


    protected String keyword;

    protected String activationCode;

    protected Date createTimeStart;

    protected Date createTimeEnd;

    protected String userName;

    protected String userCode;


    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

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
        ParamUtil<ActivationCodeVo> queryMap = new ParamUtil<ActivationCodeVo>();
        map.putAll(queryMap.objectToMap(this,map));
        return map;
    }

}
