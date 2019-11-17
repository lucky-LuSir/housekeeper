package com.kfwy.hkp.hos.house.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.logging.Logger;
import com.gniuu.framework.logging.LoggerFactory;
import com.kfwy.hkp.hos.house.business.IHouseMapManager;
import com.kfwy.hkp.hos.house.dao.IHouseMapDbDao;
import com.kfwy.hkp.hos.house.dao.IWareHouseDbDao;
import com.kfwy.hkp.hos.house.dto.HousePriceDto;
import com.kfwy.hkp.hos.house.entity.HouseMapEntity;
import com.kfwy.hkp.hos.house.entity.HousePriceEntity;
import com.kfwy.hkp.hos.house.entity.WarehouseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Create By hjh on 2018/7/24
 */
@Service
public class HouseMapManagerImpl extends AbstractManager<HouseMapEntity> implements IHouseMapManager {

    private static Logger logger = LoggerFactory.getLogger(HouseMapManagerImpl.class);

    @Autowired
    private IHouseMapDbDao houseMapDbDao;

    @Autowired
    private IWareHouseDbDao wareHouseDbDao;

    @Override
    protected IMyBatisBaseDao<HouseMapEntity, Long> getMyBatisDao() {
        return this.houseMapDbDao;
    }

    @Override
    public List<HouseMapEntity> count(Map<String, Object> map) {
        map.put("isDeleted",false);
        return houseMapDbDao.count(map);
    }

    @Override
    public PageResult<WarehouseEntity> query(Map<String, Object> map,int start,int pageSize) {
        map.put("isDeleted",false);
        if(map.get("statusList") != null){
            map.put("statusList",map.get("statusList")+"");
        }
        if(map.get("status") != null){
            map.put("status",map.get("status")+"");
        }
        if(map.get("warehouseType") != null){
            map.put("warehouseType",map.get("warehouseType")+"");
        }
        return wareHouseDbDao.selectByStatement("selectByMapSimple",map, start, pageSize, "create_time", Boolean.FALSE);
    }

    @Override
    public List<HouseMapEntity> pointCount(Map<String, Object> map) {
        map.put("isDeleted",false);
        return houseMapDbDao.pointCount(map);
    }

    @Override
    public List<HousePriceDto> monthPrice(Map<String, Object> map) {
        return houseMapDbDao.monthPrice(map);
    }

    @Override
    public HousePriceEntity avgPriceByMonth(Map<String, Object> map) {
        return houseMapDbDao.avgPriceByMonth(map);
    }

    @Override
    public List<HouseMapEntity> togetherCount(Map<String, Object> param) {
        param.put("isDeleted",false);
        return houseMapDbDao.togetherCount(param);
    }
}