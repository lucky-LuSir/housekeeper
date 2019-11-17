package com.kfwy.hkp.bi.count.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.bi.count.dao.ISupportAchievementsDbDao;
import com.kfwy.hkp.bi.count.entity.SupportAchievementsEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;


/**
 * Create By hjh on 2019/07/23
 */
@Repository
public class SupportAchievementsDbDaoImpl extends AbstractMyBatisDao<SupportAchievementsEntity,Long> implements ISupportAchievementsDbDao {

    @Override
    public SupportAchievementsEntity selectSupportAchievements(Map<String, Object> param) {
        return this.getSqlSession().selectOne(this.mapperNamespace + "selectSupportAchievements", param);
    }
}
