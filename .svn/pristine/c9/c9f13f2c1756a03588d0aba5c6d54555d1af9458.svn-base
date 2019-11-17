/*
 * Copyright (c) 2014. kupat Corporation. All rights reserved.
 *  see statement on http://www.kupat.cn.
 */
package com.kfwy.hkp.common.mongo;

import com.gniuu.framework.service.AbstractDto;
import com.gniuu.framework.service.IServiceRequest;

public abstract class AbstractMongoServiceRequest<T> extends AbstractDto implements IServiceRequest {

    /**
     * 默认从0开始查询
     */
    private Integer start = 0;

    /**
     * 默认分页大小为50
     */
    private Integer pageSize = 50;

    /**
     * 针对新增的时候就可以用这个属性
     */
    private T entity;


    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
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
}
