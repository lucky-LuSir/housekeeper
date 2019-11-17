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
public class HouseFileUse implements DictionaryProvider {



    public final static String HouseCover = "1";//封面展示图
    public final static String HouseInner = "2";//内部推广图
    public final static String HouseYard = "HouseYard";//外立面隐私图
    public final static String Protocol = "HouseProtocal";//委托协议图

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(getKey(),getParentCode(),"房源文件状态"));
        list.add(new Dictionary(getKey(),HouseCover,"房源封面",getParentCode()));
        list.add(new Dictionary(getKey(),HouseInner,"房源内部图片(推广图)",getParentCode()));
        list.add(new Dictionary(getKey(),Protocol,"房源得出租委托协议",getParentCode()));
        list.add(new Dictionary(getKey(),HouseYard,"房子外立面(隐私图)/园区道路",getParentCode()));
        return list;
    }

    public static String getKey(){
        return HouseFileUse.class.getName();
    }
    public static String getParentCode(){
        return HouseFileUse.class.getSimpleName();
    }

}
