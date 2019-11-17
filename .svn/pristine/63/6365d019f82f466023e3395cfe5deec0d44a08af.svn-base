package com.kfwy.hkp.hos.house.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.hos.house.business.IHouseFollowupManager;
import com.kfwy.hkp.hos.house.dao.IHouseDbDao;
import com.kfwy.hkp.hos.house.dao.IHouseFollowupDbDao;
import com.kfwy.hkp.hos.house.dao.IHouseLocationDbDao;
import com.kfwy.hkp.hos.house.entity.HouseEntity;
import com.kfwy.hkp.hos.house.entity.HouseFollowupEntity;
import com.kfwy.hkp.hos.house.entity.HouseLocationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Create By hjh on 2018/10/11
 */
@Service
public class HouseFollowupManagerImpl extends AbstractManager<HouseFollowupEntity> implements IHouseFollowupManager {

    @Autowired
    private IHouseFollowupDbDao houseFollowupDbDao;
    @Autowired
    private IHouseDbDao houseDbDao;
    @Autowired
    private IHouseLocationDbDao houseLocationDbDao;


    @Override
    protected IMyBatisBaseDao<HouseFollowupEntity, Long> getMyBatisDao() {
        return this.houseFollowupDbDao;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int create(HouseFollowupEntity houseFollowupEntity) {

        OperateFillUtils.fill(houseFollowupEntity);
        houseFollowupEntity.setEmpCode(CurrentContext.getUserCode());
        houseFollowupEntity.setDeptCode(CurrentContext.getUserInfo().getOwnerDeptCode());
        HouseEntity houseEntity = houseDbDao.selectUniqueByProp("houseCode", houseFollowupEntity.getHouseCode());
        houseFollowupEntity.setHouseName(houseEntity.getHouseName());
        houseFollowupEntity.setHousePurpose(houseEntity.getHousePurpose());
        HouseLocationEntity locationEntity = houseLocationDbDao.selectUniqueByProp("locationCode", houseEntity.getLocationCode());
        houseFollowupEntity.setHouseAddress(locationEntity.getDetailAddress());

        int result =this.houseFollowupDbDao.insert(houseFollowupEntity);

        //跟新房源最后跟进时间
        HouseEntity house = new HouseEntity();
        house.setHouseCode(houseFollowupEntity.getHouseCode());
        house.setLastFollowupTime(houseFollowupEntity.getCreateTime());
        //house.setLeaseExpiration(houseFollowupEntity.getLeaseExpiration());
        houseDbDao.update(house);


        return result;
    }

    /**
     * 修改房源信息时造成的修改房源最后跟进时间将不在此处修改最后跟进时间
     * @param houseFollowupEntity
     * @return
     */
    @Override
    public int createHouseFollowupNotLastFollowupTime(HouseFollowupEntity houseFollowupEntity) {
        OperateFillUtils.fill(houseFollowupEntity);
        houseFollowupEntity.setEmpCode(CurrentContext.getUserCode());
        houseFollowupEntity.setDeptCode(CurrentContext.getUserInfo().getOwnerDeptCode());
        HouseEntity houseEntity = houseDbDao.selectUniqueByProp("houseCode", houseFollowupEntity.getHouseCode());
        houseFollowupEntity.setHouseName(houseEntity.getHouseName());
        houseFollowupEntity.setHousePurpose(houseEntity.getHousePurpose());
        HouseLocationEntity locationEntity = houseLocationDbDao.selectUniqueByProp("locationCode", houseEntity.getLocationCode());
        houseFollowupEntity.setHouseAddress(locationEntity.getDetailAddress());

        int result =this.houseFollowupDbDao.insert(houseFollowupEntity);
        return result;
    }
}