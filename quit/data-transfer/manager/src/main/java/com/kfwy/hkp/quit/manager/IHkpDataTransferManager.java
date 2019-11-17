package com.kfwy.hkp.quit.manager;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.hkp.quit.entity.BackupEntity;

import java.util.Map;

public interface IHkpDataTransferManager extends IManager<BackupEntity> {

    /**
     * 房源数据转移
     * @param  param
     * @param backupEntity
     */
    String houseTransfer(BackupEntity backupEntity, Map<String,Object> param);
}
