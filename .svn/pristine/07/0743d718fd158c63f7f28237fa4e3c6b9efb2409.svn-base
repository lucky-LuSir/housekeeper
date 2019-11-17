package com.kfwy.hkp.crm.houseowner.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjp on 2018/8/1.
 */
@Component
public class HouseownerType implements DictionaryProvider {

    public static String BIG_LANDLORD = "1";
    public static String SECOND_LANDLORD = "2";
    public static String SUBLET_THE_HOUSEHOLD = "3";

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(HouseownerType.class.getName(),"HouseownerType","业主类型"));
        list.add(new Dictionary(HouseownerType.class.getName(),BIG_LANDLORD,"大房东","HouseownerType"));
        list.add(new Dictionary(HouseownerType.class.getName(),SECOND_LANDLORD,"职业二房东","HouseownerType"));
        list.add(new Dictionary(HouseownerType.class.getName(),SUBLET_THE_HOUSEHOLD,"转租户","HouseownerType"));
        return list;
    }
}
