package com.kfwy.hkp.hos.house.api.House;


import com.kfwy.hkp.common.api.AbstractApiRequest;
import com.kfwy.hkp.hos.house.entity.HouseEntity;

import java.util.List;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/10/31 16:16
 */
public class HouseApiResponse extends AbstractApiRequest {


     private List<HouseEntity> data;
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<HouseEntity> getData() {
        return data;
    }

    public void setData(List<HouseEntity> data) {
        this.data = data;
    }
}
