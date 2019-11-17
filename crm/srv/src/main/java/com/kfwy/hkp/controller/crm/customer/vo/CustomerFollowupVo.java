package com.kfwy.hkp.controller.crm.customer.vo;

import com.kfwy.hkp.crm.customer.entity.CustomerFollowupEntity;

import java.util.Date;

/**
 * Created by hjh on 2018/8/24.
 */
public class CustomerFollowupVo extends CustomerFollowupEntity{


    protected Date createTimeStart;

    protected Date createTimeEnd;

    protected Integer handleStatus;

    public Integer getHandleStatus() {
        return handleStatus;
    }

    public void setHandleStatus(Integer handleStatus) {
        this.handleStatus = handleStatus;
    }

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
