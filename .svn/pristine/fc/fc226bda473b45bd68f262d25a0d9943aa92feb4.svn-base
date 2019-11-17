package com.kfwy.hkp.crm.customer.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjp on 2018/8/11.
 */
@Component
public class CustomerQueryType implements DictionaryProvider {

    public final static String MINE = "1";
    public final static String AGENT = "2";
    public final static String PLATFORM = "3";
    public final static String COLLECT = "4";
    public final static String DEPT = "5";
    public final static String PULL_PRIVATE="6";
    public final static String FOCUS_CUS="7";
    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(CustomerQueryType.class.getName(),"CustomerQueryType","客户类型"));
        list.add(new Dictionary(CustomerQueryType.class.getName(),MINE,"我的","CustomerQueryType"));
        list.add(new Dictionary(CustomerQueryType.class.getName(),AGENT,"经纪人","CustomerQueryType"));
        list.add(new Dictionary(CustomerQueryType.class.getName(),PLATFORM,"平台","CustomerQueryType"));
        list.add(new Dictionary(CustomerQueryType.class.getName(),COLLECT,"收藏","CustomerQueryType"));
        list.add(new Dictionary(CustomerQueryType.class.getName(),DEPT,"本部门","CustomerQueryType"));
        list.add(new Dictionary(CustomerQueryType.class.getName(),PULL_PRIVATE,"平台拉私","CustomerQueryType"));
        list.add(new Dictionary(CustomerQueryType.class.getName(),FOCUS_CUS,"集中获客","CustomerQueryType"));
        return list;
    }
}
