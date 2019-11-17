package com.kfwy.hkp.trade.order.business;

import com.gniuu.framework.common.business.IManager;
import com.gniuu.framework.dataaccess.PageResult;
import com.kfwy.hkp.trade.order.entity.OrderEntity;
import com.kfwy.hkp.trade.order.entity.OrderFollowupEntity;
import java.util.Map;

/**
 * Created by kfwy_it_003 on 2017/8/2.
 */
public interface IOrderFollowupManager extends IManager<OrderFollowupEntity> {
    int insert(OrderFollowupEntity orderFollowup);
    int update(OrderFollowupEntity orderFollowup);
    PageResult<OrderFollowupEntity> select(Map<String,Object> map, int start, int pageSize);
}
