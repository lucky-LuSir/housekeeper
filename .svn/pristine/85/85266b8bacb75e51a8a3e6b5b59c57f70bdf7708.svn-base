package com.kfwy.hkp.common.dic;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjh on 2018/8/7.
 */
@Component
public class FireLevelType implements DictionaryProvider {

    public static String JIA_CLASS = "1";
    public static String YI_CLASS = "2";
    public static String BING_CLASS = "3";
    public static String DING_CLASS = "4";
    public static String WU_CLASS = "5";

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(FireLevelType.class.getName(), "FireLevelType", "消防等级类型"));
        list.add(new Dictionary(FireLevelType.class.getName(), JIA_CLASS, "甲类", "FireLevelType"));
        list.add(new Dictionary(FireLevelType.class.getName(), YI_CLASS, "乙类", "FireLevelType"));
        list.add(new Dictionary(FireLevelType.class.getName(), BING_CLASS, "丙类(消防栓+喷淋)", "FireLevelType"));
        list.add(new Dictionary(FireLevelType.class.getName(), DING_CLASS, "丁类(消防栓)", "FireLevelType"));
        list.add(new Dictionary(FireLevelType.class.getName(), WU_CLASS, "戊类", "FireLevelType"));
        return list;
    }
}
