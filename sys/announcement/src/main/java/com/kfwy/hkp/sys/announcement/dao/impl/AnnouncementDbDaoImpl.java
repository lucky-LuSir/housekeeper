package com.kfwy.hkp.sys.announcement.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.sys.announcement.dao.IAnnouncementDbDao;
import com.kfwy.hkp.sys.announcement.entity.AnnouncementEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by davidcun on 2018/5/25.
 */
@Repository
public class AnnouncementDbDaoImpl extends AbstractMyBatisDao<AnnouncementEntity,Long> implements IAnnouncementDbDao {
    @Override
    public void deletedData(Map<String, Object> param) {
         this.getSqlSession().update(this.mapperNamespace+"deleteData",param);
    }
}
