package com.kfwy.hkp.hos.house.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import com.gniuu.framework.dic.DictionaryStorage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 房源排序类型 枚举
 */
@Component
public class OrderFlagType implements DictionaryProvider {

    public final static Integer ENTER_TIME_ASC= 1;
    public final static Integer ENTER_TIME_DESC = 2;
    public final static Integer CREATE_TIME_ASC = 3;
    public final static Integer CREATE_TIME_DESC = 4;
    public final static Integer LAST_FOLLOWUP_TIME_ASC = 5;
    public final static Integer LAST_FOLLOWUP_TIME_DESC = 6;

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(OrderFlagType.class.getName(),"OrderFlagType","房源自定义排序"));
        list.add(new Dictionary(OrderFlagType.class.getName(),String.valueOf(ENTER_TIME_ASC),"enter_time,true","OrderFlagType"));
        list.add(new Dictionary(OrderFlagType.class.getName(),String.valueOf(ENTER_TIME_DESC),"enter_time,false","OrderFlagType"));
        list.add(new Dictionary(OrderFlagType.class.getName(),String.valueOf(CREATE_TIME_ASC),"create_time,true","OrderFlagType"));
        list.add(new Dictionary(OrderFlagType.class.getName(),String.valueOf(CREATE_TIME_DESC),"create_time,false","OrderFlagType"));
        list.add(new Dictionary(OrderFlagType.class.getName(),String.valueOf(LAST_FOLLOWUP_TIME_ASC),"last_followup_time,true","OrderFlagType"));
        list.add(new Dictionary(OrderFlagType.class.getName(),String.valueOf(LAST_FOLLOWUP_TIME_DESC),"last_followup_time,false","OrderFlagType"));
        return list;
    }

    public static String getKey() {
        return OrderFlagType.class.getName();
    }

    /**
     * 设置自定义时间排序
     * @param map
     * @param orderFlagType
     */
    public static void setOrderFlagType(Map map,String orderFlagType){
        //设置排序类型
        String orderBy = "create_time";
        boolean descOrAsc;
        if (orderFlagType != null) {
            String[] s = DictionaryStorage.get(OrderFlagType.getKey(),
                    orderFlagType).getName().split(",");
            orderBy = s[0];
            descOrAsc = Boolean.parseBoolean(s[1]);
        }else {
            descOrAsc = false;
        }
        map.put("orderBy",orderBy);
        map.put("descOrAsc",descOrAsc);
    }
}
