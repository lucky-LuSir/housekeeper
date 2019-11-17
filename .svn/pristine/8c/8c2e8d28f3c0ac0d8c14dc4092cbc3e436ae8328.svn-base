package com.kfwy.hkp.crm.customer.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.crm.customer.business.IFocusCusBlackListManager;
import com.kfwy.hkp.crm.customer.dao.IFocusCusBlackListDbDao;
import com.kfwy.hkp.crm.customer.entity.FocusCusBlackListEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IFocusCusBlackListManagerImpl extends AbstractManager<FocusCusBlackListEntity> implements IFocusCusBlackListManager {
    @Autowired
    private IFocusCusBlackListDbDao focusCusBlackListDbDao;

    @Override
    protected IMyBatisBaseDao<FocusCusBlackListEntity, Long> getMyBatisDao () {
        return focusCusBlackListDbDao;
    }

}
