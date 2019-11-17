package com.kfwy.hkp.controller.hrm.cusservice.vo;

import com.gniuu.framework.service.AbstractServiceRequest;
import com.kfwy.hkp.hrm.org.dept.entity.CusServiceEntity;
import com.kfwy.hkp.hrm.org.dept.entity.DeptEntity;

/**
 * Created by davidcun on 2018/5/22.
 */
public class CusServiceServiceRequest extends AbstractServiceRequest<CusServiceEntity> {

    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
