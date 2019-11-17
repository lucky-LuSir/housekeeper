package com.kfwy.hkp.crm.customer.enums;

import com.gniuu.framework.common.enums.SexType;
import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import com.gniuu.framework.dic.DictionaryStorage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zjp on 2018/8/7.
 */
@Component
public class CusSortType implements DictionaryProvider {

    public final static Integer ENTER_TIME_ASC= 1;
    public final static Integer ENTER_TIME_DESC = 2;
    public final static Integer CREATE_TIME_ASC = 3;
    public final static Integer CREATE_TIME_DESC = 4;
    public final static Integer LAST_FOLLOWUP_TIME_ASC = 5;
    public final static Integer LAST_FOLLOWUP_TIME_DESC = 6;
    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(CusSortType.class.getName(),"CusSortType","客户自定义排序类型"));
        list.add(new Dictionary(CusSortType.class.getName(),String.valueOf(ENTER_TIME_ASC),"enter_time,true","CusSortType"));
        list.add(new Dictionary(CusSortType.class.getName(),String.valueOf(ENTER_TIME_DESC),"enter_time,false","CusSortType"));
        list.add(new Dictionary(CusSortType.class.getName(),String.valueOf(CREATE_TIME_ASC),"create_time,true","CusSortType"));
        list.add(new Dictionary(CusSortType.class.getName(),String.valueOf(CREATE_TIME_DESC),"create_time,false","CusSortType"));
        list.add(new Dictionary(CusSortType.class.getName(),String.valueOf(LAST_FOLLOWUP_TIME_ASC),"last_followup_time,true","CusSortType"));
        list.add(new Dictionary(CusSortType.class.getName(),String.valueOf(LAST_FOLLOWUP_TIME_DESC),"last_followup_time,false","CusSortType"));
        return list;
    }

    public static String getKey() {
        return CusSortType.class.getName();
    }


    public static Map setCusSortType(String cusSortType){
        Map map = new HashMap(2);
        //设置排序类型
        String orderBy = "last_upshelf_time";
        boolean descOrAsc;
        if (cusSortType != null) {
            String[] s = DictionaryStorage.get(CusSortType.getKey(),
                    cusSortType).getName().split(",");
            orderBy = s[0];
            descOrAsc = Boolean.parseBoolean(s[1]);
        }else {
            descOrAsc = false;
        }
        map.put("orderBy",orderBy);
        map.put("descOrAsc",descOrAsc);
        return map;
    }

}
