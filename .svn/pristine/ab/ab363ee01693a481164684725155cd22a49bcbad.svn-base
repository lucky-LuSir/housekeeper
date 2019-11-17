package com.kfwy.hkp.hrm.org.dept.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: HJH
 * @Date: 2019/8/6
 * @Description: TODO
 */
@Component
public class ProfitShareType implements DictionaryProvider {

    public final static String PLATFORM = "platform";
    public final static String PERSONAL = "personal";

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(getKey(), PLATFORM, "公司获客"));
        list.add(new Dictionary(getKey(), PERSONAL, "个人获客"));
        return list;
    }

    public static String getKey() {
        return ProfitShareType.class.getName();
    }
}
