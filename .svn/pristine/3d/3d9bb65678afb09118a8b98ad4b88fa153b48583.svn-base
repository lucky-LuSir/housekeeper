package com.kfwy.hkp.hrm.org.employee.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.hrm.org.employee.entity.EmployeeEntity;

import java.util.List;

/**
 * Created by davidcun on 2018/5/16.
 */
public interface IEmployeeDbDao extends IMyBatisBaseDao<EmployeeEntity,Long> {

    /**
     * 根据区域查询人员信息
     * @param regionCode
     * @return
     */
    public List<EmployeeEntity> selectEmpByRegion(String regionCode);
}
