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
public class OrderStatus implements DictionaryProvider {

    public final static String NoSign = "1";
    public final static String HasDeal = "2";
    public final static String Destroy = "3";
    public final static String BadDebt = "4";
    public final static String BackPayment = "5";
    public final static String PartialPayment = "6";
    public final static String HasCancel = "7";
    public final static String BackPaymenting = "8";

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(OrderStatus.class.getName(),"OrderStatus","订单状态"));
        list.add(new Dictionary(OrderStatus.class.getName(),NoSign,"未签租赁合同","OrderStatus"));
        list.add(new Dictionary(OrderStatus.class.getName(),HasDeal,"已签租赁合同","OrderStatus"));
        list.add(new Dictionary(OrderStatus.class.getName(),Destroy,"已毁单","OrderStatus"));
        list.add(new Dictionary(OrderStatus.class.getName(),BadDebt,"已坏账","OrderStatus"));
        list.add(new Dictionary(OrderStatus.class.getName(),BackPayment,"已回款","OrderStatus"));
        list.add(new Dictionary(OrderStatus.class.getName(),PartialPayment,"异常回款","OrderStatus"));
        list.add(new Dictionary(OrderStatus.class.getName(),HasCancel,"已取消","OrderStatus"));
        list.add(new Dictionary(OrderStatus.class.getName(),BackPaymenting,"回款中","OrderStatus"));
        return list;
    }
}
