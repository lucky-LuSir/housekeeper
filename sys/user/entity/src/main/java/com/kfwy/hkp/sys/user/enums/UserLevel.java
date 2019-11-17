package com.kfwy.hkp.sys.user.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/10/27 00:26
 */
@Component
public class UserLevel implements DictionaryProvider{
    public final static String USER_LEVEL = "USER_LEVEL";
    public final static String K0 = "K0";
    public final static String K1 = "K1";
    public final static String K2 = "K2";
    public final static String T0 = "T0";

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(getKey(), USER_LEVEL, "用户级别"));
        list.add(new Dictionary(getKey(), K0, "库房无忧级别用户", USER_LEVEL));
        list.add(new Dictionary(getKey(), K1, "认证会员用户（付费）", USER_LEVEL));
        list.add(new Dictionary(getKey(), K2, "普通用户", USER_LEVEL));
        list.add(new Dictionary(getKey(), T0, "认证团队用户（付费）", USER_LEVEL));
        return list;
    }

    public static String getKey() {
        return UserLevel.class.getName();
    }
}
