package com.kfwy.hkp.sys.quota.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.sys.quota.dao.IMyAchievementDbDao;
import com.kfwy.hkp.sys.quota.entity.MyAchievementEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Create By hjh on 2019/2/28
 */
@Repository
public class MyAchievementDbDaoImpl extends AbstractMyBatisDao<MyAchievementEntity, Long> implements IMyAchievementDbDao {

    @Override
    public MyAchievementEntity selectAchievementByMap(Map<String, Object> map) {
        return this.getSqlSession().selectOne(this.mapperNamespace + "selectAchievementByMap", map);
    }

}
