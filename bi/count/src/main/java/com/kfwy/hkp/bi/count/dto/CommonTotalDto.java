package com.kfwy.hkp.bi.count.dto;

import com.gniuu.framework.entity.BaseEntity;

import java.util.List;

/**
 * @Auther: HJH
 * @Date: 2019/7/2
 * @Description: TODO
 */
public class CommonTotalDto<T extends BaseEntity> extends BaseEntity {

    private List<T> total1;

    private List<T> total2;

    public List<T> getTotal1() {
        return total1;
    }

    public void setTotal1(List<T> total1) {
        this.total1 = total1;
    }

    public List<T> getTotal2() {
        return total2;
    }

    public void setTotal2(List<T> total2) {
        this.total2 = total2;
    }
}
