package com.kfwy.hkp.hos.house.vo;

import com.kfwy.hkp.hos.house.entity.HouseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kfwy_it_005 on 2018/12/4.
 */
public class HouseDataPage implements Serializable {
    private Integer start;
    private Integer end;
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    private List<HouseEntity> data;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public List<HouseEntity> getData() {
        return data;
    }

    public void setData(List<HouseEntity> data) {
        this.data = data;
    }
}
