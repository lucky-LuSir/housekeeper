package com.kfwy.hkp.hos.house.api.HouseFavorite;


import com.gniuu.framework.common.utils.HttpRequestUtils;
import com.kfwy.hkp.hos.house.api.House.HouseApiRequest;
import com.kfwy.hkp.hos.house.api.House.HouseApiResponse;
import com.kfwy.hkp.hos.house.vo.HouseDataPage;
import com.kfwy.hkp.hos.house.vo.HouseFavoriteDataPage;

import java.util.Map;


/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/10/31 17:44
 */
public class HouseFavoriteApiClient {




    public static HouseFavoriteDataPage getData(Map<String,Object> param, Integer start, Integer end){


        HouseFavoriteDataPage result =new HouseFavoriteDataPage();



            HouseFavoriteApiRequest request = new HouseFavoriteApiRequest();
            request.setKey("@Housekeeper$");
            request.setStart(start);
            request.setEnd(end);
        HouseFavoriteApiResponse response = HttpRequestUtils.requestPost(request,"http://mo.kufangwuyou.com/kfwy-office/rs/dataApi/favorite",
                HouseFavoriteApiResponse.class,"application/json;charset=UTF-8");

            result.setData(response.getData());
            result.setCount(response.getCount());


        return result;
    }


}
