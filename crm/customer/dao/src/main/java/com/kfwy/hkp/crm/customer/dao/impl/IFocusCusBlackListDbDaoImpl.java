package com.kfwy.hkp.crm.customer.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.crm.customer.dao.IFocusCusBlackListDbDao;
import com.kfwy.hkp.crm.customer.entity.FocusCusBlackListEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IFocusCusBlackListDbDaoImpl extends AbstractMyBatisDao<FocusCusBlackListEntity,Long> implements IFocusCusBlackListDbDao {

    @Override
    public List<String> excludeUserCodesByBlackList () {
        return this.getSqlSession ().selectList ("excludeUserCodesByBlackList");
    }
}
