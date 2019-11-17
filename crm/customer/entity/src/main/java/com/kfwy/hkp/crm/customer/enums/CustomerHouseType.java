package com.kfwy.hkp.crm.customer.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjp on 2018/8/8.·
 */
@Component
public class CustomerHouseType implements DictionaryProvider {

    public static String STORAGE = "1";
    public static String PRODUCT = "2";
    public static String StorageProduction="3";
    public static String CLEARING = "4";
    public static String OFFICES = "5";

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(CustomerHouseType.class.getName(),"CustomerHouseType","找房用途"));
        list.add(new Dictionary(CustomerHouseType.class.getName(),STORAGE,"仓储","CustomerHouseType"));
        list.add(new Dictionary(CustomerHouseType.class.getName(),PRODUCT,"生产","CustomerHouseType"));
        list.add(new Dictionary(CustomerHouseType.class.getName(),StorageProduction,"仓储生产","CustomerHouseType"));
        list.add(new Dictionary(CustomerHouseType.class.getName(),CLEARING,"空地","CustomerHouseType"));
        list.add(new Dictionary(CustomerHouseType.class.getName(),OFFICES,"写字楼","CustomerHouseType"));
        return list;
    }
}
