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
public class HouseType implements DictionaryProvider {

    public static final String PLATFORM = "1";
    public static final String AGENT = "2";

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(HouseType.class.getName(),"HouseType","房源类型"));
        list.add(new Dictionary(HouseType.class.getName(),"1","平台","HouseType"));
        list.add(new Dictionary(HouseType.class.getName(),"2","经纪人","HouseType"));
        return list;
    }
}
