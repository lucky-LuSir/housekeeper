package com.kfwy.hkp.hos.house.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.hos.house.dao.IHouseLocationDbDao;
import com.kfwy.hkp.hos.house.entity.HouseLocationEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Create By hjh on 2018/7/31
 */
@Repository
public class HouseLocationDbDaoImpl extends AbstractMyBatisDao<HouseLocationEntity,Long> implements IHouseLocationDbDao {
    @Override
    public List<HouseLocationEntity> selectLocByHos(Map<String, Object> param) {
        return this.getSqlSession().selectList(this.mapperNamespace + "selectLocByHos", param);
    }
}
