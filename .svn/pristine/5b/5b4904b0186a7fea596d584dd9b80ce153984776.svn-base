package com.kfwy.hkp.trade.wage.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzp on 2019/3/27.
 */
@Component
public class CommissionType implements DictionaryProvider {

    public final static String FIXED = "1";
    public final static String PERSONAL = "2";
    public final static String GROUPMANAGER = "3";
    public final static String LEADERMANAGER = "4";
    public final static String SENIORMANAGER = "5";
    public final static String RECOMMEND = "6";

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(CommissionType.class.getName(),"CommissionType","提成类型"));
        list.add(new Dictionary(CommissionType.class.getName(),FIXED,"固定底薪模式提成","CommissionType"));
        list.add(new Dictionary(CommissionType.class.getName(),PERSONAL,"合伙人模式提成","CommissionType"));
        list.add(new Dictionary(CommissionType.class.getName(),GROUPMANAGER,"组长管理提成","CommissionType"));
        list.add(new Dictionary(CommissionType.class.getName(),LEADERMANAGER,"经理管理提成","CommissionType"));
        list.add(new Dictionary(CommissionType.class.getName(),SENIORMANAGER,"资深经理管理提成","CommissionType"));
        list.add(new Dictionary(CommissionType.class.getName(),RECOMMEND,"推荐提成","CommissionType"));
        return list;
    }
}
