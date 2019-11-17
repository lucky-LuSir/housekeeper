package com.kfwy.hkp.common.sms.sendNew;


import com.kfwy.hkp.common.sms.SmsEntity;

import java.util.Map;

/**
 * Created by moyi on 15-8-4.
 * 15-8-4
 */
@Deprecated
public interface IHXTSmsTemplate {

    <T> T codeSender(SmsEntity entity);

    Map<String, Object> send(SmsEntity entity);

}
