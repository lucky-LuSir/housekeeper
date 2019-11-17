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
public class HouseLocationFileUse implements DictionaryProvider {

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(HouseLocationFileUse.class.getName(),"HouseLocationFileUse","房源位置文件状态"));
        list.add(new Dictionary(HouseLocationFileUse.class.getName(),"1","门口360°环视短视频","HouseLocationFileUse"));
        list.add(new Dictionary(HouseLocationFileUse.class.getName(),"2","门口照片","HouseLocationFileUse"));
        return list;
    }
}
