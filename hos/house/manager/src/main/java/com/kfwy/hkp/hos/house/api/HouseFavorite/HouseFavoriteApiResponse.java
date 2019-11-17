package com.kfwy.hkp.hos.house.api.HouseFavorite;


import com.kfwy.hkp.common.api.AbstractApiRequest;
import com.kfwy.hkp.hos.house.entity.HouseFavoriteEntity;

import java.util.List;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/10/31 16:16
 */
public class HouseFavoriteApiResponse extends AbstractApiRequest {


     private List<HouseFavoriteEntity> data;
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<HouseFavoriteEntity> getData() {
        return data;
    }

    public void setData(List<HouseFavoriteEntity> data) {
        this.data = data;
    }
}
