package com.kfwy.hkp.controller.version.vo;


import com.gniuu.framework.service.AbstractServiceRequest;

/**
 * Created by davidcun on 2015-8-26.
 *
 * @author davidcun
 */
public class AppServiceRequest extends AbstractServiceRequest {


    /**
     * 示例0.0.1，0.0.2，1.0.1
     */
    private String version;

    /**
     * 更新类型（1-Android，2-IOS）
     */
    private Integer type;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
