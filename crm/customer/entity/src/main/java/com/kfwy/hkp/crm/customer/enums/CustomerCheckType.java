package com.kfwy.hkp.crm.customer.enums;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 客户模块操作权限类型字典
 */
@Component
public class CustomerCheckType implements DictionaryProvider {

    /**
     * 客户收藏
     */
    public final static String CHECK_CREATE_FAVORITE = "checkCreateFavorite";
    /**
     * 客户分享
     */
    public final static String CHECK_CREATE_SHARE = "checkCreateShare";
    /**
     * 客户信息修改
     */
    public final static String CHECK_CUSTOMER_INFO_CAN_UPDATE = "checkCustomerInfoCanUpdate";
    /**
     * 客户跟进查看
     */
    public final static String CHECK_SEE_CUS_FOLLOWUP = "checkSeeCusFollowup";
    /**
     * 创建客户跟进
     */
    public final static String CHECK_CREATE_FOLLOWUP = "checkCreateFollowup";
    /**
     * 下架客户
     */
    public final static String CHECK_DOWN_CUS = "checkDownCus";
    /**
     * 隐藏客户
     */
    public final static String CHECK_OFF_OPEN_FLAG = "checkOffOpenFlag";
    /**
     * 公开客户
     */
    public final static String CHECK_ON_OPEN_FLAG = "checkOnOpenFlag";
    /**
     * 上架客户
     */
    public final static String CHECK_UP_SHELF_CUSTOMER = "checkUpShelfCustomer";
    /**
     * 创建客户推送
     */
    public final static String CHECK_CREATE_CUS_PUSH = "checkCreateCusPush";
    /**
     * 创建选址报告
     */
    public final static String CHECK_CREATE_SELECT_ADDRESS_REPORT = "checkCreateSelectAddressReport";
    /**
     * 智能匹配
     */
    public final static String CHECK_MATCH_HOS = "checkMatchHos";
    /**
     * 创建合作
     */
    public final static String CHECK_CREATE_COOPERATE = "checkCreateCooperate";
    /**
     * 客户看房明细
     */
    public final static String CHECK_CUS_SAW_HOUSES = "checkCusSawHouses";
    /**
     * 申请平台客户电话查看
     */
    public final static String CHECK_APPLY_SEE_CUS_PHONE = "checkApplySeeCusPhone";
    /**
     * 客户拉私
     */
    public final static String CHECK_CREATE_PULL_PRIVATE  = "checkCreatePullPrivate";
    /**
     * 客户详情查看电话号码
     */
    public final static String CHECK_CUSTOMER_PHONE_CAN_SEE = "checkCustomerPhoneCanSee";
    /**
     * 检查客户拨打电话人数
     */
    public final static String CHECK_CUSTOMER_CALL_PHONE_PEOPLE_COUNT = "checkCustomerCallPhonePeopleCount";
    /**
     * 检查拨打客户时间间隔
     */
    public final static String CHECK_CUSTOMER_CALL_PHONE_TIME_INTERVAL = "checkCustomerCallPhoneTimeInterval";
    /**
     * 检查客户释放权限
     */
    public final static String CHECK_CUSTOMER_RELEASE = "checkCustomerRelease";
    /**
     * 检查客户隐藏权限
     */
    public final static String CHECK_CUSTOMER_OFF_OPEN_FLAG = "checkCustomerOffOpenFlag";
    /**
     * 检查是否展示集中获客客户列表
     */
    public final static String CHECK_FOCUS_CUS_NOTICE = "checkFocusCusNotice";

    /**
     *判断拨打电话次数是否达到上限 未达到则记录
     */
    public final static String CHECK_CUS_CALL_PHONE_NUM = "checkCusCallPhoneNum";
    /**
     * 检查创建客户
     */
    public final static String CHECK_CREATE_CUS = "checkCreateCus";
    /**
     * 检查客户电话拨打规则
     */
    public final static String CHECK_CUS_CALL_PHONE_RULE = "checkCusCallPhoneRule";

    @Override
    public List<Dictionary> produce() {
        List<Dictionary> list = new ArrayList<Dictionary>();
        list.add(new Dictionary(CustomerCheckType.class.getName(),
                "CustomerCheckType","客户操作权限检查类型"));

        list.add(new Dictionary(CustomerCheckType.class.getName(),
                CHECK_CREATE_FAVORITE,"检查创建客户收藏操作","CustomerCheckType"));

        list.add(new Dictionary(CustomerCheckType.class.getName(),
                CHECK_CREATE_SHARE,"检查创建分享操作","CustomerCheckType"));

        list.add(new Dictionary(CustomerCheckType.class.getName(),
                CHECK_CUSTOMER_INFO_CAN_UPDATE,"检查客户信息修改操作","CustomerCheckType"));

        list.add(new Dictionary(CustomerCheckType.class.getName(),
                CHECK_SEE_CUS_FOLLOWUP,"检查客户跟进记录查询操作","CustomerCheckType"));

        list.add(new Dictionary(CustomerCheckType.class.getName(),
                CHECK_CREATE_FOLLOWUP,"检查创建客户跟进操作","CustomerCheckType"));

        list.add(new Dictionary(CustomerCheckType.class.getName(),
                CHECK_OFF_OPEN_FLAG,"检查客户隐藏操作","CustomerCheckType"));

        list.add(new Dictionary(CustomerCheckType.class.getName(),
                CHECK_ON_OPEN_FLAG,"检查客户公开操作","CustomerCheckType"));

        list.add(new Dictionary(CustomerCheckType.class.getName(),
                CHECK_UP_SHELF_CUSTOMER,"检查上架客户操作","CustomerCheckType"));

        list.add(new Dictionary(CustomerCheckType.class.getName(),
                CHECK_DOWN_CUS,"检查下架客户操作","CustomerCheckType"));

        list.add(new Dictionary(CustomerCheckType.class.getName(),
                CHECK_CREATE_CUS_PUSH,"检查创建客户推送操作","CustomerCheckType"));

        list.add(new Dictionary(CustomerCheckType.class.getName(),
                CHECK_CREATE_SELECT_ADDRESS_REPORT,"检查创建客户选址报告操作","CustomerCheckType"));

        list.add(new Dictionary(CustomerCheckType.class.getName(),
                CHECK_MATCH_HOS,"检查创建客户智能匹配房源操作","CustomerCheckType"));

        list.add(new Dictionary(CustomerCheckType.class.getName(),
                CHECK_CREATE_COOPERATE,"检查创建客户合作操作","CustomerCheckType"));

        list.add(new Dictionary(CustomerCheckType.class.getName(),
                CHECK_CUS_SAW_HOUSES,"检查客户看房明细操作","CustomerCheckType"));

        list.add(new Dictionary(CustomerCheckType.class.getName(),
                CHECK_APPLY_SEE_CUS_PHONE,"检查申请平台客户电话查看","CustomerCheckType"));

        list.add(new Dictionary(CustomerCheckType.class.getName(),
                CHECK_CREATE_PULL_PRIVATE,"检查客户拉私操作","CustomerCheckType"));

        list.add(new Dictionary(CustomerCheckType.class.getName(),
                CHECK_CUSTOMER_PHONE_CAN_SEE,"客户详情查看电话号码","CustomerCheckType"));

        list.add(new Dictionary(CustomerCheckType.class.getName(),
                CHECK_CUSTOMER_RELEASE,"客户释放","CustomerCheckType"));
        list.add(new Dictionary(CustomerCheckType.class.getName(),
                CHECK_CUSTOMER_OFF_OPEN_FLAG,"客户隐藏限制","CustomerCheckType"));
        list.add(new Dictionary(CustomerCheckType.class.getName(),CHECK_FOCUS_CUS_NOTICE,"集中获客处理结果强提醒","CustomerCheckType"));
        list.add(new Dictionary(CustomerCheckType.class.getName(),
                CHECK_CUS_CALL_PHONE_NUM,"客户拨打电话次数限制","checkCusCallPhoneNum"));
        list.add(new Dictionary(CustomerCheckType.class.getName(),CHECK_CREATE_CUS,"客户新增","CustomerCheckType"));
        list.add (new Dictionary (CustomerCheckType.class.getName (),CHECK_CUSTOMER_CALL_PHONE_PEOPLE_COUNT,"检查客户电话被多少人拨打","CustomerCheckType"));
        list.add (new Dictionary (CustomerCheckType.class.getName (),CHECK_CUSTOMER_CALL_PHONE_TIME_INTERVAL,"检查客户电话拨打时间间隔","CustomerCheckType"));
        return list;
    }
}
