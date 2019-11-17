package com.kfwy.hkp.controller.count.report.vo;

import com.gniuu.framework.service.AbstractServiceRequest;
import com.kfwy.hkp.bi.count.entity.ReportEntity;

import java.util.Date;

/**
 * Create By hjh on 2019/03/11
 */
public class ReportApiServiceRequest extends AbstractServiceRequest<ReportEntity> {

    // 密钥
    private String key;
    // 编码
    private String code;
    // 开始时间
    private Date startDate;
    // 结束时间
    private Date endDate;
    // 是否可取缓存
    private boolean dynamic;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public boolean isDynamic() {
        return dynamic;
    }

    public boolean getDynamic() {
        return dynamic;
    }

    public void setDynamic(boolean dynamic) {
        this.dynamic = dynamic;
    }
}
