package com.kfwy.hkp.crm.customer.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class CustomerApplyHidOrPriType implements DictionaryProvider {
    //（1.隐藏2.拉私）
    public final static Integer HIDDEN= 1;
    public final static Integer PRIVATE = 2;
    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<>();
        list.add(new Dictionary(CustomerApplyHidOrPriType.class.getName(),"CustomerApplyHidOrPriType","客户申请隐藏或拉私类型"));
        list.add(new Dictionary(CustomerApplyHidOrPriType.class.getName(),String.valueOf(HIDDEN),"隐藏申请","CustomerApplyHidOrPriType"));
        list.add(new Dictionary(CustomerApplyHidOrPriType.class.getName(),String.valueOf(PRIVATE),"拉私申请","CustomerApplyHidOrPriType"));
         return list;
    }
}
