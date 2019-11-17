package com.kfwy.hkp.hos.house.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzp on 2018/12/05.
 * 装修类型
 */
@Component
public class HouseDecorationType implements DictionaryProvider {

    public static final String MAO = "1";
    public static final String JIAN = "2";
    public static final String JING = "3";
    public static final String HAO = "4";
    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(HouseDecorationType.class.getName(),"DecorationType","房源类型"));
        list.add(new Dictionary(HouseDecorationType.class.getName(),MAO,"毛坯","DecorationType"));
        list.add(new Dictionary(HouseDecorationType.class.getName(),JIAN,"简装","DecorationType"));
        list.add(new Dictionary(HouseDecorationType.class.getName(),JING,"精装","DecorationType"));
        list.add(new Dictionary(HouseDecorationType.class.getName(),HAO,"豪华","DecorationType"));
        return list;
    }
}
