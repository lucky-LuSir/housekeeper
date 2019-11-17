package com.kfwy.hkp.quit.manager.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.quit.dao.IToolsDataTransferDao;
import com.kfwy.hkp.quit.entity.SpecialEntity;
import com.kfwy.hkp.quit.entity.enums.TransferType;
import com.kfwy.hkp.quit.manager.IToolsDataTransferManger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 工具想转移 manager实现
 */
@Service
@Transactional
public class ToolsDataTransferMangerImpl extends AbstractManager<SpecialEntity> implements IToolsDataTransferManger {

    @Autowired
    private IToolsDataTransferDao toolsDataTransferDao;

    @Override
    protected IMyBatisBaseDao<SpecialEntity, Long> getMyBatisDao() {
        return this.toolsDataTransferDao;
    }


    @Override
    public SpecialEntity transfer(SpecialEntity specialEntity,Integer type) {
        SpecialEntity se = new SpecialEntity();
        if(TransferType.House.getCode() == type){
            //如果房源code不为空，则将负责专员设置为空
            if(specialEntity.getHosCodes() != null && specialEntity.getHosCodes().size() > 0){
                specialEntity.setHisEmpCode(null);
            }
            List<String> list = this.toolsDataTransferDao.getTransferFromHouse(specialEntity);
            this.toolsDataTransferDao.transferFromHouse(specialEntity);
            list = Optional.ofNullable(list).orElseGet(() -> new ArrayList<>());
            se.setHosCodes(list);
        }
        if(TransferType.Customer.getCode() == type){
            //如果客户code不为空，则将负责专员设置为空
            if(specialEntity.getCusCodes() != null && specialEntity.getCusCodes().size() > 0){
                specialEntity.setHisEmpCode(null);
            }
            List<String> list = this.toolsDataTransferDao.getTransferFromCustomer(specialEntity);
            list = Optional.ofNullable(list).orElseGet(() -> new ArrayList<>());
            this.toolsDataTransferDao.transferFromCustomer(specialEntity);
            se.setCusCodes(list);
        }
        if(TransferType.Owner.getCode() == type){
            //如果业主code不为空，则将负责专员设置为空
            if(specialEntity.getOwnCodes() != null && specialEntity.getOwnCodes().size() > 0){
                specialEntity.setHisEmpCode(null);
            }
            List<String> list = this.toolsDataTransferDao.getTransferFromOwner(specialEntity);
            this.toolsDataTransferDao.transferFromOwner(specialEntity);
            list = Optional.ofNullable(list).orElseGet(() -> new ArrayList<>());
            se.setOwnCodes(list);
        }
        if(TransferType.PartTime.getCode() == type){
            //如果兼职code不为空，则将负责专员设置为空
            if(specialEntity.getUserCodes() != null && specialEntity.getUserCodes().size() > 0){
                specialEntity.setHisEmpCode(null);
            }
            List<String> list = this.toolsDataTransferDao.getTransferFromPartTime(specialEntity);
            this.toolsDataTransferDao.transferFromPartTime(specialEntity);
            list = Optional.ofNullable(list).orElseGet(() -> new ArrayList<>());
            se.setUserCodes(list);
        }
        return se;
    }
}








