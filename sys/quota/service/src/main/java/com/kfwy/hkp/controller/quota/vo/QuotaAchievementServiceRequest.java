package com.kfwy.hkp.controller.quota.vo;


import com.gniuu.framework.service.AbstractServiceRequest;
import com.kfwy.hkp.sys.quota.entity.QuotaAchievementEntity;

import java.util.Date;

/**
 * Create By hjh on 2019/02/28
 */
public class QuotaAchievementServiceRequest extends AbstractServiceRequest<QuotaAchievementEntity> {
    // 指标时间
    private Date qaTime;
    // 指标时间
    private String ownerCode;
    // 开始时间
    private Date startDate;
    // 结束时间
    private Date endDate;

    public Date getQaTime() {
        return qaTime;
    }

    public void setQaTime(Date qaTime) {
        this.qaTime = qaTime;
    }

    public String getOwnerCode() {
        return ownerCode;
    }

    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
