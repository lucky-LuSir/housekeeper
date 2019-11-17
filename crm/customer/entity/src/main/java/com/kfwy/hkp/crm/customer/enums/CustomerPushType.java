package com.kfwy.hkp.crm.customer.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kfwy_it_013
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/10/25 15:47
 */
@Component
public class CustomerPushType implements DictionaryProvider {

    public final static String CustomerPushType = "CustomerPushType";
    public final static String Personal = "Personal";
    public final static String Department = "Department";

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(getKey(),CustomerPushType,"客户推送类型"));
        list.add(new Dictionary(getKey(),Personal,"个人",CustomerPushType));
        list.add(new Dictionary(getKey(),Department,"部门",CustomerPushType));
        return list;
    }

    public static String getKey(){
        return CustomerPushType.class.getName();
    }
}
