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
public class UserType implements DictionaryProvider{
    public final static String UserType = "UserType";
    public final static String Individual = "Individual";
    public final static String Employee = "Employee";
    public final static String JoinStaff = "JoinStaff";

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(getKey(), UserType, "用户类型"));
        list.add(new Dictionary(getKey(), Individual, "独立经纪人", UserType));
        list.add(new Dictionary(getKey(), Employee, "官方经纪人", UserType));
        list.add(new Dictionary(getKey(), JoinStaff, "加盟经纪人", UserType));
        return list;
    }

    public static String getKey() {
        return UserType.class.getName();
    }
}
