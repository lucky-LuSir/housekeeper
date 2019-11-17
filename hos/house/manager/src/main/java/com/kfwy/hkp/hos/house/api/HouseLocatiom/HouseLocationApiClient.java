package com.kfwy.hkp.hos.house.api.HouseLocatiom;


import com.gniuu.framework.common.utils.HttpRequestUtils;
import com.kfwy.hkp.hos.house.api.House.HouseApiRequest;
import com.kfwy.hkp.hos.house.api.House.HouseApiResponse;
import com.kfwy.hkp.hos.house.vo.HouseDataPage;
import com.kfwy.hkp.hos.house.vo.HouseLocationDataPage;

import java.util.Map;


/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/10/31 17:44
 */
public class HouseLocationApiClient {




    public static HouseLocationDataPage getData(Map<String,Object> param, Integer start, Integer end){

        HouseLocationDataPage result =new HouseLocationDataPage();



        HouseLocationApiRequest request = new HouseLocationApiRequest();
            request.setKey("@Housekeeper$");
            request.setStart(start);
            request.setEnd(end);
        HouseLocationApiResponse response = HttpRequestUtils.requestPost(request,"http://mo.kufangwuyou.com/kfwy-office/rs/dataApi/location",
                HouseLocationApiResponse.class,"application/json;charset=UTF-8");

            result.setData(response.getData());
            result.setCount(response.getCount());


        return result;
    }


}
