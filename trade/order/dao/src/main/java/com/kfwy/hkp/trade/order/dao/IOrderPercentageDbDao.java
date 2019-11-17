package com.kfwy.hkp.trade.order.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.trade.order.entity.OrderPercentageEntity;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by zjp on 2018/8/15.
 */
public interface IOrderPercentageDbDao extends IMyBatisBaseDao<OrderPercentageEntity,Long>{

    void deletePercentageByOrderCode(Map<String, Object> map);

    void updateIsDeleteByOrderCode(Map<String, Object> map);
    /*
    *  查询关联订单状态的分成
    * */
    BigDecimal selectByMapAndStatus(Map<String, Object> map);

    public int simpleCountByMap(Map<String,Object> map);
}
