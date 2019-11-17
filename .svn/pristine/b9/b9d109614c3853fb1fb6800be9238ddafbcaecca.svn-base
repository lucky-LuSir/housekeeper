package com.kfwy.hkp.hrm.org.dept.api;

import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.AbstractServiceResponse;
import com.kfwy.hkp.hrm.org.dept.dto.DeptTreeDto;
import com.kfwy.hkp.hrm.org.dept.entity.DeptEntity;

import java.util.List;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/10/31 17:46
 */
public class DeptApiResponse extends AbstractServiceResponse {

    private PageResult<DeptEntity> result;

    private List<DeptTreeDto> resultDto;

    private DeptEntity entity;


    private List<DeptEntity> deptList;

    private PageResult<DeptEntity> pageDept;

    public List<DeptEntity> getDeptList() {
        return deptList;
    }

    public void setDeptList(List<DeptEntity> deptList) {
        this.deptList = deptList;
    }

    public PageResult<DeptEntity> getPageDept() {
        return pageDept;
    }

    public void setPageDept(PageResult<DeptEntity> pageDept) {
        this.pageDept = pageDept;
    }

    public DeptEntity getEntity() {
        return entity;
    }

    public void setEntity(DeptEntity entity) {
        this.entity = entity;
    }

    @Override
    public PageResult<DeptEntity> getResult() {
        return result;
    }

    public List<DeptTreeDto> getResultDto() {
        return resultDto;
    }

    public void setResultDto(List<DeptTreeDto> resultDto) {
        this.resultDto = resultDto;
    }

    public void setResult(PageResult<DeptEntity> result) {
        this.result = result;
    }
}
