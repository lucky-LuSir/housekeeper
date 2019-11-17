package com.kfwy.hkp.sys.user.api;

import com.gniuu.framework.common.utils.HttpRequestUtils;
import com.kfwy.hkp.common.utils.ApiConfigUtils;

/**
 * @description TODO
 * @auth david·cun
 * @date 2019-05-17 16:28
 * @since 1.0
 */
public class SSOApiClient {

    public static String sso(String token){

        SSOServiceRequest request = new SSOServiceRequest();

        request.setToken(token);

        SSOServiceResponse response = HttpRequestUtils.requestPost(request, ApiConfigUtils.getMessage("sso-auth"),
                SSOServiceResponse.class,"application/json;charset=UTF-8");

        if (response.getIsSuccess()){
            return response.getUserCode();
        }

        return null;
    }
}
