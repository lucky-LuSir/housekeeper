package com.kfwy.hkp.sys.user.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户认证状态
 * @version 1.0
 * @description TODO
 * @auth Lzp
 * @date 2018/12/20
 */
@Component
public class UserCAStatus implements DictionaryProvider{
    public final static String UserCAStatus = "UserCAStatus";
    public final static String UnCertified = "UnCertified";
    public final static String Progress = "Progress";
    public final static String Certified = "Certified";

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(getKey(), UserCAStatus, "用户认证状态"));
        list.add(new Dictionary(getKey(), UnCertified, "未认证", UnCertified));
        list.add(new Dictionary(getKey(), Progress, "认证中", Progress));
        list.add(new Dictionary(getKey(), Certified, "已认证", Certified));
        return list;
    }

    public static String getKey() {
        return UserCAStatus.class.getName();
    }
}
