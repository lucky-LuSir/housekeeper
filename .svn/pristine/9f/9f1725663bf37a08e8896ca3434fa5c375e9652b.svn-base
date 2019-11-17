package com.kfwy.hkp.crm.customer.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjp on 2018/8/7.
 */
@Component
public class LayerNum implements DictionaryProvider {

    public final static Integer BUTTOM = 1;
    public final static Integer FLOORUP = 2;
    public final static Integer ALL = 3;

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(LayerNum.class.getName(),"LayerNum","楼层类型"));
        list.add(new Dictionary(LayerNum.class.getName(),String.valueOf(BUTTOM),"只要底楼","LayerNum"));
        list.add(new Dictionary(LayerNum.class.getName(),String.valueOf(FLOORUP),"只要楼上","LayerNum"));
        list.add(new Dictionary(LayerNum.class.getName(),String.valueOf(ALL),"楼上楼下都行","LayerNum"));
        return list;
    }
}
