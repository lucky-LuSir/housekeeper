package com.kfwy.hkp.hos.house.enums;

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
public class SharePoolType implements DictionaryProvider {

    public final static String  CUS_SHARE_POOL= "cus";
    public final static String  HOS_SHARE_POOL= "hos";

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(getKey(),"SharePoolType","共享池类型"));
        list.add(new Dictionary(getKey(),CUS_SHARE_POOL,"客户共享","SharePoolType"));
        list.add(new Dictionary(getKey(),HOS_SHARE_POOL,"房源共享","SharePoolType"));
        return list;
    }

    public static String getKey(){
        return SharePoolType.class.getName();
    }
}
