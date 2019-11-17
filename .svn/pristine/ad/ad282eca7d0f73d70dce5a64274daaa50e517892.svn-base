package com.kfwy.hkp.bi.count.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.bi.count.dao.IWorkbenchDbDao;
import com.kfwy.hkp.bi.count.entity.WorkbenchEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Create By hjh on 2018/9/7
 */
@Repository
public class WorkbenchDbDaoImpl extends AbstractMyBatisDao<WorkbenchEntity,Long> implements IWorkbenchDbDao {
    @Override
    public WorkbenchEntity queryWorkCount(Map<String, Object> param) {
        return this.getSqlSession().selectOne(this.mapperNamespace + "queryWorkCount", param);
    }

    @Override
    public List<WorkbenchEntity> richList(Map<String, Object> param) {
        return this.getSqlSession().selectList(this.mapperNamespace + "richList",param);
    }

    @Override
    public List<WorkbenchEntity> richListCpy(Map<String, Object> param) {
        return this.getSqlSession().selectList(this.mapperNamespace + "richListCpy",param);
    }
}
