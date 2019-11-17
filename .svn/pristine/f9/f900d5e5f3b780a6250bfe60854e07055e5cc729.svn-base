package com.kfwy.hkp.sys.version.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjp on 2018/8/7.
 */
@Component("clientAppType")
public class ClientType implements DictionaryProvider {

    public final static Integer Android = 1;
    public final static Integer IOS = 2;

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(ClientType.class.getName(),"ClientType","设备类型"));
        list.add(new Dictionary(ClientType.class.getName(),String.valueOf(Android),"渠道安卓设备","ClientType"));
        list.add(new Dictionary(ClientType.class.getName(),String.valueOf(IOS),"渠道Iphone设备","ClientType"));
        return list;
    }
}
