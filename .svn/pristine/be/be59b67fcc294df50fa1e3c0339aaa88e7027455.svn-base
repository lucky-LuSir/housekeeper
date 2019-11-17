package com.kfwy.hkp.hos.house.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenjie on 2019/8/8.
 */
@Component
public class EntrustType implements DictionaryProvider {

    public static final Integer CUS = 1;
    public static final Integer HOS = 2;

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(EntrustType.class.getName(),"EntrustType","委托类型"));
        list.add(new Dictionary(EntrustType.class.getName(),String.valueOf(CUS),"客户","EntrustType"));
        list.add(new Dictionary(EntrustType.class.getName(),String.valueOf(HOS),"房源","EntrustType"));
        return list;
    }
}
