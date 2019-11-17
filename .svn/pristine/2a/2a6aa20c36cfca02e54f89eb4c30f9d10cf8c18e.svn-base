package com.kfwy.hkp.crm.customer.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjh on 2018/8/23.
 */
@Component
public class CustomerFollowupType implements DictionaryProvider {

    public static String SeeHouse = "1";
    public static String VisitPhone = "2";
    public static String VisitDoor = "3";


    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(CustomerFollowupType.class.getName(),"CustomerFollowupType","客户状态"));
        list.add(new Dictionary(CustomerFollowupType.class.getName(),"1","实地带看","CustomerFollowupType"));
        list.add(new Dictionary(CustomerFollowupType.class.getName(),"2","电话拜访","CustomerFollowupType"));
        list.add(new Dictionary(CustomerFollowupType.class.getName(),"3","上门拜访","CustomerFollowupType"));
        return list;
    }
}
