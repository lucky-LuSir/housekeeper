package com.kfwy.hkp.common.sms.send;


import com.kfwy.hkp.common.sms.SmsEntity;

import java.util.Map;

/**
 * Created by moyi on 15-8-4.
 * 15-8-4
 */
public interface ISmsTemplate {

    <T> T send(SmsEntity entity);


    public Map<String, Object> send(String to, String templateId, String[] args);

    /**
     * 异步发送
     * @param entity
     */
    void asyncSend(SmsEntity entity);


    public void asyncSend(String to, String templateId, String[] args);
}
