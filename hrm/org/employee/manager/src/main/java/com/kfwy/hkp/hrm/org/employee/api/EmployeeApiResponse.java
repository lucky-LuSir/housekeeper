package com.kfwy.hkp.hrm.org.employee.api;

import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.AbstractServiceResponse;
import com.kfwy.hkp.hrm.org.employee.entity.EmployeeEntity;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/10/31 19:08
 */
public class EmployeeApiResponse extends AbstractServiceResponse {

    private PageResult<EmployeeEntity> result;

    @Override
    public PageResult<EmployeeEntity> getResult() {
        return result;
    }

    public void setResult(PageResult<EmployeeEntity> result) {
        this.result = result;
    }
}
