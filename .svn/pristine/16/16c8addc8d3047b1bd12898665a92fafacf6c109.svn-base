package com.kfwy.hkp.sys.quota.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.sys.quota.business.IQuotaAchievementManager;
import com.kfwy.hkp.sys.quota.dao.IQuotaAchievementDbDao;
import com.kfwy.hkp.sys.quota.entity.QuotaAchievementEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Create By hjh on 2019/2/28
 */
@Service
public class QuotaAchievementManagerImpl extends AbstractManager<QuotaAchievementEntity> implements IQuotaAchievementManager {

    @Autowired
    private IQuotaAchievementDbDao quotaAchievementDbDao;

    @Override
    protected IMyBatisBaseDao<QuotaAchievementEntity, Long> getMyBatisDao() {
        return this.quotaAchievementDbDao;
    }

    @Override
    @Transactional
    public int create(QuotaAchievementEntity q) {
        Map<String, Object> map = new HashMap<>();
        map.put("ownerCode", q.getOwnerCode());
        map.put("qaTime", q.getQaTime());
        map.put("isDeleted", Boolean.FALSE);
        QuotaAchievementEntity qs = quotaAchievementDbDao.selectUniqueByMap(map);
        if (qs != null) {
            throw new RemoveStackBusinessException ("已存在相同部门的指标！");
        }
        return quotaAchievementDbDao.insert(q);
    }

    @Override
    protected void beforeUpdate(QuotaAchievementEntity q) {
    }
}
