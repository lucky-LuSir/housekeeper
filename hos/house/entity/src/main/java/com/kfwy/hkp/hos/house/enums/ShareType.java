package com.kfwy.hkp.hos.house.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class ShareType implements DictionaryProvider {

    public final static String  RESPECTIVELY_SHARING= "1";
    public final static String  UNIFIED_SHARING= "2";
    public final static String  SEPARATE_SHARING="3";

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(getKey(),"ShareType","共享类型"));
        list.add(new Dictionary(getKey(),RESPECTIVELY_SHARING,"分别共享","ShareType"));
        list.add(new Dictionary(getKey(),UNIFIED_SHARING,"统一共享","ShareType"));
        list.add(new Dictionary(getKey(),SEPARATE_SHARING,"单独共享","ShareType"));
        return list;
    }

    public static String getKey(){
        return ShareType.class.getName();
    }
}
