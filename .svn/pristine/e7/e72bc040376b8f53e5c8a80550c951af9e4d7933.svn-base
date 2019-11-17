package com.kfwy.hkp.quit.manager.impl;

import com.gniuu.framework.common.context.CurrentContext;
import com.kfwy.hkp.quit.dao.IResourceBackupDao;
import com.kfwy.hkp.quit.entity.BackupEntity;
import com.kfwy.hkp.quit.entity.QuitEntity;
import com.kfwy.hkp.quit.entity.enums.TransferType;
import com.kfwy.hkp.quit.manager.ITeamDataTransferManager;
import com.kfwy.hkp.quit.manager.QuitDataTransferManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 团队 数据转移 业务层实现
 */
@Service
@Transactional
public class TeamDataTransferManagerImpl implements ITeamDataTransferManager {

    @Autowired
    private QuitDataTransferManager quitDataTransferManager;
    @Autowired
    private IResourceBackupDao resourceBackupDao;

    @Override
    public Integer transfer(QuitEntity quitEntity) {
        //需要备份的
        QuitEntity save = quitDataTransferManager.getQuitEntity(quitEntity);
        //转移
        Integer result = quitDataTransferManager.update(quitEntity);
        //备份
        if(save.getUserCodes()!=null && save.getUserCodes().size()>0){
            quitEntity.setUserCodes(save.getUserCodes());
            resourceBackupDao.backup(getBackUpFromQuit(quitEntity,TransferType.PartTime.getCode()));
        }
        if(save.getCusCodes()!=null && save.getCusCodes().size()>0){
            quitEntity.setCusCodes(save.getCusCodes());
            resourceBackupDao.backup(getBackUpFromQuit(quitEntity,TransferType.Customer.getCode()));
        }
        if((save.getHosCodes()!=null && save.getHosCodes().size()>0) ||
                (save.getOwnCodes()!=null && save.getOwnCodes().size()>0)){
            quitEntity.setHosCodes(save.getHosCodes());
            quitEntity.setOwnCodes(save.getOwnCodes());
            resourceBackupDao.backup(getBackUpFromQuit(quitEntity,TransferType.House.getCode()));
        }

        return result;
    }

    /**
     * 获取 备份实体
     * @param quitEntity
     * @param type {@link TransferType}
     * @return
     */
    public BackupEntity getBackUpFromQuit(QuitEntity quitEntity,Integer type){
        BackupEntity backupEntity = new BackupEntity();
        backupEntity.setHisEmpCode(quitEntity.getCreateCode());
        backupEntity.setHisEmpName(quitEntity.getCreateName());

        //房源 业主 转给同一人
        if(type == TransferType.House.getCode()){
            backupEntity.setNewEmpCode(quitEntity.getWareHouseDataTransferCode());
            backupEntity.setNewEmpName(quitEntity.getWareHouseDataTransfer());
            backupEntity.setShiftHouseCount(quitEntity.getHosCodes().size());
            backupEntity.setShiftHouseData(StringUtils.join(quitEntity.getHosCodes(),","));
            backupEntity.setShiftOwnerCount(quitEntity.getOwnCodes().size());
            backupEntity.setShiftOwnerData(StringUtils.join(quitEntity.getCusCodes(),","));
        }
        if(type == TransferType.Customer.getCode()){
            backupEntity.setNewEmpCode(quitEntity.getCusDataTransferCode());
            backupEntity.setNewEmpName(quitEntity.getCusDataTransfer());
            backupEntity.setShiftCustomerCount(quitEntity.getCusCodes().size());
            backupEntity.setShiftCustomerData(StringUtils.join(quitEntity.getCusCodes(),","));
        }
        if(type == TransferType.PartTime.getCode()){
            backupEntity.setNewEmpCode(quitEntity.getPartTimeDataTransferCode());
            backupEntity.setNewEmpName(quitEntity.getPartTimeDataTransfer());
            backupEntity.setShiftUserCount(quitEntity.getUserCodes().size());
            backupEntity.setShiftUserData(StringUtils.join(quitEntity.getUserCodes(),","));
        }
        backupEntity.setHasWithDraw(Boolean.FALSE);

        backupEntity.setCreateCode(CurrentContext.getUserCode());
        backupEntity.setLastUpdateCode(CurrentContext.getUserCode());
        Date date = new Date();
        backupEntity.setCreateTime(date);
        backupEntity.setLastUpdateTime(date);
        backupEntity.setIsDeleted(Boolean.FALSE);
        backupEntity.setCreateDeptCode(CurrentContext.getUserInfo().getOwnerDeptCode());
        return backupEntity;
    }
}
