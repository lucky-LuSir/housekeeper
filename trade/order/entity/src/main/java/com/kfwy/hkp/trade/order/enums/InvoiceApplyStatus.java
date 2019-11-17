package com.kfwy.hkp.trade.order.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: HJH
 * @Date: 2019/7/26
 * @Description: TODO
 */
@Component
public class InvoiceApplyStatus implements DictionaryProvider {

    public static final String Handle = "handle";
    public static final String Success = "success";

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(InvoiceApplyStatus.class.getName(), "InvoiceApplyStatus", "订单状态"));
        list.add(new Dictionary(InvoiceApplyStatus.class.getName(), Handle, "处理中", "InvoiceApplyStatus"));
        list.add(new Dictionary(InvoiceApplyStatus.class.getName(), Success, "已开票", "InvoiceApplyStatus"));
        return list;
    }
}
