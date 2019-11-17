package com.kfwy.hkp.quit.manager.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.quit.dao.IResourceBackupDao;
import com.kfwy.hkp.quit.entity.BackupEntity;
import com.kfwy.hkp.quit.manager.IHkpDataTransferManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class HkpDataTransferManagerImpl extends AbstractManager<BackupEntity> implements IHkpDataTransferManager {

    @Autowired
    private IResourceBackupDao resourceBackupDao;

    @Override
    protected IMyBatisBaseDao<BackupEntity, Long> getMyBatisDao() {
        return this.resourceBackupDao;
    }



    @Override
    public String houseTransfer(BackupEntity backupEntity, Map<String,Object> param) {

        StringBuffer result = new StringBuffer();

        param.put("lastUpdateCode", CurrentContext.getUserCode());
        param.put("lastUpdateTime",new Date());
        param.put("newEmpCode",backupEntity.getNewEmpCode());

        List<String> hosList;
        //房源历史拥有者不为空 按人转
        //为空的话就是按house_number转
        if(StringUtils.isNotBlank(backupEntity.getHisEmpCode())){
            param.put("hisEmpCode",backupEntity.getHisEmpCode());
            hosList = resourceBackupDao.getTransferFromHouse(param);
            if(hosList == null || hosList.size()<=0){
                throw new RemoveStackBusinessException("房源转移者没有房源要转！");
            }
            String shiftHouseData = StringUtils.join(hosList,",");
            param.put("shiftHouseCount",hosList.size());
            param.put("shiftHouseData",shiftHouseData);

            backupEntity.setShiftHouseData(shiftHouseData);
            backupEntity.setShiftHouseCount(hosList.size());
        }else if(StringUtils.isNotBlank(backupEntity.getShiftHouseData())){
            List<String> oldHosList = Arrays.asList(backupEntity.getShiftHouseData().split("\\,"));
            param.put("hosNumbers",oldHosList);
            param.put("ownerDeptCode",CurrentContext.getUserInfo().getOwnerDeptCode());
            hosList = resourceBackupDao.getTransferFromHouse(param);
            param.put("hosNumbers",hosList);

            if(hosList == null || hosList.size()<=0){
                throw new RemoveStackBusinessException("没有本部门房源要转！");
            }
            String shiftHouseData = StringUtils.join(hosList,",");
            param.put("shiftHouseCount",hosList.size());
            param.put("shiftHouseData",shiftHouseData);

            backupEntity.setShiftHouseData(shiftHouseData);
            backupEntity.setShiftHouseCount(hosList.size());

            //判断哪些房源是不转的 给前端提示
            for (String h : oldHosList){
                if(!hosList.contains(h)){
                    result.append(h).append(",");
                }
            }
        }else {
            throw new RemoveStackBusinessException ("没有房源要转！");
        }
        //转移房源
        resourceBackupDao.transferFromHouse(param);
        //备份房源
        backupEntity.setCreateCode(CurrentContext.getUserCode());
        backupEntity.setLastUpdateCode(CurrentContext.getUserCode());
        Date date = new Date();
        backupEntity.setCreateTime(date);
        backupEntity.setLastUpdateTime(date);
        backupEntity.setIsDeleted(Boolean.FALSE);
        backupEntity.setHasWithDraw(Boolean.FALSE);
        backupEntity.setCreateDeptCode(CurrentContext.getUserInfo().getOwnerDeptCode());
        resourceBackupDao.backup(backupEntity);

        return result.toString();
    }
}
