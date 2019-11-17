package com.kfwy.hkp.sys.quota.dao;


import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.sys.quota.entity.MyAchievementEntity;

import java.util.Map;

/**
 * Create By hjh on 2019/2/28
 */
public interface IMyAchievementDbDao extends IMyBatisBaseDao<MyAchievementEntity, Long> {

    public MyAchievementEntity selectAchievementByMap(Map<String, Object> map);

}
