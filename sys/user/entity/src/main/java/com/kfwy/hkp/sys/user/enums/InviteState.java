package com.kfwy.hkp.sys.user.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @description 
 * @auth xpp
 * @date 2018/10/27 00:26
 */
@Component
public class InviteState implements DictionaryProvider{
    public final static String InviteState = "InviteState";
    public final static String HasInvite = "HasInvite";
    public final static String NotInvite = "NotInvite";

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(getKey(), InviteState, "用户类型"));
        list.add(new Dictionary(getKey(), HasInvite, "已邀请", InviteState));
        list.add(new Dictionary(getKey(), NotInvite, "未邀请", InviteState));
        return list;
    }

    public static String getKey() {
        return InviteState.class.getName();
    }
}
