package com.kfwy.hkp.crm.customer.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class CustomerApplyHidOrPriStatus implements DictionaryProvider {
    //（1.待审核2.通过3.不通过）
    public final static Integer PENDING = 1;
    public final static Integer PASS = 2;
    public final static Integer NOTPASS = 3;
    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<>();
        list.add(new Dictionary(CustomerApplyHidOrPriStatus.class.getName(),"CustomerApplyHidOrPriStatus","客户申请隐藏或拉私状态"));
        list.add(new Dictionary(CustomerApplyHidOrPriStatus.class.getName(),String.valueOf(PENDING),"待审核","CustomerApplyHidOrPriStatus"));
        list.add(new Dictionary(CustomerApplyHidOrPriStatus.class.getName(),String.valueOf(PASS),"通过","CustomerApplyHidOrPriStatus"));
        list.add(new Dictionary(CustomerApplyHidOrPriStatus.class.getName(),String.valueOf(NOTPASS),"不通过","CustomerApplyHidOrPriStatus"));
        return list;
    }
}
