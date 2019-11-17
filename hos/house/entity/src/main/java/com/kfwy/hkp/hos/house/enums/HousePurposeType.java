package com.kfwy.hkp.hos.house.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjh on 2018/8/7.
 */
@Component
public class HousePurposeType implements DictionaryProvider {

    public static String STORAGE = "1";
    public static String PRODUCT = "2";
    public static String StorageProduction = "3";


    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(HousePurposeType.class.getName(),"HousePurposeType","房源用途"));
        list.add(new Dictionary(HousePurposeType.class.getName(),STORAGE,"只能做仓储","HousePurposeType"));
        list.add(new Dictionary(HousePurposeType.class.getName(),PRODUCT,"只能做生产","HousePurposeType"));
        list.add(new Dictionary(HousePurposeType.class.getName(),StorageProduction,"生产仓储都行","HousePurposeType"));
        return list;
    }
}
