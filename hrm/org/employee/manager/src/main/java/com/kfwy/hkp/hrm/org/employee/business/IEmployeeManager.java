package com.kfwy.hkp.hrm.org.employee.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.hkp.hrm.org.employee.entity.EmployeeEntity;

import java.util.List;

/**
 * Created by davidcun on 2018/5/16.
 */
public interface IEmployeeManager extends IManager<EmployeeEntity> {

    List<EmployeeEntity> selectEmpByRegion(String region);
}
