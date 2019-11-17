package com.kfwy.hkp.trade.order.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.trade.order.dao.IOrderPercentageDbDao;
import com.kfwy.hkp.trade.order.entity.OrderPercentageEntity;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Map;


/**
 * Created by zjp on 2018/8/15.
 */
@Repository
public class OrderPercentageDbDaoImpl extends AbstractMyBatisDao<OrderPercentageEntity,Long> implements IOrderPercentageDbDao {

    @Override
    public void deletePercentageByOrderCode(Map<String, Object> map) {
        this.getSqlSession().delete(this.mapperNamespace + "deletePercentageByOrderCode", map);
    }

    @Override
    public void updateIsDeleteByOrderCode(Map<String, Object> map) {
        this.getSqlSession().delete(this.mapperNamespace + "updateIsDeleteByOrderCode", map);
    }

    @Override
    public BigDecimal selectByMapAndStatus(Map<String, Object> map) {
        return this.getSqlSession().selectOne(this.mapperNamespace + "selectByMapAndStatus", map);
    }

    @Override
    public int simpleCountByMap(Map<String, Object> map) {
        return this.getSqlSession().selectOne(this.mapperNamespace + "simpleCountByMap",map);
    }
}
