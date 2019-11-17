package com.kfwy.hkp.hos.house.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HouseStructureType implements DictionaryProvider {

    public static String STEEL_STRUCTURE="1";
    public static String FRAMEWORK = "2";
    public static String STEEL_MIXED="3";
    public static String BRICK_MIXED="4";


    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(HouseStructureType.class.getName(),"HouseStructureType","房屋结构类型"));
        list.add(new Dictionary(HouseStructureType.class.getName(),STEEL_STRUCTURE,"钢结构","HouseStructureType"));
        list.add(new Dictionary(HouseStructureType.class.getName(),FRAMEWORK,"框架","HouseStructureType"));
        list.add(new Dictionary(HouseStructureType.class.getName(),STEEL_MIXED,"钢混","HouseStructureType"));
        list.add(new Dictionary(HouseStructureType.class.getName(),BRICK_MIXED,"砖混","HouseStructureType"));
        return list;
    }
}
