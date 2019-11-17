package com.kfwy.hkp.controller.hos.house.vo;

import com.kfwy.hkp.hos.house.entity.HouseFollowupEntity;

import java.util.Date;

public class HouseFollowupVo extends HouseFollowupEntity {
    private Date createTimeStart;

    private Date createTimeEnd;


    public Date getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(Date createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public Date getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(Date createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }
}
