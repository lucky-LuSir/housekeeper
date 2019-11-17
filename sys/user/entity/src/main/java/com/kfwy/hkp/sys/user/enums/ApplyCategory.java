package com.kfwy.hkp.sys.user.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjh on 2018/8/7.
 */
@Component
public class ApplyCategory implements DictionaryProvider {

    public static final String ASKFOR = "askfor";
    public static final String AGREE = "agree";
    public static final String REJECTED = "rejected";

    public static final String WAITJOIN = "waitJoin";
    public static final String ACCOUNTAUDIT = "AccountAudit";

    public final static String APPLYCATEGORY = "APPLYCATEGORY";

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(getKey(), APPLYCATEGORY, "类型"));
        //加入状态
        list.add(new Dictionary(getKey(), WAITJOIN, "待加入", APPLYCATEGORY));
        list.add(new Dictionary(getKey(), ACCOUNTAUDIT, "账号审核中", APPLYCATEGORY));
        //离开人状态
        list.add(new Dictionary(getKey(), ASKFOR, "已申请", APPLYCATEGORY));
        list.add(new Dictionary(getKey(), AGREE, "已同意", APPLYCATEGORY));
        list.add(new Dictionary(getKey(), REJECTED, "已拒绝", APPLYCATEGORY));
        return list;
    }

    public static String getKey() {
        return ApplyCategory.class.getName();
    }
}
