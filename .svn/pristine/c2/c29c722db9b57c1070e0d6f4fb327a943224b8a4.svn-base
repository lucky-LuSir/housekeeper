package com.kfwy.hkp.bi.count.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.user.entity.BaseUserEntity;
import com.kfwy.hkp.bi.count.business.IWorkbenchManager;
import com.kfwy.hkp.bi.count.dao.IWorkbenchDbDao;
import com.kfwy.hkp.bi.count.entity.WorkbenchEntity;
import com.kfwy.hkp.common.enums.DeptType;
import com.kfwy.hkp.common.utils.DateFormatUtil;
import com.kfwy.hkp.hrm.org.dept.dao.IDeptDbDao;
import com.kfwy.hkp.hrm.org.dept.entity.DeptEntity;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create By hjh on 2018/9/7
 */
@Service
public class WorkbenchManagerImpl extends AbstractManager<WorkbenchEntity> implements IWorkbenchManager {

    @Autowired
    private IWorkbenchDbDao workbenchDbDao;

    @Autowired
    private IDeptDbDao deptDbDao;

    @Override
    protected IMyBatisBaseDao<WorkbenchEntity, Long> getMyBatisDao() {
        return this.workbenchDbDao;
    }

    @Override
    public List<WorkbenchEntity> richList() {

        Map<String, Object> param = new HashMap<> ();
        param.put("createTimeStart", DateFormatUtil.monthBeginDay(DateFormatUtil.getCurrentDateTime()));
        param.put("createTimeEnd", DateFormatUtil.dayEnd(DateFormatUtil.getCurrentDateTime()));
        param.put("isDeleted", Boolean.FALSE);
        if (null!=CurrentContext.getCpyCode () && ""!=CurrentContext.getCpyCode ()){
            param.put("cpyCode",CurrentContext.getCpyCode());
        }
        List<WorkbenchEntity> list = workbenchDbDao.richList(param);
        if (list==null){
            list = new ArrayList<> ();
        }
        return list;

    }

    @Override
    public WorkbenchEntity queryWorkCount(Map<String, Object> param) {
        param.put("empCode", CurrentContext.getUserCode());
        param.put("createTimeStart", DateFormatUtil.monthBeginDay(DateFormatUtil.getCurrentDateTime()));
        param.put("createTimeEnd", DateFormatUtil.dayEnd(DateFormatUtil.getCurrentDateTime()));
        return workbenchDbDao.queryWorkCount(param);
    }
}
