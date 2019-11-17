package com.kfwy.hkp.base.textlegal.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.user.entity.BaseUserEntity;
import com.kfwy.hkp.base.history.business.IHistoryManager;
import com.kfwy.hkp.base.history.dao.IHistoryDbDao;
import com.kfwy.hkp.base.history.entity.HistoryEntity;
import com.kfwy.hkp.base.textlegal.business.ITextLegalManager;
import com.kfwy.hkp.base.textlegal.dao.ITextLegalDbDao;
import com.kfwy.hkp.base.textlegal.entity.TextLegalEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description manager
 * @Auth xpp
 * @Date 2018/12/8
 * @Version 1.0
 */
@Service
public class TextLegalManagerImpl extends AbstractManager<TextLegalEntity> implements ITextLegalManager {


    @Autowired
    private ITextLegalDbDao textLegalDbDao;


    @Override
    protected IMyBatisBaseDao<TextLegalEntity, Long> getMyBatisDao() {
        return this.textLegalDbDao;
    }




}
