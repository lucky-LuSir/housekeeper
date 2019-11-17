package com.kfwy.hkp.hrm.org.dept.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.hrm.org.dept.dao.IDeptAreaDbDao;
import com.kfwy.hkp.hrm.org.dept.entity.DeptAreaEntity;
import org.springframework.stereotype.Repository;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/12/11 11:47
 */
@Repository
public class IDeptAreaDbDaoImpl extends AbstractMyBatisDao<DeptAreaEntity,Long> implements IDeptAreaDbDao {


    public int deleteByDeptCode(String deptCode){

        return this.getSqlSession().delete(this.mapperNamespace+"deleteByDeptCode",deptCode);

    }
}
