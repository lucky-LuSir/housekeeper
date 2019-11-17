package com.kfwy.hkp.trade.order.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjp on 2018/8/16.
 */
@Component
public class OrderPercentageType implements DictionaryProvider {

    //获客分成
    public final static String CUSTOMER = "1";
    //房源分成(房源开发)
    public final static String HOUSE = "2";
    //带看
    public final static String ASSIST_FOLLOW = "3";
    //协助成交分成
    public final static String ASSIST_DEAL = "4";
    //成交
    public final static String DEAL = "5";
    //客服分成
    public final static String CUSSERVICE = "6";
    //房源委托
    public final static String PROXY = "7";
    //个人获客补贴
    public final static String CUSPERSONFEN = "8";
    //客户推送
    public final static String CUSPUSHFEN = "9";
    //公司获客分成
    public final static String CPYDIVIDE = "10";
    //活动经费分成
    public final static String ACTDIVIDE = "11";


    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(OrderPercentageType.class.getName(),"OrderPercentageType","订单分成类型"));
        list.add(new Dictionary(OrderPercentageType.class.getName(),CUSTOMER,"客户分成","OrderPercentageType"));
        list.add(new Dictionary(OrderPercentageType.class.getName(),HOUSE,"房源分成","OrderPercentageType"));
        list.add(new Dictionary(OrderPercentageType.class.getName(),ASSIST_FOLLOW,"协助带看分成","OrderPercentageType"));
        list.add(new Dictionary(OrderPercentageType.class.getName(),ASSIST_DEAL,"协助成交分成","OrderPercentageType"));
        list.add(new Dictionary(OrderPercentageType.class.getName(),DEAL,"成交分成","OrderPercentageType"));
        list.add(new Dictionary(OrderPercentageType.class.getName(),CUSSERVICE,"客服分成","OrderPercentageType"));
        list.add(new Dictionary(OrderPercentageType.class.getName(),PROXY,"房源委托分成","OrderPercentageType"));

        list.add(new Dictionary(OrderPercentageType.class.getName(),CUSPERSONFEN,"个人获客补贴","OrderPercentageType"));
        list.add(new Dictionary(OrderPercentageType.class.getName(),CUSPUSHFEN,"客户推送","OrderPercentageType"));
        list.add(new Dictionary(OrderPercentageType.class.getName(),CPYDIVIDE,"公司获客分成","OrderPercentageType"));
        list.add(new Dictionary(OrderPercentageType.class.getName(),ACTDIVIDE,"活动经费分成","OrderPercentageType"));


        return list;
    }
}
