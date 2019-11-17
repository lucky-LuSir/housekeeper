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
public class CustomerFileUse implements DictionaryProvider {

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(CustomerFileUse.class.getName(),"CustomerFileUse","客户文件类型"));
        list.add(new Dictionary(CustomerFileUse.class.getName(),"1","客户名片","CustomerFileUse"));
        list.add(new Dictionary(CustomerFileUse.class.getName(),"2","客户委托协议","CustomerFileUse"));
        list.add(new Dictionary(CustomerFileUse.class.getName(),"3","客户带看凭证","CustomerFileUse"));
        return list;
    }
}
