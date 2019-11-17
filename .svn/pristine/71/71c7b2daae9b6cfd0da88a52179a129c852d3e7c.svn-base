package com.kfwy.hkp.quit.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.quit.dao.IResourceBackupDao;
import com.kfwy.hkp.quit.entity.BackupEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * 数据备份dao实现层
 */
@Repository
public class ResourceBackupDaoImpl extends AbstractMyBatisDao<BackupEntity,Long> implements IResourceBackupDao {

    @Override
    public int backup(BackupEntity backupEntity) {
        return this.getSqlSession().insert(this.mapperNamespace+"resourceBackup",backupEntity);
    }

    @Override
    public List<String> getTransferFromHouse(Map<String, Object> param) {
        return this.getSqlSession().selectList(this.mapperNamespace+"getTransferFromHouse",param);
    }

    @Override
    public int transferFromHouse(Map<String, Object> param) {
        return this.getSqlSession().update(this.mapperNamespace+"transferFromHouse",param);
    }
}
