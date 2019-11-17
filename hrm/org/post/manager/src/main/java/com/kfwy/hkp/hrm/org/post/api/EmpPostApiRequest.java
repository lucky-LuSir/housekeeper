package com.kfwy.hkp.hrm.org.post.api;

import com.kfwy.hkp.common.api.AbstractApiRequest;

import java.util.Map;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/10/31 19:08
 */
public class EmpPostApiRequest extends AbstractApiRequest {

    private String keyword;

    private Map<String,Object> param;

    public Map<String, Object> getParam() {
        return param;
    }

    public void setParam(Map<String, Object> param) {
        this.param = param;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
