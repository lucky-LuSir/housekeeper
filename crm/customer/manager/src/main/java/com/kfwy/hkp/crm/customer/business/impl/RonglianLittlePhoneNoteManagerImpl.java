package com.kfwy.hkp.crm.customer.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.crm.customer.business.IRonglianLittlePhoneNoteManager;
import com.kfwy.hkp.crm.customer.dao.IRonglianLittlePhoneNoteDbDao;
import com.kfwy.hkp.crm.customer.entity.RonlianPhoneLittleNoteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RonglianLittlePhoneNoteManagerImpl extends AbstractManager<RonlianPhoneLittleNoteEntity> implements IRonglianLittlePhoneNoteManager {

    @Autowired
    private IRonglianLittlePhoneNoteDbDao ronglianLittlePhoneNoteDbDao;

    @Override
    protected IMyBatisBaseDao<RonlianPhoneLittleNoteEntity, Long> getMyBatisDao () {
        return ronglianLittlePhoneNoteDbDao;
    }
}
