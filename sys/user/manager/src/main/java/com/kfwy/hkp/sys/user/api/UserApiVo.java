package com.kfwy.hkp.sys.user.api;

import com.kfwy.hkp.sys.user.entity.UserEntity;

import java.util.Date;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/11/10 15:09
 */
public class UserApiVo extends UserEntity {

    /**
     * 可以查询
     */
    private String keyword;
    /**
     * 分页开始
     */
    private Integer start;
    /**
     * 分页大小
     */
    private Integer pageSize;
    /**
     * 入职时间
     */
    private Date entryTimeStart;
    private Date entryTimeEnd;

    /**
     * 离职时间
     */
    private Date quitTimeStart;

    private Date quitTimeEnd;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Date getEntryTimeStart() {
        return entryTimeStart;
    }

    public void setEntryTimeStart(Date entryTimeStart) {
        this.entryTimeStart = entryTimeStart;
    }

    public Date getEntryTimeEnd() {
        return entryTimeEnd;
    }

    public void setEntryTimeEnd(Date entryTimeEnd) {
        this.entryTimeEnd = entryTimeEnd;
    }

    public Date getQuitTimeStart() {
        return quitTimeStart;
    }

    public void setQuitTimeStart(Date quitTimeStart) {
        this.quitTimeStart = quitTimeStart;
    }

    public Date getQuitTimeEnd() {
        return quitTimeEnd;
    }

    public void setQuitTimeEnd(Date quitTimeEnd) {
        this.quitTimeEnd = quitTimeEnd;
    }
}
