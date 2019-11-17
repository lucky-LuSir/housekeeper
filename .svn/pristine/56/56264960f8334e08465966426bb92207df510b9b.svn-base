package com.kfwy.hkp.sys.area.dictionary;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/6/30 15:48
 */
public class AreaStatus implements DictionaryProvider {


    public static final String ENABLE="1";
    public static final String DISABLE="0";

    @Override
    public List<Dictionary> produce() {

        List<Dictionary> dic = new ArrayList<Dictionary>();

        dic.add(new Dictionary(AreaStatus.class.getName(), AreaStatus.class.getSimpleName(),"区域状态"));
        dic.add(new Dictionary(AreaStatus.class.getName(),ENABLE,"禁用", AreaStatus.class.getSimpleName()));
        dic.add(new Dictionary(AreaStatus.class.getName(),DISABLE,"启用", AreaStatus.class.getSimpleName()));

        return dic;
    }
}
