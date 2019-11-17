package com.kfwy.hkp.hos.house.api.RecommendApi;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.HttpRequestUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.kfwy.hkp.common.utils.ApiConfigUtils;
import com.kfwy.hkp.hos.house.entity.EntrustEntity;
import com.kfwy.hkp.hos.house.entity.RecommendEntity;

import java.util.List;
import java.util.Map;

/**
 * @Author:ChenJie Created by kfwy_it_009 on 2018/12/10.
 */
public class RecommendApiClient {

    public static PageResult<RecommendEntity> getData(Map<String,Object> param, Integer start, Integer end){

        RecommendApiRequest request = new RecommendApiRequest();
        request.setKey("@Housekeeper$");
        request.setStart(start);
        request.setEnd(end);
        request.setMap(param);

        RecommendApiResponse response = HttpRequestUtils.requestPost(request, ApiConfigUtils.getMessage("recommendApi-query"),
                RecommendApiResponse.class,"application/json;charset=UTF-8");

        return response.getRecommend();
    }

    public static List<String> findSelectBespeakPower(String userCode,String deptCode){

        RecommendApiRequest request = new RecommendApiRequest();
        request.setKey("@Housekeeper$");
        request.setUserCode(userCode);
        request.setDeptCode(deptCode);
        RecommendApiResponse response = HttpRequestUtils.requestPost(request, ApiConfigUtils.getMessage("bespeakApi-findSelectBespeakPower"),
                RecommendApiResponse.class,"application/json;charset=UTF-8");
        return response.getDeptCodes();
    }

    public static Object update(RecommendEntity recommendEntity){

        RecommendApiRequest request = new RecommendApiRequest();
        request.setKey("@Housekeeper$");
        request.setEntity(setData(recommendEntity));
        RecommendApiResponse response = HttpRequestUtils.requestPost(request, ApiConfigUtils.getMessage("recommendApi-update"),
                RecommendApiResponse.class,"application/json;charset=UTF-8");
        return response;
    }

    public static int updateProcessed(Integer id,String businessCode){

        RecommendApiRequest request = new RecommendApiRequest();
        RecommendEntity recommendEntity =new RecommendEntity();
        recommendEntity.setId(id.longValue());
        recommendEntity.setStatus(3);
        recommendEntity.setBusinessCode(businessCode);
        request.setEntity(setData(recommendEntity));
        RecommendApiResponse response = HttpRequestUtils.requestPost(request, ApiConfigUtils.getMessage("recommendApi-update"),
                RecommendApiResponse.class,"application/json;charset=UTF-8");
        return response.getI();
    }

    /**
     * 设置操作业务专员的信息
     * @param recommendEntity
     * @return
     */
    private static RecommendEntity setData(RecommendEntity recommendEntity){
        recommendEntity.setEmpCode(CurrentContext.getUserCode());
        recommendEntity.setEmpName(CurrentContext.getUserInfo().getUserName());
        recommendEntity.setDeptCode(CurrentContext.getUserInfo().getOwnerDeptCode());
        return  recommendEntity;
    }
}
