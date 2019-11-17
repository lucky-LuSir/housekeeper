package com.kfwy.hkp.hos.house.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjh on 2018/9/5.
 */
@Component
public class HouseStyleType implements DictionaryProvider {
    public static String PuTong = "1";
    public static String LengLian = "2";
    public static String Gaotai = "3";
    public static String Danger = "4";
    public static String Factory = "5";


    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(HouseStyleType.class.getName(),"HouseStyleType","房源风格"));
        list.add(new Dictionary(HouseStyleType.class.getName(),PuTong,"普通仓库","HouseStyleType"));
        list.add(new Dictionary(HouseStyleType.class.getName(),LengLian,"冷链仓库","HouseStyleType"));
        list.add(new Dictionary(HouseStyleType.class.getName(),Gaotai,"高台仓库","HouseStyleType"));
        list.add(new Dictionary(HouseStyleType.class.getName(),Danger,"危险品库","HouseStyleType"));
        list.add(new Dictionary(HouseStyleType.class.getName(),Factory,"厂房","HouseStyleType"));
        return list;
    }
}
