package com.kfwy.hkp.trade.order.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.trade.order.entity.OrderPercentageApplyEntity;

import java.util.List;
import java.util.Map;

/**
 * 订单修改分成  申请 dao
 */
public interface IOrderPercentageApplyDbDao extends IMyBatisBaseDao<OrderPercentageApplyEntity,Long>{

    void deletePercentageByOrderCode(Map<String, Object> map);

    void updateIsDeleteByOrderCode(Map<String, Object> map);
    /*
    *  查询关联订单状态的分成
    * */
    List<OrderPercentageApplyEntity> selectByMapAndStatus(Map<String, Object> map);

    public int simpleCountByMap(Map<String, Object> map);
}
