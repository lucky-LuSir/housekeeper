package com.kfwy.hkp.hos.house.api.HouseFollowUp;


import com.gniuu.framework.common.utils.HttpRequestUtils;
import com.kfwy.hkp.hos.house.vo.HouseFollowUpDataPage;

import java.util.Map;


/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/10/31 17:44
 */
public class HouseFollowUpApiClient {




    public static HouseFollowUpDataPage getData(Map<String,Object> param, Integer start, Integer end){

        Integer count=0;
        HouseFollowUpDataPage result =new HouseFollowUpDataPage();



        HouseFollowUpApiRequest request = new HouseFollowUpApiRequest();
            request.setKey("@Housekeeper$");
            request.setStart(start);
            request.setEnd(end);
        HouseFollowUpApiResponse response = HttpRequestUtils.requestPost(request,"http://mo.kufangwuyou.com/kfwy-office/rs/dataApi/followup",
                HouseFollowUpApiResponse.class,"application/json;charset=UTF-8");

            result.setData(response.getData());
            result.setCount(response.getCount());


        return result;
    }


}
