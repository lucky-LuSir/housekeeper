package com.kfwy.hkp.trade.wage.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzp on 2019/3/27.
 */
@Component
public class WageType implements DictionaryProvider {

    public final static String PAYBACK = "1";
    public final static String INVOICE = "2";


    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(WageType.class.getName(),"CommissionType","工资类型"));
        list.add(new Dictionary(WageType.class.getName(),PAYBACK,"回款工资","CommissionType"));
        list.add(new Dictionary(WageType.class.getName(),INVOICE,"开票扣款","CommissionType"));
        return list;
    }
}
