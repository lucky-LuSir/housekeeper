package com.kfwy.hkp.crm.customer.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjp on 2018/7/16.
 */
@Component
public class CustomerStatus implements DictionaryProvider {

    public static String FOLLOWING = "1";
    public static String HASDEAL = "2";


    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(CustomerStatus.class.getName(),"CustomerStatus","客户状态"));
        list.add(new Dictionary(CustomerStatus.class.getName(),FOLLOWING,"跟进中","CustomerStatus"));
        list.add(new Dictionary(CustomerStatus.class.getName(),HASDEAL,"已租好","CustomerStatus"));
        return list;
    }
}
