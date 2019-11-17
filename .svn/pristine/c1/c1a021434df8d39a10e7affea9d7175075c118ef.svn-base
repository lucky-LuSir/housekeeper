package com.kfwy.hkp.hos.house.api.BespeakApi;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.HttpRequestUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.kfwy.hkp.common.utils.ApiConfigUtils;
import com.kfwy.hkp.hos.house.entity.BespeakEntity;

import java.util.List;
import java.util.Map;

/**
 * @Author:ChenJie Created by kfwy_it_009 on 2018/12/10.
 */
public class BespeakApiClient {

    public static PageResult<BespeakEntity> getData(Map<String,Object> param, Integer start, Integer end){

        BespeakApiRequest request = new BespeakApiRequest();
        request.setKey("@Housekeeper$");
        request.setStart(start);
        request.setEnd(end);
        request.setMap(param);

        BespeakApiResponse response =HttpRequestUtils.requestPost(request, ApiConfigUtils.getMessage("bespeakApi-query"),
                BespeakApiResponse.class,"application/json;charset=UTF-8");

        return response.getBespeak();
    }

    public static List<String> findSelectBespeakPower(String userCode,String deptCode){

        BespeakApiRequest request = new BespeakApiRequest();
        request.setKey("@Housekeeper$");
        request.setUserCode(userCode);
        request.setDeptCode(deptCode);
        BespeakApiResponse response = HttpRequestUtils.requestPost(request, ApiConfigUtils.getMessage("bespeakApi-findSelectBespeakPower"),
                BespeakApiResponse.class,"application/json;charset=UTF-8");
        return response.getDeptCodes();
    }

    public static Object update(BespeakEntity bespeakEntity){

        BespeakApiRequest request = new BespeakApiRequest();
        request.setKey("@Housekeeper$");
        bespeakEntity.setEmpCode(CurrentContext.getUserCode());
        request.setEntity(bespeakEntity);
        BespeakApiResponse response = HttpRequestUtils.requestPost(request, ApiConfigUtils.getMessage("bespeakApi-update"),
                BespeakApiResponse.class,"application/json;charset=UTF-8");
        return response;
    }

    public static int updateProcessed(Integer id){

        BespeakApiRequest request = new BespeakApiRequest();
        BespeakEntity bespeakEntity =new BespeakEntity();
        bespeakEntity.setId(id.longValue());
        bespeakEntity.setStatus(2);
        bespeakEntity.setEmpCode(CurrentContext.getUserCode());
        request.setEntity(bespeakEntity);
        BespeakApiResponse response = HttpRequestUtils.requestPost(request, ApiConfigUtils.getMessage("bespeakApi-update"),
                BespeakApiResponse.class,"application/json;charset=UTF-8");
        return response.getI();
    }
}
