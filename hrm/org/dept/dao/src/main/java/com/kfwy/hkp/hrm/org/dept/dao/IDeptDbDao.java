package com.kfwy.hkp.hrm.org.dept.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.hrm.org.dept.entity.DeptEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by davidcun on 2018/5/22.
 */
public interface IDeptDbDao extends IMyBatisBaseDao<DeptEntity,Long> {
    public void deleteByCode(String code);

    public List<DeptEntity> getList();

    List<DeptEntity> findNearby(Map<String, Object> map);

    List<DeptEntity> selectSimpleList(Map<String, Object> map);

    List<String> selectDeptCodeByMap(Map<String, Object> map);

    public DeptEntity findOneDept(Map map);

    public DeptEntity findDeptByUserCode(String userCode);
}
