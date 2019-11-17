package com.kfwy.hkp.crm.customer.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.crm.customer.business.IRonglianPhoneNoteManager;
import com.kfwy.hkp.crm.customer.dao.IRonglianPhoneNoteDbDao;
import com.kfwy.hkp.crm.customer.entity.RonglianPhoneNoteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RonglianPhoneNoteManagerImpl extends AbstractManager<RonglianPhoneNoteEntity> implements IRonglianPhoneNoteManager {

    @Autowired
    private IRonglianPhoneNoteDbDao ronglianPhoneNoteDbDao;

    @Override
    protected IMyBatisBaseDao<RonglianPhoneNoteEntity, Long> getMyBatisDao () {
        return ronglianPhoneNoteDbDao;
    }
}
