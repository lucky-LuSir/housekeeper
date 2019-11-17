package com.kfwy.hkp.sys.user.api;

import com.gniuu.framework.service.AbstractServiceRequest;
import com.gniuu.framework.service.AbstractServiceResponse;

/**
 * @description TODO
 * @auth david·cun
 * @date 2019-05-17 16:29
 * @since 1.0
 */
public class SSOServiceResponse extends AbstractServiceResponse {

    private String userCode;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
