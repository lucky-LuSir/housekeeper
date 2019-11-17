package com.kfwy.hkp.hrm.org.dept.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.hrm.org.dept.entity.DeptAreaEntity;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/12/11 11:46
 */
public interface IDeptAreaDbDao extends IMyBatisBaseDao<DeptAreaEntity,Long> {

    public int deleteByDeptCode(String deptCode);

}
