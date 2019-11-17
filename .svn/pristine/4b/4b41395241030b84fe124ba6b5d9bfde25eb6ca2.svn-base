package com.kfwy.hkp.sys.parttime.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.sys.parttime.business.IParttimeManager;
import com.kfwy.hkp.sys.parttime.dao.IParttimeDbDao;
import com.kfwy.hkp.sys.parttime.entity.ParttimeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create By hjh on 2018/11/7
 */
@Service
public class ParttimeManagerImpl extends AbstractManager<ParttimeEntity> implements IParttimeManager {

    @Autowired
    private IParttimeDbDao parttimeDbDao;

    @Override
    protected IMyBatisBaseDao<ParttimeEntity, Long> getMyBatisDao() {
        return this.parttimeDbDao;
    }

    @Override
    protected void beforeCreate(ParttimeEntity parttimeEntity) {
        parttimeEntity.setPtCode(CodeGenUtils.generate());
        parttimeEntity.setOwnerPhone(CurrentContext.getUserInfo().getUserPhone());
    }
}
