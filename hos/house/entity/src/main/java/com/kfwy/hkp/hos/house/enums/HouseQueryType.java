package com.kfwy.hkp.hos.house.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjh on 2018/9/3.
 */
@Component
public class HouseQueryType implements DictionaryProvider {

    public final static String MINE = "1";
    public final static String COLLECT = "2";
    public final static String AGENT = "3";
    public final static String PLATFORM = "4";
    public final static String NEARBY = "5";
    public final static String ALL = "6";
    public final static String UNION = "7";
    public final static String DEPT = "8";

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(HouseQueryType.class.getName(),"HouseQueryType","房源查询类型"));
        list.add(new Dictionary(HouseQueryType.class.getName(),MINE,"我的","HouseQueryType"));
        list.add(new Dictionary(HouseQueryType.class.getName(),COLLECT,"收藏","HouseQueryType"));
        list.add(new Dictionary(HouseQueryType.class.getName(),AGENT,"经纪人","HouseQueryType"));
        list.add(new Dictionary(HouseQueryType.class.getName(),PLATFORM,"平台","HouseQueryType"));
        list.add(new Dictionary(HouseQueryType.class.getName(),NEARBY,"附近","HouseQueryType"));
        list.add(new Dictionary(HouseQueryType.class.getName(),ALL,"全部","HouseQueryType"));
        list.add(new Dictionary(HouseQueryType.class.getName(),UNION,"合作房源","HouseQueryType"));
        list.add(new Dictionary(HouseQueryType.class.getName(),DEPT,"同部门房源","HouseQueryType"));
        return list;
    }
}
