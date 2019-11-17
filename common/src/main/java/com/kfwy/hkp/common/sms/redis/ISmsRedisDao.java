package com.kfwy.hkp.common.sms.redis;

import java.util.Map;

/**
 * Created by moyi on 15-8-4.
 * 15-8-4
 */
public interface ISmsRedisDao {

    void setSmsCode(String phoneNumber, String code);

    boolean clearSmsCode(String phoneNumber);

    Map<String,String> getSmsCode(String phoneNumber);

    int getLimit(String phoneNumber);

    void setUnbindCode(String phoneNumber,String code);

    Map<String,String> getUnbindCode(String phoneNumber);
}
