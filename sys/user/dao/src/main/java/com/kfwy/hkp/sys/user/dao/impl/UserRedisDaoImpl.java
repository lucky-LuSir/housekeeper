package com.kfwy.hkp.sys.user.dao.impl;

import com.gniuu.framework.user.dao.impl.AbstractBaseUserRedisDao;
import com.kfwy.hkp.sys.user.dao.IUserRedisDao;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Calendar;

/**
 * Created by davidcun on 2018/5/16.
 */
@Repository
public class UserRedisDaoImpl extends AbstractBaseUserRedisDao<UserEntity> implements IUserRedisDao<UserEntity> {


    public static String userCodeKey = "ky:hkp:user:code:";
    public static String userAuthKey = "ky:hkp:user:auth:";
    public static String userData = "ky:hkp:user:data";

    @Override
    public String createUserCodeKey(String userCode){
        return userCodeKey+userCode;
    }

    @Override
    public String createAuthKey(String authKey){
        return userAuthKey+authKey;
    }

    @Override
    public void setUserInfo(String userCode, UserEntity user) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);

        super.setUserInfo(userCode, user);

        this.setTimeout(createUserCodeKey(userCode),calendar.getTime());
    }

    @Override
    public void setLoginUserCode(String authKey, String userCode) {

        super.setLoginUserCode(authKey, userCode);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        setTimeout(createAuthKey(authKey),calendar.getTime());
    }


}
