package com.kfwy.hkp.hos.house.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.hos.house.dao.IHouseDbDao;
import com.kfwy.hkp.hos.house.dao.IHouseFollowupDbDao;
import com.kfwy.hkp.hos.house.entity.HouseEntity;
import com.kfwy.hkp.hos.house.entity.HouseFollowupEntity;
import com.kfwy.hkp.hrm.org.employee.entity.EmployeeEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/*
*   Create By hjh @Date 2018/10/11
* */
@Repository
public class HouseFollowupDbDaoImpl extends AbstractMyBatisDao<HouseFollowupEntity,Long> implements IHouseFollowupDbDao {

    @Override
    public int selectFollowupCount(Map<String, Object> map) {
        return this.getSqlSession().selectOne(this.mapperNamespace+"selectFollowupCount",map);
    }
}
