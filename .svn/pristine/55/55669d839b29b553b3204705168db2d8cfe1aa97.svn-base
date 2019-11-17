package com.kfwy.hkp.hos.house.api.HouseUpdown;


import com.gniuu.framework.common.utils.HttpRequestUtils;
import com.kfwy.hkp.hos.house.api.House.HouseApiRequest;
import com.kfwy.hkp.hos.house.api.House.HouseApiResponse;
import com.kfwy.hkp.hos.house.vo.HouseDataPage;
import com.kfwy.hkp.hos.house.vo.HouseUpdownDataPage;

import java.util.Map;


/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/10/31 17:44
 */
public class HouseUpdownApiClient {




    public static HouseUpdownDataPage getData(Map<String,Object> param, Integer start, Integer end){


        HouseUpdownDataPage result =new HouseUpdownDataPage();



            HouseUpdownApiRequest request = new HouseUpdownApiRequest();
            request.setKey("@Housekeeper$");
            request.setStart(start);
            request.setEnd(end);
            HouseUpdownApiResponse response = HttpRequestUtils.requestPost(request,"http://mo.kufangwuyou.com/kfwy-office/rs/dataApi/updown",
                HouseUpdownApiResponse.class,"application/json;charset=UTF-8");

            result.setData(response.getData());
            result.setCount(response.getCount());


        return result;
    }


}
