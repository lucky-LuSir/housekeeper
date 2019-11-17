package com.kfwy.hkp.hos.house.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjh on 2018/8/29.
 */
@Component
public class HouseStatus implements DictionaryProvider {
    /**
     * 热租中
     */
    public static String hotRenting = "1";
    /**
     * 已成交
     */
    public static String HasLease = "2";

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(HouseStatus.class.getName(),"HouseStatus","房源类型"));
        list.add(new Dictionary(HouseStatus.class.getName(),hotRenting,"热租中","HouseStatus"));
        list.add(new Dictionary(HouseStatus.class.getName(),HasLease,"已成交","HouseStatus"));
        return list;
    }
}
