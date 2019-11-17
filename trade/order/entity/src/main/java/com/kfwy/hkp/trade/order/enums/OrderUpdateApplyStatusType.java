package com.kfwy.hkp.trade.order.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单修改申请 状态
 */
@Component
public class OrderUpdateApplyStatusType  implements DictionaryProvider {


    public final static String MANAGERAUDIT = "1";
    public final static String FINANCEAUDIT = "2";
    public final static String AGREE = "3";
    public final static String DISAGREE = "4";
    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(OrderUpdateApplyStatusType.class.getName(),"OrderUpdateApplyStatusType","订单修改申请状态"));
        list.add(new Dictionary(OrderUpdateApplyStatusType.class.getName(),MANAGERAUDIT,"大区总审核中","OrderUpdateApplyStatusType"));
        list.add(new Dictionary(OrderUpdateApplyStatusType.class.getName(),FINANCEAUDIT,"财务审核中","OrderUpdateApplyStatusType"));
        list.add(new Dictionary(OrderUpdateApplyStatusType.class.getName(),AGREE,"审核通过","OrderUpdateApplyStatusType"));
        list.add(new Dictionary(OrderUpdateApplyStatusType.class.getName(),DISAGREE,"审核驳回","OrderUpdateApplyStatusType"));
        return list;
    }
}
