package com.kfwy.hkp.trade.order.business;

import com.gniuu.framework.common.business.IManager;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.AbstractServiceResponse;
import com.kfwy.hkp.trade.order.entity.OrderPaybackEntity;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @Author:ChenJie Created by kfwy_it_009 on 2018/11/10.
 */
public interface IOrderPaybackManager  extends IManager<OrderPaybackEntity> {

    int insert(List<OrderPaybackEntity> pe, AbstractServiceResponse response) throws Exception;

    List<OrderPaybackEntity> selectByMap(Map<String, Object> map);

    PageResult<OrderPaybackEntity> selectByMap(Map<String, Object> map, int start, int pageSize);

    int createWage() throws ParseException;
    OrderPaybackEntity selectPayBackSum(Map<String,Object> param);


}
