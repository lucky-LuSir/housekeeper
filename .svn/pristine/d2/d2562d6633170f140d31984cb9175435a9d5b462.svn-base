package com.kfwy.hkp.crm.customer.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zjp
 * @date 2018/8/7
 */
@Component
public class CustomerGainType implements DictionaryProvider {

    public final static String COMPANY_GAIN = "1";
    public final static String PERSONAL_GAIN = "2";

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(CustomerGainType.class.getName(),"CustomerGainType","获客类型"));
        list.add(new Dictionary(CustomerGainType.class.getName(),COMPANY_GAIN,"公司获客","CustomerGainType"));
        list.add(new Dictionary(CustomerGainType.class.getName(),PERSONAL_GAIN,"个人获客","CustomerGainType"));
        return list;
    }
}
