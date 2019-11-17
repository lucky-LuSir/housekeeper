package com.kfwy.hkp.crm.houseowner.business.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.common.utils.ListUtils;
import com.kfwy.hkp.crm.houseowner.business.IHouseownerContactsManager;
import com.kfwy.hkp.crm.houseowner.dao.IHouseownerContactsDbDao;
import com.kfwy.hkp.crm.houseowner.entity.HouseownerContactsEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by zjp on 2018/8/1.
 */
@Service
public class HouseownerContactsManagerImpl extends AbstractManager<HouseownerContactsEntity> implements IHouseownerContactsManager {

    @Autowired
    private IHouseownerContactsDbDao houseownerContactsDbDao;

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.houseownerContactsDbDao;
    }

    @Override
    public int create(HouseownerContactsEntity houseownerContactsEntity) {

        // 业主编码生成
        houseownerContactsEntity.setContactCode(CodeGenUtils.generate());
        OperateFillUtils.fill(houseownerContactsEntity);
        return houseownerContactsDbDao.insertEntity(houseownerContactsEntity);
    }

    @Override
    public List<HouseownerContactsEntity> findByMap(Map<String, Object> param) {
        param.put("isDeleted", false);
        return houseownerContactsDbDao.selectByMap(param);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(List<HouseownerContactsEntity> houseownerContactsEntityList, String houseCode) {

        if(CollectionUtil.isNotEmpty(houseownerContactsEntityList)){
            for(HouseownerContactsEntity houseownerContactsEntity : houseownerContactsEntityList){
                    if (StringUtils.isEmpty(houseownerContactsEntity.getHouseCode())) {
                        houseownerContactsEntity.setHouseCode(houseCode);
                    }
                    if (!houseOwnerContractPhoneCheck(houseownerContactsEntity)){
                        continue;
                    }
                    this.create(houseownerContactsEntity);
            }
        }
    }

    public boolean houseOwnerContractPhoneCheck(HouseownerContactsEntity houseownerContactsEntity) {
        String preSql = "SELECT " +
                " COUNT (*) " +
                " FROM\n" +
                " t_hkp_hos_houseowner_contacts " +
                " where house_code=\'%s\'" +
                " and contact_phone=\'%s\'";

        String sql = String.format(preSql, houseownerContactsEntity.getHouseCode(), houseownerContactsEntity.getContactPhone());
        int count = houseownerContactsDbDao.countByNativeSql(sql);
        return count>0 ? false : true;
    }

    @Override
    public int batchInsert(List<HouseownerContactsEntity> houseownerContactsEntityList) {
        if (ListUtils.isEmpty(houseownerContactsEntityList)) {
            return 0;
        }
        int count = 0;
        for (HouseownerContactsEntity houseownerContactsEntity : houseownerContactsEntityList) {
            count += this.create(houseownerContactsEntity);
        }
        return count;
    }
}
