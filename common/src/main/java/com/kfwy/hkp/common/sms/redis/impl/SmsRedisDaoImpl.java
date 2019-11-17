package com.kfwy.hkp.common.sms.redis.impl;

import com.gniuu.framework.dataaccess.redis.AbstractRedisDao;
import com.gniuu.framework.utils.JsonMapper;
import com.kfwy.hkp.common.sms.SmsEntity;
import com.kfwy.hkp.common.sms.redis.ISmsRedisDao;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author moyi
 * @date 15-8-4
 * 15-8-4
 */
@Service
public class SmsRedisDaoImpl extends AbstractRedisDao<SmsEntity> implements ISmsRedisDao {

    private static final String REDIS_PATH = "hkp:user:verCode:";
    private static final String REDIS_UNBIND_VERIFY_CODE_PATH="hkp:user:unBindVerCode:";
    JsonMapper jsonMapper = JsonMapper.nonDefaultMapper();
    /**
     *
     * @param phoneNumber 电话号
     * @param verCode 验证码
     */
    @Override
    public void setSmsCode(String phoneNumber, String verCode) {
        String key = REDIS_PATH+phoneNumber;
        Map<String,String> map = new HashMap<String,String>();
        map.put("verCode",verCode);
        map.put("lastTime", System.currentTimeMillis()+"");
        String value = jsonMapper.toJson(map);
        this.valueOps.set(REDIS_PATH+phoneNumber,value,20L, TimeUnit.MINUTES);
    }

    @Override
    public boolean clearSmsCode(String phoneNumber) {
        return false;
    }

    @Override
    public Map<String,String> getSmsCode(String phoneNumber) {
        String value = (String)this.valueOps.get(REDIS_PATH+phoneNumber);
        Map<String,String> values = jsonMapper.fromJson(value,Map.class);
        return values;
    }

    @Override
    public int getLimit(String phoneNumber) {
        return 0;
    }

    @Override
    public void setUnbindCode(String phoneNumber, String code) {
        String unbindKey = REDIS_UNBIND_VERIFY_CODE_PATH+phoneNumber;
        Map<String,String> map = new HashMap<String,String>();
        map.put("unBindVerifyCode",code);
        map.put("lastTime", System.currentTimeMillis()+"");
        String value = jsonMapper.toJson(map);
        this.valueOps.set(unbindKey,value,20L, TimeUnit.MINUTES);
    }

    @Override
    public Map<String,String> getUnbindCode(String phoneNumber) {
        String value = (String)this.valueOps.get(REDIS_UNBIND_VERIFY_CODE_PATH+phoneNumber);
        Map<String,String> values = jsonMapper.fromJson(value,Map.class);
        return values;
    }
}
