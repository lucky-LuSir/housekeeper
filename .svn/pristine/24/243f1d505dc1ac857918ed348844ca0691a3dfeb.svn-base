package com.kfwy.hkp.crm.customer.dao.impl;

import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.crm.customer.dao.IFollowupHouseDbDao;
import com.kfwy.hkp.crm.customer.entity.FollowupHouseEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Create By hjh on 2018/8/30
 */
@Repository
public class FollowupHouseDbDaoImpl extends AbstractMyBatisDao<FollowupHouseEntity,Long> implements IFollowupHouseDbDao {

    @Override
    public PageResult<FollowupHouseEntity> selectCusSeeHouse(Map map,Integer start,Integer pageSize) {
        return this.selectByStatement("selectCusSeeHouse",map,start,pageSize);
    }
}
