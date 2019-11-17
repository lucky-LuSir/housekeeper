package com.kfwy.hkp.sys.user.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @description TODO
 * @auth lzp
 * @date 2019/03/15 10:26
 */
@Component
public class UserSalaryType implements DictionaryProvider{
    public final static String SALARY_TYPE = "SALARY_TYPE";
    public final static String TEST = "1";
    public final static String PARTNER = "2";
    public final static String SMART = "3";

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(getKey(), SALARY_TYPE, "薪资类型"));
        list.add(new Dictionary(getKey(), TEST, "试用期底薪", SALARY_TYPE));
        list.add(new Dictionary(getKey(), PARTNER, "合伙人方案", SALARY_TYPE));
        list.add(new Dictionary(getKey(), SMART, "职能薪资", SALARY_TYPE));
        return list;
    }

    public static String getKey() {
        return UserSalaryType.class.getName();
    }
}
