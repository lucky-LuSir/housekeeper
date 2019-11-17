package com.kfwy.hkp.controller.hrm.post.vo;

import com.gniuu.framework.service.AbstractServiceRequest;
import com.kfwy.hkp.hrm.org.post.entity.EmpPostEntity;

/**
 * Created by davidcun on 2018/5/22.
 */
public class EmpPostServiceRequest extends AbstractServiceRequest<EmpPostEntity> {

    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
