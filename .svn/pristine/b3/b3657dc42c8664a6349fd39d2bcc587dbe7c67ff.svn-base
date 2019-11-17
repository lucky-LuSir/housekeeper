package com.kfwy.hkp.common.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjh on 2018/11/08.
 */
@Component
public class DivideType implements DictionaryProvider {

    public final static String Ratio = "ratio";
    public final static String Cash = "cash";

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(getKey(),"DivideType","兼职分成类型"));
        list.add(new Dictionary(getKey(),Ratio,"比例分成","DivideType"));
        list.add(new Dictionary(getKey(),Cash,"现金分成","DivideType"));
        return list;
    }

    public static String getKey(){
        return DivideType.class.getName();
    }
}
