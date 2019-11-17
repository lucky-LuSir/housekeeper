package com.kfwy.hkp.common.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @description TODO
 * @auth 李文思汗
 */
@Component("operationType")
public class OperationType implements DictionaryProvider {

    public final static String OPERATION_TYPE = "operationType";
    public final static String CALL_CUS_PHONE = "callCusPhone";
    public final static String SEE_CUS_DETAIL = "seeCusDetail";
    public final static String SEE_HOS_DETAIL = "seeHosDetail";
    public final static String CALL_HOUSE_OWNER_PHONE="callHouseOwnerPhone";
    public final static String SEE_HOUSE_OWNER_DETAIL="seeHouseOwnerDetail";
    public final static String CALL_EMP_PHONE ="callEmpPhone";
    public final static String DELETE_CUS = "deleteCus";

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(getKey(), OPERATION_TYPE, "日志操作类型"));
        list.add(new Dictionary(getKey(), CALL_CUS_PHONE, "客户电话拨打", OPERATION_TYPE));
        list.add(new Dictionary(getKey(), CALL_HOUSE_OWNER_PHONE, "房源业主电话拨打", OPERATION_TYPE));
        list.add(new Dictionary(getKey(), SEE_CUS_DETAIL, "查看客户详情信息", OPERATION_TYPE));
        list.add(new Dictionary(getKey(), SEE_HOS_DETAIL, "查看房源详情信息", OPERATION_TYPE));
        list.add(new Dictionary(getKey(), SEE_HOUSE_OWNER_DETAIL, "查看房源业主详情信息", OPERATION_TYPE));
        list.add(new Dictionary(getKey(), CALL_EMP_PHONE, "拨打员工记录", OPERATION_TYPE));
        list.add (new Dictionary (getKey (),DELETE_CUS,"删除客户记录",OPERATION_TYPE));
        return list;
    }

    public static String getKey() {
        return OperationType.class.getName();
    }
}
