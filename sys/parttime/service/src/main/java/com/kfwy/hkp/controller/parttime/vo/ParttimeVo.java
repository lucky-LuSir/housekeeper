package com.kfwy.hkp.controller.parttime.vo;

import com.kfwy.hkp.sys.parttime.entity.ParttimeEntity;

import java.util.Date;

/**
 * Create By hjh on 2018/11/7
 */
public class ParttimeVo extends ParttimeEntity {

    private Date createTimeStart;

    private Date createTimeEnd;

    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
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
