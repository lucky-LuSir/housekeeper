package com.kfwy.hkp.hos.house.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjh on 2018/8/7.
 */
@Component
public class RepairReturn implements DictionaryProvider {

    public static final String NEEDREPAIR = "needRepair";
    public static final String NOTREPAIR = "notRepair";

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(HouseType.class.getName(),"RepairReturn","补全类型"));
        list.add(new Dictionary(HouseType.class.getName(),"needRepair","要补全","RepairReturn"));
        list.add(new Dictionary(HouseType.class.getName(),"notRepair","不用补全","RepairReturn"));
        return list;
    }
}
