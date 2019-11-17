package com.kfwy.hkp.common.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjh on 2018/8/7.
 */
@Component
public class ApplyState implements DictionaryProvider {

    public static final String ASKFOR = "askfor";
    public static final String SHARED = "shared";
    public static final String REJECTED = "rejected";

    public static final String DELETESHARE = "deleteshare";

    public final static String APPLYSTATE = "ApplyState";

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(getKey(), APPLYSTATE, "类型"));
        list.add(new Dictionary(getKey(), ASKFOR, "申请中", APPLYSTATE));
        list.add(new Dictionary(getKey(), SHARED, "已共享", APPLYSTATE));
        list.add(new Dictionary(getKey(), REJECTED, "已拒绝", APPLYSTATE));
        list.add(new Dictionary(getKey(), DELETESHARE, "断开共享", APPLYSTATE));
        return list;
    }

    public static String getKey() {
        return ApplyState.class.getName();
    }
}
