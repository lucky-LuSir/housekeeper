package com.kfwy.hkp.crm.customer.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjp on 2018/8/7.
 */
@Component
public class CustomerFollowupFileUse implements DictionaryProvider {

public static final String CREDENTIALS = "1";

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(CustomerFollowupFileUse.class.getName(),"CustomerFollowupFileUse","客户带看文件类型"));
        list.add(new Dictionary(CustomerFollowupFileUse.class.getName(),CREDENTIALS,"带看凭证","CustomerFollowupFileUse"));
        return list;
    }
}
