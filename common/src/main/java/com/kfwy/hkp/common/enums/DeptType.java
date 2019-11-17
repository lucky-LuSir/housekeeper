package com.kfwy.hkp.common.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/12/27 11:24
 */
public class DeptType implements DictionaryProvider {


    public final static String DeptType = "DeptType";
    public final static String InnerDept = "InnerDept";
    public final static String JoinStore = "JoinStore";
    public final static String Team = "Team";

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(getKey(), DeptType, "部门类型"));
        list.add(new Dictionary(getKey(), InnerDept, "内部部门", DeptType));
        list.add(new Dictionary(getKey(), JoinStore, "招商选址加盟", DeptType));
        list.add(new Dictionary(getKey(), Team, "团队", DeptType));
        return list;
    }

    public static String getKey() {
        return DeptType.class.getName();
    }
}
