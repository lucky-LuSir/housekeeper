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
public class OrderInvoiceType implements DictionaryProvider {


    public final static String Dedicated = "1";
    public final static String  Common= "2";

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(OrderInvoiceType.class.getName(),"OrderInvoiceType","订单开票类型"));
        list.add(new Dictionary(OrderInvoiceType.class.getName(),Dedicated,"专用发票","OrderInvoiceType"));
        list.add(new Dictionary(OrderInvoiceType.class.getName(),Common,"普通发票","OrderInvoiceType"));
        return list;
    }
    public static String getKey() {
        return OrderInvoiceType.class.getName();
    }

    public String getKey(String name){
        List<Dictionary> list = produce();
        for(Dictionary d:list){
            if(name.equals(d.getName())){
                return d.getCode();
            }
        }
        return null;
    }
}
