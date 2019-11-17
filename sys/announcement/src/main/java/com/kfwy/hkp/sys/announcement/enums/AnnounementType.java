package com.kfwy.hkp.sys.announcement.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzp on 2019/02/25.
 *
 */
@Component
public class AnnounementType implements DictionaryProvider {


    @Override
    public List<Dictionary> produce() {

        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(AnnounementType.class.getName(),"AnnounementType","榜单类型"));
        list.add(new Dictionary(AnnounementType.class.getName(),"1","业绩光荣榜","AnnounementType"));
        list.add(new Dictionary(AnnounementType.class.getName(),"2","业绩落后榜","AnnounementType"));
        list.add(new Dictionary(AnnounementType.class.getName(),"3","服务品质最好","AnnounementType"));
        list.add(new Dictionary(AnnounementType.class.getName(),"4","服务品质最差","AnnounementType"));
        list.add(new Dictionary(AnnounementType.class.getName(),"5","标准落地最好","AnnounementType"));
        list.add(new Dictionary(AnnounementType.class.getName(),"6","标准落地最差","AnnounementType"));
        return list;
    }
}
