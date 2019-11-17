package com.kfwy.hkp.common.sms;

import com.gniuu.framework.entity.BaseEntity;

import java.util.Arrays;

/**
 * Created by moyi on 15-8-4.
 * 15-8-4
 */
public class SmsEntity extends BaseEntity {

    //发送验证码模板
    public static final String smsVerTemplate = "68539";

    //登录动态密码模板
    public static final String smsLoginTemplate = "68474";

    //预约通知客户模板
    public static final String CusBespeakTemplate = "71188";

    //预约通知员工模板
    public static final String empBespeakTemplate = "71187";

    //委托提醒客户模板
    public static final String cusEntrustTemplate = "71564";

    //后台委托
    public static final String cusEntrustTemplate_back = "83512";

    //委托提醒业务员
    public static final String empEntrustTemplate = "71561";

    //创建客户信息短信（历史：87242,125113）
    public static final String cusCreateTemplate = "159668";

    //创建业主信息短信（历史：87242）
    public static final String ownerCreateTemplate = "87242";
    //录入客户带看需要给客户和业主发短信
    public static final String followupCustomerTemplate = "125115";
    public static final String followupOwnerTemplate = "125116";


    public static final String recommendUserTemplate = "149196";

    //推荐分配提醒业务员
    public static final String empRecommendTemplate = "149197";

    //推荐客户-发布合作客户
    public static final String crowdCustomerToEmp = "205033";
    //推荐房源-发布合作房源
    public static final String crowdHouseToEmp = "203904";
    //线索推荐-客户线索
    public static final String clueCustomerToEmp = "203905";
    //线索推荐-房源线索
    public static final String clueHouseToEmp = "203906";

    public static final String platCustomerBidToEmp = "203914";

    public static final String customerFollowupEia = "414798";



    /**
     * 发送手机号
     */
    private String to;

    /**
     * 消息占位
     */
    private String[] messages;
    /**
     * 消息占位
     */
    private String smsContent;

    /**
     * 短信模版ID
     */
    private String template;
    /**
     * 短信消息体
     */
    private String body;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String[] getMessages() {
        return messages;
    }

    public void setMessages(String[] messages) {
        this.messages = messages;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    @Override
    public String toString() {
        return "SmsEntity{" +
                "to='" + to + '\'' +
                ", messages=" + Arrays.toString(messages) +
                ", template='" + template + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }
}
