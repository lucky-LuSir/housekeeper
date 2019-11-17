package com.kfwy.hkp.hos.house.api.RecommendApi;

import com.kfwy.hkp.common.api.AbstractApiRequest;
import com.kfwy.hkp.hos.house.entity.RecommendEntity;

import java.util.Map;

/**
 * @Author:ChenJie Created by kfwy_it_009 on 2018/12/10.
 */
public class RecommendApiRequest extends AbstractApiRequest<RecommendEntity> {
    private Integer start;

    private Integer end;

    private String key;

    private Map<String,Object> map;

    private String userCode;
    private String deptCode;

    @Override
    public Integer getStart() {
        return start;
    }

    @Override
    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public void setKey(String key) {
        this.key = key;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }
}
