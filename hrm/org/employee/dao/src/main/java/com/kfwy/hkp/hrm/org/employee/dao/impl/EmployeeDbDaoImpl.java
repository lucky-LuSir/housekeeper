package com.kfwy.hkp.hrm.org.employee.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.hrm.org.employee.dao.IEmployeeDbDao;
import com.kfwy.hkp.hrm.org.employee.entity.EmployeeEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by davidcun on 2018/5/16.
 */
@Repository
public class EmployeeDbDaoImpl extends AbstractMyBatisDao<EmployeeEntity,Long> implements IEmployeeDbDao {

    @Override
    public List<EmployeeEntity> selectEmpByRegion(String regionCode){

        return this.getSqlSession().selectList(this.mapperNamespace+"queryRegionEmployee",regionCode);
    }
}
