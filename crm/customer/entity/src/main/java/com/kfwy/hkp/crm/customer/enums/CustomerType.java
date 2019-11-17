package com.kfwy.hkp.crm.customer.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zjp
 * @date 2018/8/7
 */
@Component
public class CustomerType implements DictionaryProvider {

    public final static Integer PLATFORM = 1;
    public final static Integer AGENT = 2;
    public final static Integer PRIVATE = 3;
    public final static Integer AGENT_AND_PRIVATE = 4;
    public final static Integer FOCUS_CUS = 5;
    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<>();
        list.add(new Dictionary(CustomerType.class.getName(),"CustomerType","客户类型"));
        list.add(new Dictionary(CustomerType.class.getName(),String.valueOf(PLATFORM),"平台","CustomerType"));
        list.add(new Dictionary(CustomerType.class.getName(),String.valueOf(AGENT),"经纪人","CustomerType"));
        list.add(new Dictionary(CustomerType.class.getName(),String.valueOf(PRIVATE),"平台拉私","CustomerType"));
        list.add(new Dictionary(CustomerType.class.getName(),String.valueOf(AGENT_AND_PRIVATE),"经理和大区经理可查看本部门个人类型和拉私类型的客户","CustomerType"));
        list.add(new Dictionary(CustomerType.class.getName(),String.valueOf(FOCUS_CUS),"集中获客","CustomerType"));
        return list;
    }
}
