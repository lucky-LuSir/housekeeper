package com.kfwy.hkp.hrm.org.employee.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.hrm.org.employee.business.IEmployeeManager;
import com.kfwy.hkp.hrm.org.employee.dao.IEmployeeDbDao;
import com.kfwy.hkp.hrm.org.employee.entity.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by davidcun on 2018/5/16.
 */
@Component
public class EmployeeManagerImpl extends AbstractManager<EmployeeEntity> implements IEmployeeManager {

    @Autowired
    private IEmployeeDbDao employeeDbDao;

    @Override
    protected IMyBatisBaseDao<EmployeeEntity, Long> getMyBatisDao() {
        return this.employeeDbDao;
    }

    @Override
    public List<EmployeeEntity> selectEmpByRegion(String region) {
        return employeeDbDao.selectEmpByRegion(region);
    }

}
