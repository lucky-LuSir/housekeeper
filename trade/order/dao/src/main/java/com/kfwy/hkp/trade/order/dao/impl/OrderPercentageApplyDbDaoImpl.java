package com.kfwy.hkp.trade.order.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.trade.order.dao.IOrderPercentageApplyDbDao;
import com.kfwy.hkp.trade.order.entity.OrderPercentageApplyEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * 订单修改分成  申请 dao实现
 */
@Repository
public class OrderPercentageApplyDbDaoImpl extends AbstractMyBatisDao<OrderPercentageApplyEntity,Long> implements IOrderPercentageApplyDbDao {

    @Override
    public void deletePercentageByOrderCode(Map<String, Object> map) {
        this.getSqlSession().delete(this.mapperNamespace + "deletePercentageByOrderCode", map);
    }

    @Override
    public void updateIsDeleteByOrderCode(Map<String, Object> map) {
        this.getSqlSession().delete(this.mapperNamespace + "updateIsDeleteByOrderCode", map);
    }

    @Override
    public List<OrderPercentageApplyEntity> selectByMapAndStatus(Map<String, Object> map) {
        return this.getSqlSession().selectList(this.mapperNamespace + "selectByMapAndStatus", map);
    }

    @Override
    public int simpleCountByMap(Map<String, Object> map) {
        return this.getSqlSession().selectOne(this.mapperNamespace + "simpleCountByMap",map);
    }
}
