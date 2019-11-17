package com.kfwy.hkp.bi.count.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.hkp.bi.count.entity.WorkbenchEntity;

import java.util.List;
import java.util.Map;

/**
 * Create By hjh on 2018/9/7
 */
public interface IWorkbenchManager extends IManager<WorkbenchEntity> {

    public WorkbenchEntity queryWorkCount(Map<String, Object> param);

    public List<WorkbenchEntity> richList();

}
