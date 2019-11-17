package com.kfwy.hkp.crm.customer.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 客户未跟进天数查询枚举
 */
@Component
public class CustomerPushQueryType implements DictionaryProvider {

    public final static String MINE = "1";
    public final static String PUSH_TO_ME = "2";
    public final static String PUSH_TO_DEPT = "3";

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(CustomerPushQueryType.class.getName(),"CustomerPushQueryType","客户未跟进天数条件"));
        list.add(new Dictionary(CustomerPushQueryType.class.getName(),MINE,"我的","CustomerNotFollowupType"));
        list.add(new Dictionary(CustomerPushQueryType.class.getName(),PUSH_TO_ME,"推送给我的","CustomerPushQueryType"));
        list.add(new Dictionary(CustomerPushQueryType.class.getName(),PUSH_TO_DEPT,"推送给部门的","CustomerPushQueryType"));
        return list;
    }
}
