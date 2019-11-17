package com.kfwy.hkp.quit.dao.impl;

import com.gniuu.framework.dataaccess.redis.AbstractRedisDao;
import com.kfwy.hkp.quit.dao.QuitDelRedisDao;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class QuitDelRedisDaoImpl extends AbstractRedisDao<UserEntity> implements QuitDelRedisDao {


    @Override
    public void deleteEmp(String userCode) {
        //删除redis 的用户信息 根据用户编ma
        Set<String> authKeys = template.keys("ky:hkp:user:auth:*");

        for(String s: authKeys){
            String userCodes = template.opsForValue().get(s);
            if(userCodes.equals(userCode)){
                template.delete(s);
                break;
            }
        }
    }
}
