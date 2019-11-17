package com.kfwy.hkp.sys.area.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.sys.area.business.IBaseAreaManager;
import com.kfwy.hkp.sys.area.dao.IBaseAreaDbDao;
import com.kfwy.hkp.sys.area.entity.BaseAreaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by davidcun on 2018/5/22.
 * Update by luming on 2018/6/6
 */
@Service
public class BaseAreaManagerImpl extends AbstractManager<BaseAreaEntity> implements IBaseAreaManager {


    @Autowired
    private IBaseAreaDbDao baseAreaDbDao;

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.baseAreaDbDao;
    }

    @Override
    public BaseAreaEntity findAreaByCode(String areaCode) {
        return baseAreaDbDao.selectUniqueByProp("areaCode",areaCode);
    }
}
