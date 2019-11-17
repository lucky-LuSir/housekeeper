package com.kfwy.hkp.bi.count.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.bi.count.entity.WorkbenchEntity;

import java.util.List;
import java.util.Map;

/**
 * Create By hjh on 2018/9/7
 */
public interface IWorkbenchDbDao extends IMyBatisBaseDao<WorkbenchEntity,Long> {

    public WorkbenchEntity queryWorkCount(Map<String, Object> param);

    public List<WorkbenchEntity> richList(Map<String, Object> param);

    public List<WorkbenchEntity> richListCpy(Map<String, Object> param);
}
