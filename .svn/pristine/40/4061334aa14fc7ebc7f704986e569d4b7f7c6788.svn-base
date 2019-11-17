package com.kfwy.hkp.common.sms.send.impl;

import com.cloopen.rest.sdk.CCPRestSDK;
import com.kfwy.hkp.common.sms.SmsEntity;
import com.kfwy.hkp.common.sms.send.ISmsTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by moyi on 15-8-4.
 * 15-8-4
 */
@Component
public class XiuXiuSmsTemplateImpl implements ISmsTemplate {

    private static Logger logger = LoggerFactory.getLogger(XiuXiuSmsTemplateImpl.class);

    private static final String AUTH_TOKEN = "fe32d8e023ef4064b8be8d9c0fde5980";

    private static final String ACCOUNT_SID = "aaf98f8952f7367a01530d53b02829ad";

    private static final String SMS_SERVER_URL = "app.cloopen.com";

    private static final String APPLICATION_ID = "aaf98f8952f7367a01530d58eaa029c1";

    //private static final String APPLICATION_TOKEN = "3ff0cd39c5c9a4b7421c8fdcc817b123";

    private static final String SMS_SERVER_PORT = "8883";

    private CCPRestSDK restAPI = new CCPRestSDK();

    @Autowired
    private TaskExecutor taskExecutor;

    public void init() {
        restAPI.init(SMS_SERVER_URL, SMS_SERVER_PORT);
        restAPI.setAccount(ACCOUNT_SID, AUTH_TOKEN);
        restAPI.setAppId(APPLICATION_ID);
    }

    @Override
    public Map<String, Object> send(SmsEntity entity) {

        return this.send(entity.getTo(), entity.getTemplate(), entity.getMessages());
    }

    @Override
    public Map<String, Object> send(String to, String templateId, String[] args) {

        Map<String, Object> map = restAPI.sendTemplateSMS(to, templateId, args);
        if (map.get("statusCode").equals("000000")) {
            return (Map<String, Object>) map.get("data");
        } else {
            logger.error("错误编码{},错误信息={}", map.get("statusCode"), map.get("statusMsg"));
            return null;
        }
    }

    @Override
    public void asyncSend(SmsEntity entity){

        this.asyncSend(entity.getTo(), entity.getTemplate(), entity.getMessages());
    }

    @Override
    public void asyncSend(String to, String templateId, String[] args){
        taskExecutor.execute(() -> {
            Map<String, Object> map = restAPI.sendTemplateSMS(to, templateId, args);
            if (map.get("statusCode").equals("000000")) {
            } else {
                logger.error("错误编码{},错误信息={}", map.get("statusCode"), map.get("statusMsg"));
            }
        });
    }


}
