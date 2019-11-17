package com.kfwy.hkp.cooperate.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjp on 2018/8/16.
 */
@Component
public class CooQueryType implements DictionaryProvider {

    public final static String MY_APPLY = "1";
    public final static String MY_RECEIVE = "2";

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(CooQueryType.class.getName(),"CooStatus","合作状态"));
        list.add(new Dictionary(CooQueryType.class.getName(),MY_APPLY,"我申请的","CooStatus"));
        list.add(new Dictionary(CooQueryType.class.getName(),MY_RECEIVE,"我收到的","CooStatus"));
        return list;
    }
}
