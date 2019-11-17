package com.kfwy.hkp.hrm.org.dept.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;

import java.util.ArrayList;
import java.util.List;

/**
* @Description
* @Auth xpp
* @Date 2019/4/24 14:55
* @Version 1.0
* @Param
* @return
**/
public class PayStatus implements DictionaryProvider {


    public final static String PayStatus = "PayStatus";
    public final static String Begin = "Begin";
    public final static String FINISH = "Finish";

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(getKey(), PayStatus, "支付类型"));
        list.add(new Dictionary(getKey(), Begin, "支付开始", PayStatus));
        list.add(new Dictionary(getKey(), FINISH, "支付完成", PayStatus));
        return list;
    }

    public static String getKey() {
        return PayStatus.class.getName();
    }
}
