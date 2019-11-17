package com.kfwy.hkp.sys.user.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:ChenJie Created by kfwy_it_009 on 2019/4/16.
 */
public class UserHasPass implements DictionaryProvider {
    public final static String UserHasPass = "UserHasPass";
    public final static String pass = "0";
    public final static String NoPass = "1";

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(getKey(), UserHasPass, "用户是否试岗通过"));
        list.add(new Dictionary(getKey(), pass, "通过", UserHasPass));
        list.add(new Dictionary(getKey(), NoPass, "未通过", UserHasPass));
        return list;
    }

    public static String getKey() {
        return UserHasPass.class.getName();
    }
}
