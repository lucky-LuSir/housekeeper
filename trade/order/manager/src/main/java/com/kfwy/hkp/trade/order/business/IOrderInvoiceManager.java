package com.kfwy.hkp.trade.order.business;

import com.gniuu.framework.common.business.IManager;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.AbstractServiceResponse;
import com.kfwy.hkp.trade.order.entity.OrderInvoiceEntity;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @Author:ChenJie Created by kfwy_it_009 on 2018/11/11.
 */
public interface IOrderInvoiceManager  extends IManager<OrderInvoiceEntity> {
    int insert(List<OrderInvoiceEntity> orderInvoiceEntity,AbstractServiceResponse response);

    PageResult<OrderInvoiceEntity> selectByMap(Map<String, Object> map, int start, int pageSize);

    public int dealshui() throws ParseException;

}
