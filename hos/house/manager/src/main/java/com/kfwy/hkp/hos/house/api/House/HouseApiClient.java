package com.kfwy.hkp.hos.house.api.House;


import com.gniuu.framework.common.utils.HttpRequestUtils;
import com.kfwy.hkp.hos.house.entity.HouseEntity;
import com.kfwy.hkp.hos.house.vo.HouseDataPage;

import java.util.List;
import java.util.Map;


/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/10/31 17:44
 */
public class HouseApiClient {




    public static HouseDataPage getData(Map<String,Object> param, Integer start, Integer end){

        Integer count=0;
        HouseDataPage result =new HouseDataPage();



            HouseApiRequest request = new HouseApiRequest();
            request.setKey("@Housekeeper$");
            request.setStart(start);
            request.setEnd(end);
            HouseApiResponse response = HttpRequestUtils.requestPost(request,"http://mo.kufangwuyou.com/kfwy-office/rs/dataApi/house",
                    HouseApiResponse.class,"application/json;charset=UTF-8");

            result.setData(response.getData());
            result.setCount(response.getCount());


        return result;
    }


}
