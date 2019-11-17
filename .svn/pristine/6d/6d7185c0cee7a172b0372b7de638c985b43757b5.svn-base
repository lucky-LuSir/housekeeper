package com.kfwy.hkp.hos.house.api.EntrustApi;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.HttpRequestUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.kfwy.hkp.common.utils.ApiConfigUtils;
import com.kfwy.hkp.hos.house.entity.BespeakEntity;
import com.kfwy.hkp.hos.house.entity.EntrustEntity;

import java.util.List;
import java.util.Map;

/**
 * @Author:ChenJie Created by kfwy_it_009 on 2018/12/10.
 */
public class EntrustApiClient {

    public static PageResult<EntrustEntity> getData(Map<String,Object> param, Integer start, Integer end){

        EntrustApiRequest request = new EntrustApiRequest();
        request.setKey("@Housekeeper$");
        request.setStart(start);
        request.setEnd(end);
        request.setMap(param);
        EntrustApiResponse response = HttpRequestUtils.requestPost(request, ApiConfigUtils.getMessage("entrustApi-query"),
                EntrustApiResponse.class,"application/json;charset=UTF-8");
        return response.getEntrust();
    }

    public static List<String> findSelectBespeakPower(String userCode,String deptCode){

        EntrustApiRequest request = new EntrustApiRequest();
        request.setKey("@Housekeeper$");
        request.setUserCode(userCode);
        request.setDeptCode(deptCode);
        EntrustApiResponse response = HttpRequestUtils.requestPost(request, ApiConfigUtils.getMessage("bespeakApi-findSelectBespeakPower"),
                EntrustApiResponse.class,"application/json;charset=UTF-8");
        return response.getDeptCodes();
    }

    public static Object update(EntrustEntity entrustEntity){

        EntrustApiRequest request = new EntrustApiRequest();
        request.setKey("@Housekeeper$");
        entrustEntity.setEmpCode(CurrentContext.getUserCode());
        entrustEntity.setEmpName(CurrentContext.getUserInfo().getUserName());
        request.setEntity(entrustEntity);
        EntrustApiResponse response = HttpRequestUtils.requestPost(request, ApiConfigUtils.getMessage("entrustApi-update"),
                EntrustApiResponse.class,"application/json;charset=UTF-8");
        return response;
    }

    public static int updateProcessed(Integer id){
        EntrustApiRequest request = new EntrustApiRequest();
        EntrustEntity entrustEntity=new EntrustEntity();
        entrustEntity.setId(id.longValue());
        entrustEntity.setStatus(3);
        entrustEntity.setEmpCode(CurrentContext.getUserCode());
        entrustEntity.setEmpName(CurrentContext.getUserInfo().getUserName());
        request.setEntity(entrustEntity);
        EntrustApiResponse response = HttpRequestUtils.requestPost(request, ApiConfigUtils.getMessage("entrustApi-update"),
                EntrustApiResponse.class,"application/json;charset=UTF-8");
        return response.getI();
    }
}
