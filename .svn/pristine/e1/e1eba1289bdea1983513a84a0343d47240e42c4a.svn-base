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
public class CustomerNotFollowupType implements DictionaryProvider {

    public final static Integer ONEDAY = 1;
    public final static Integer ONE_TO_THREE = 2;
    public final static Integer THREE_TO_FIVE = 3;
    public final static Integer FIVE_TO_TEN = 4;
    public final static Integer MORE_THAN_TEN = 5;
    public final static Integer CUSTOM = 6;

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(CustomerNotFollowupType.class.getName(),"CustomerNotFollowupType","客户未跟进天数条件"));
        list.add(new Dictionary(CustomerNotFollowupType.class.getName(),String.valueOf(ONEDAY),"一天内","CustomerNotFollowupType"));
        list.add(new Dictionary(CustomerNotFollowupType.class.getName(),String.valueOf(ONE_TO_THREE),"一到三天","CustomerNotFollowupType"));
        list.add(new Dictionary(CustomerNotFollowupType.class.getName(),String.valueOf(THREE_TO_FIVE),"三到五天","CustomerNotFollowupType"));
        list.add(new Dictionary(CustomerNotFollowupType.class.getName(),String.valueOf(FIVE_TO_TEN),"五到十天","CustomerNotFollowupType"));
        list.add(new Dictionary(CustomerNotFollowupType.class.getName(),String.valueOf(MORE_THAN_TEN),"十天以上","CustomerNotFollowupType"));
        list.add(new Dictionary(CustomerNotFollowupType.class.getName(),String.valueOf(CUSTOM),"自定义","CustomerNotFollowupType"));
        return list;
    }
}
