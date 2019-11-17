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
public class CooStatus implements DictionaryProvider {

    public final static String WAIT = "1";
    public final static String REFUSE = "2";
    public final static String ACCEPT = "3";
    public final static String FOLLOW = "4";
    public final static String CONTRACT = "5";
    public final static String END = "6";

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(CooStatus.class.getName(),"CooStatus","合作状态"));
        list.add(new Dictionary(CooStatus.class.getName(),WAIT,"待审核","CooStatus"));
        list.add(new Dictionary(CooStatus.class.getName(),REFUSE,"已拒绝","CooStatus"));
        list.add(new Dictionary(CooStatus.class.getName(),ACCEPT,"已接受","CooStatus"));
        list.add(new Dictionary(CooStatus.class.getName(),FOLLOW,"已带看","CooStatus"));
        list.add(new Dictionary(CooStatus.class.getName(),CONTRACT,"已签约","CooStatus"));
        list.add(new Dictionary(CooStatus.class.getName(),END,"已结束","CooStatus"));
        return list;
    }
}
