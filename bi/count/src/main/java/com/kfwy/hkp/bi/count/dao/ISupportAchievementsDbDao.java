package com.kfwy.hkp.bi.count.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.bi.count.entity.SupportAchievementsEntity;

import java.util.Map;

/**
 * Create By lzp on 2019/07/23
 */
public interface ISupportAchievementsDbDao extends IMyBatisBaseDao<SupportAchievementsEntity,Long> {

    public SupportAchievementsEntity selectSupportAchievements(Map<String, Object> param);

}
