package com.kfwy.hkp.hos.house.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.hos.house.business.IHouseFavoriteManager;
import com.kfwy.hkp.hos.house.dao.IHouseFavoriteDbDao;
import com.kfwy.hkp.hos.house.entity.HouseFavoriteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create By hjh on 2018/8/8
 */
@Service
public class HouseFavoriteManagerImpl extends AbstractManager<HouseFavoriteEntity> implements IHouseFavoriteManager{

    @Autowired
    private IHouseFavoriteDbDao houseFavoriteDbDao;

    @Override
    protected IMyBatisBaseDao<HouseFavoriteEntity, Long> getMyBatisDao() {
        return this.houseFavoriteDbDao;
    }

    @Override
    public synchronized int create(HouseFavoriteEntity houseFavoriteEntity) {
        Map<String, Object> param = new HashMap<>();
        param.put("empCode",CurrentContext.getUserCode());
        param.put("houseCode",houseFavoriteEntity.getHouseCode());
        HouseFavoriteEntity one = this.findOne(param);
        if(null != one){
            throw new RemoveStackBusinessException ("已经收藏过该房源");
        }
        OperateFillUtils.fill(houseFavoriteEntity);
        houseFavoriteEntity.setEmpCode(CurrentContext.getUserCode());
        return houseFavoriteDbDao.insert(houseFavoriteEntity);
    }

    @Override
    public void delete(Map<String, Object> param) {
        houseFavoriteDbDao.delete(param);
    }

    @Override
    public List<HouseFavoriteEntity> getCountGroupByUserCode() {
        return houseFavoriteDbDao.getCountGroupByUserCode();
    }

    @Override
    public HouseFavoriteEntity findOne(Map<String, Object> param) {
        return houseFavoriteDbDao.selectUniqueByMap(param);
    }

}
