package com.kfwy.hkp.hos.house.api.HouseUpdown;



import com.kfwy.hkp.common.api.AbstractApiRequest;
import com.kfwy.hkp.hos.house.entity.HouseEntity;
import com.kfwy.hkp.hos.house.entity.HouseUpdownEntity;


/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/10/31 16:16
 */
public class HouseUpdownApiRequest extends AbstractApiRequest<HouseUpdownEntity> {

    private Integer start;

    private Integer end;

    private String key;

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

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public void setKey(String key) {
        this.key = key;
    }
}
