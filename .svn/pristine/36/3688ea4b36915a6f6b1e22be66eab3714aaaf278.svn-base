package com.kfwy.hkp.sys.remind.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: HJH
 * @Date: 2019/4/19
 * @Description: TODO
 */
public class DownType implements DictionaryProvider {

    public final static String CUS = "cus";
    public final static String HOS = "hos";

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(DownType.class.getName(), "downType", "下架类型"));
        list.add(new Dictionary(DownType.class.getName(), CUS, "客户", "downType"));
        list.add(new Dictionary(DownType.class.getName(), HOS, "房源", "downType"));
        return list;
    }
}
