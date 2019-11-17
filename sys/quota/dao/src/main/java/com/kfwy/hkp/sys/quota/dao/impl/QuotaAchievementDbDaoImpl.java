package com.kfwy.hkp.sys.quota.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.sys.quota.dao.IQuotaAchievementDbDao;
import com.kfwy.hkp.sys.quota.entity.QuotaAchievementEntity;
import org.springframework.stereotype.Repository;

/**
 * Create By hjh on 2019/2/28
 */
@Repository
public class QuotaAchievementDbDaoImpl extends AbstractMyBatisDao<QuotaAchievementEntity,Long> implements IQuotaAchievementDbDao {
}
