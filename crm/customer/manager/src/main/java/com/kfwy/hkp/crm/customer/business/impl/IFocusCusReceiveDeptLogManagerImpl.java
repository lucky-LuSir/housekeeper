package com.kfwy.hkp.crm.customer.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.crm.customer.business.IFocusCusReceiveDeptLogManager;
import com.kfwy.hkp.crm.customer.dao.FocusCusReceiveDeptLogDbDao;
import com.kfwy.hkp.crm.customer.entity.FocusCusReceiveDeptLogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IFocusCusReceiveDeptLogManagerImpl extends AbstractManager<FocusCusReceiveDeptLogEntity> implements IFocusCusReceiveDeptLogManager {
    @Autowired
    private FocusCusReceiveDeptLogDbDao focusCusReceiveDeptLogDbDao;

    @Override
    protected IMyBatisBaseDao<FocusCusReceiveDeptLogEntity, Long> getMyBatisDao() {
        return this.focusCusReceiveDeptLogDbDao;
    }
}
