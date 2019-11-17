package com.kfwy.hkp.common.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 申请合作的类型
 * Created by zjp on 2018/8/16.
 *
 * 1合作类型是客户表示我的客户与别人的房源合作  ---表示合作别人的房源(1218新版)
 * 2合作类型是房源表示我的房源与别人的客户合作  ---表示合作别人的客户(1218新版)
 *

 */
@Component
public class ApplyType implements DictionaryProvider {

    public final static String  HOPEHOUSE= "1";
    public final static String  HOPECUSTOMER= "2";

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(getKey(),"ApplyType","合作类型"));
        list.add(new Dictionary(getKey(),HOPEHOUSE,"房源","ApplyType"));
        list.add(new Dictionary(getKey(),HOPECUSTOMER,"客户","ApplyType"));
        return list;
    }

    public static String getKey(){
        return ApplyType.class.getName();
    }
}
