package com.kfwy.hkp.hos.house.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.hos.house.dao.IHouseMapDbDao;
import com.kfwy.hkp.hos.house.dto.HousePriceDto;
import com.kfwy.hkp.hos.house.entity.HouseMapEntity;
import com.kfwy.hkp.hos.house.entity.HousePriceEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Create By hjh on 2018/12/1
 */
@Repository
public class HouseMapDbDaoImpl extends AbstractMyBatisDao<HouseMapEntity,Long> implements IHouseMapDbDao {

    @Override
    public List<HouseMapEntity> count(Map<String, Object> map) {
        return this.getSqlSession().selectList(this.mapperNamespace+"selectHouseCount",map);
    }

    @Override
    public List<HouseMapEntity> pointCount(Map<String, Object> map) {
        return this.getSqlSession().selectList(this.mapperNamespace+"selectHouseCountByMapPoint",map);
    }

    @Override
    public List<HousePriceDto> monthPrice(Map<String, Object> map) {
        return this.getSqlSession().selectList(this.mapperNamespace+"monthPrice",map);
    }

    @Override
    public HousePriceEntity avgPriceByMonth(Map<String, Object> map) {
        return this.getSqlSession().selectOne(this.mapperNamespace+"avgPriceByMonth",map);
    }

    @Override
    public List<HouseMapEntity> togetherCount(Map<String, Object> param) {
        return this.getSqlSession().selectList(this.mapperNamespace+"selectTogetherCount",param);
    }
}
