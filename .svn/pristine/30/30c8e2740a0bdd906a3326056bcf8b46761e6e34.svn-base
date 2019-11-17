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
public class OrderQueryType implements DictionaryProvider {

    public final static String MY_ORDER = "1";
    public final static String MY_PERCENTAGE = "2";
    public final static String MY_CREATE = "3";
    public final static String ALL="4";

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(OrderQueryType.class.getName(),"OrderQueryType","订单查询类型"));
        list.add(new Dictionary(OrderQueryType.class.getName(),MY_ORDER,"我的订单","OrderQueryType"));
        list.add(new Dictionary(OrderQueryType.class.getName(),MY_PERCENTAGE,"我的分成","OrderQueryType"));
        list.add(new Dictionary(OrderQueryType.class.getName(),MY_CREATE,"我的创建","OrderQueryType"));
        list.add(new Dictionary(OrderQueryType.class.getName(),ALL,"全部","OrderQueryType"));
        return list;
    }
}
