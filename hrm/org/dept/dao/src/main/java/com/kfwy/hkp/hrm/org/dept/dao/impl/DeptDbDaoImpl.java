package com.kfwy.hkp.hrm.org.dept.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.hrm.org.dept.dao.IDeptDbDao;
import com.kfwy.hkp.hrm.org.dept.entity.DeptEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by davidcun on 2018/5/22.
 */
@Repository
public class DeptDbDaoImpl extends AbstractMyBatisDao<DeptEntity,Long> implements IDeptDbDao {
    @Override
    public void deleteByCode(String code) {

        Map<String, Object> param = new HashMap<>();
        param.put("deptCode",code);
         this.getSqlSession().delete("DeptEntity.delete",param);

    }

    @Override
    public List<DeptEntity> getList() {
        List<DeptEntity> deptList = new ArrayList<DeptEntity>();
         deptList = this.getSqlSession().selectList("DeptEntity.getList");
        return deptList;
    }

    @Override
    public List<String> selectDeptCodeByMap(Map<String, Object> map) {
        return this.getSqlSession().selectList(this.mapperNamespace+"selectDeptCodeByMap",map);
    }

    @Override
    public List<DeptEntity> findNearby(Map<String, Object> map) {

        return this.getSqlSession().selectList(this.mapperNamespace+"selectNearby",map);
    }

    @Override
    public List<DeptEntity> selectSimpleList(Map<String, Object> map) {
        return this.getSqlSession().selectList(this.mapperNamespace+"selectSimpleList",map);
    }

    @Override
    public DeptEntity findOneDept(Map map) {
        return this.getSqlSession().selectOne(this.mapperNamespace+"findOneDept",map);
    }

    @Override
    public DeptEntity findDeptByUserCode (String userCode) {
        return this.getSqlSession ().selectOne (this.mapperNamespace+"findDeptByUserCode",userCode);
    }
}//end class
