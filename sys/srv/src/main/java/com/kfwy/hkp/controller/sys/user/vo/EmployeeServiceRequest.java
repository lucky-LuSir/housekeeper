package com.kfwy.hkp.controller.sys.user.vo;

import com.gniuu.framework.service.AbstractServiceRequest;

/**
 * Created by davidcun on 2018/5/22.
 */
public class EmployeeServiceRequest extends AbstractServiceRequest<UserVo> {
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
