package com.kfwy.hkp.quit.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.quit.entity.BackupEntity;

import java.util.List;
import java.util.Map;

/**
 * 数据备份dao接口
 */
public interface IResourceBackupDao extends IMyBatisBaseDao<BackupEntity,Long> {

    /**
     * 数据备份 房源 客户 业主 兼职
     * @param backupEntity
     * @see com.kfwy.hkp.quit.entity.enums.TransferType
     * @return
     */
    int backup(BackupEntity backupEntity);

    /**
     * 获取需要转移的房源
     * @param param
     * @return
     */
    List<String> getTransferFromHouse(Map<String,Object> param);

    /**
     * 转移房源
     * @param param
     * @return
     */
    int transferFromHouse(Map<String,Object> param);
}
