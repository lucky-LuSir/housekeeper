package com.kfwy.hkp.trade.order.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xpp on 2018/8/16.
 */
@Component
public class MoneyPercentType implements DictionaryProvider {

    public final static String SERVICE_PERCENT = "1";
    public final static String PERSON_PERCENT = "2";

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(MoneyPercentType.class.getName(),"MoneyPercentType","金额分成类型"));
        list.add(new Dictionary(MoneyPercentType.class.getName(),SERVICE_PERCENT,"客服固定分成","MoneyPercentType"));
        list.add(new Dictionary(MoneyPercentType.class.getName(),PERSON_PERCENT,"分成人分成","MoneyPercentType"));
        return list;
    }
}
