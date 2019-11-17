package com.kfwy.hkp.sys.notice.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjh on 2018/8/16.
 */
@Component
public class NoticeType implements DictionaryProvider {

    public static final String HOUSE = "1";
    public static final String CUSTOMER = "2";
    //客户或者房源推送
    public static final String Push = "3";
    public static final String Cooperation = "4";
    public static final String SYSTEM = "5";
    public static final String ORDER="6";
    public static final String DOWN = "7";
    public static final String FOCUS_FOR_THE_GUEST = "8";
    public static final String CUS_PUSH_FOR_ME = "9";

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(NoticeType.class.getName(),"NoticeType","消息类型"));
        list.add(new Dictionary(NoticeType.class.getName(),HOUSE,"房源","NoticeType"));
        list.add(new Dictionary(NoticeType.class.getName(),CUSTOMER,"客户","NoticeType"));
        list.add(new Dictionary(NoticeType.class.getName(),Push,"推送","NoticeType"));
        list.add(new Dictionary(NoticeType.class.getName(),SYSTEM,"系统","NoticeType"));
        list.add(new Dictionary(NoticeType.class.getName(),Cooperation,"合作","NoticeType"));
        list.add(new Dictionary(NoticeType.class.getName(),ORDER,"订单","NoticeType"));
        list.add(new Dictionary(NoticeType.class.getName(),DOWN,"下架提醒","NoticeType"));
        list.add(new Dictionary(NoticeType.class.getName(),FOCUS_FOR_THE_GUEST,"集中获客","NoticeType"));
        list.add(new Dictionary(NoticeType.class.getName(),CUS_PUSH_FOR_ME,"推送给我的客户","NoticeType"));

        return list;
    }
}
