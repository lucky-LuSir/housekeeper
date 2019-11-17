package com.kfwy.hkp.common.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kfwy_it_013
 * @version 1.0
 * @description TODO
 * @auth 李文思汗
 */
@Component("baseOperationType")
public class BaseOperationType implements DictionaryProvider {

    public final static String BASE_OPERATION_TYPE = "baseOperationType";
    public final static String CUSTOMER = "cus";
    public final static String HOUSE = "house";
    public final static String HOUSE_OWNER = "houseOwner";
    public final static String ACTIVATION ="activation";
    public final static String HISTORY="history";

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(getKey(), BASE_OPERATION_TYPE, "基础日志操作类型"));
        list.add(new Dictionary(getKey(), CUSTOMER, "客户模块", BASE_OPERATION_TYPE));
        list.add(new Dictionary(getKey(), HOUSE, "房源模块", BASE_OPERATION_TYPE));
        list.add(new Dictionary(getKey(), HOUSE_OWNER, "业主模块", BASE_OPERATION_TYPE));
        list.add(new Dictionary(getKey(), ACTIVATION, "激活模块", BASE_OPERATION_TYPE));
        list.add(new Dictionary(getKey(), HISTORY, "历史记录模块", BASE_OPERATION_TYPE));
        return list;
    }

    public static String getKey() {
        return BaseOperationType.class.getName();
    }
}
