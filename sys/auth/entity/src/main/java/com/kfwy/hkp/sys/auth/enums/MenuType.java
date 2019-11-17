package com.kfwy.hkp.sys.auth.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidcun on 2018/5/19.
 *
 */
@Component
public class MenuType implements DictionaryProvider {
    public final static String ROOT_MENU="0";
    public final static String MENU="1";
    public final static String FUNCTION="2";

    @Override
    public List<Dictionary> produce() {

        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(MenuType.class.getName(),"menuType","菜单类型"));
        list.add(new Dictionary(MenuType.class.getName(),ROOT_MENU,"根菜单","menuType"));
        list.add(new Dictionary(MenuType.class.getName(),MENU,"菜单","menuType"));
        list.add(new Dictionary(MenuType.class.getName(),FUNCTION,"功能","menuType"));
        return list;
    }
}
