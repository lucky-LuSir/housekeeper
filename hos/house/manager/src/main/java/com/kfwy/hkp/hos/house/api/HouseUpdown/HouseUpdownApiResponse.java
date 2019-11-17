package com.kfwy.hkp.hos.house.api.HouseUpdown;


import com.kfwy.hkp.common.api.AbstractApiRequest;
import com.kfwy.hkp.hos.house.entity.HouseUpdownEntity;

import java.util.List;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/10/31 16:16
 */
public class HouseUpdownApiResponse extends AbstractApiRequest {


     private List<HouseUpdownEntity> data;
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<HouseUpdownEntity> getData() {
        return data;
    }

    public void setData(List<HouseUpdownEntity> data) {
        this.data = data;
    }
}
