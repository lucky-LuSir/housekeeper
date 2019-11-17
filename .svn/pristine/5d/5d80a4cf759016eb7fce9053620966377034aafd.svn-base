package com.kfwy.hkp.trade.order.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjp on 2018/8/16.
 */
@Component
public class OrderExportType implements DictionaryProvider {


    public final static String OrderStatistics = "1";
    public final static String  OrderDivideInto= "2";
    public final static String OrderTransDept = "3";
    public final static String OrderUpdateLog="4";

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(OrderExportType.class.getName(),"OrderExportType","订单导出类型"));
        list.add(new Dictionary(OrderExportType.class.getName(),OrderStatistics,"订单统计导出","OrderExportType"));
        list.add(new Dictionary(OrderExportType.class.getName(),OrderDivideInto,"订单分成导出","OrderExportType"));
        list.add(new Dictionary(OrderExportType.class.getName(),OrderTransDept,"跨部门订单导出","OrderExportType"));
        list.add(new Dictionary(OrderExportType.class.getName(),OrderUpdateLog,"订单修改历史导出","OrderExportType"));
        return list;
    }
}
