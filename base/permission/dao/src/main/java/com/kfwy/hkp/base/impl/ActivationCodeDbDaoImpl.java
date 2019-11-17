package com.kfwy.hkp.base.impl;


import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.redis.AbstractRedisDao;
import com.gniuu.framework.utils.JsonMapper;
import com.kfwy.hkp.base.ActivationCodeDbDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author liwensihan
 * @description TODO
 * @date 2019/3/13 16:11
 */
@Repository
public class ActivationCodeDbDaoImpl extends AbstractRedisDao implements ActivationCodeDbDao {
    private static final String CODE_PATH = "hkp:user:userCode:";
    private static final String INFO_PATH = "hkp:user:activationCode:";
    JsonMapper jsonMapper = JsonMapper.nonDefaultMapper();

    @Override
    public void setActvationCode(String activationCode) {
        String codeKey = CODE_PATH +CurrentContext.getUserCode();
        this.valueOps.set(codeKey,activationCode,5L,TimeUnit.HOURS);

        String infoKey = INFO_PATH +activationCode;
        Map<String,String> map = new HashMap<String,String>();
        map.put("activationCode",activationCode);
        map.put("userCode", CurrentContext.getUserCode());
        map.put("userName",CurrentContext.getUserInfo().getUserName());
        map.put("ownerDeptCode",CurrentContext.getUserInfo().getOwnerDeptCode());
        map.put("ownerDeptName",CurrentContext.getUserInfo().getOwnerDeptName());
        map.put("createTime", System.currentTimeMillis()+"");
        String value = jsonMapper.toJson(map);
        this.valueOps.set(infoKey,value,5L, TimeUnit.HOURS);
    }

    @Override
    public String getActivationCodeByUserCode(String userCode) {
        String value = (String)this.valueOps.get(CODE_PATH+userCode);
        String activationCode = jsonMapper.fromJson(value,String.class);
        return activationCode;
    }

    @Override
    public Map<String, String> getActivationInfoByActivationCode(String activationCode) {
        String value = (String)this.valueOps.get(INFO_PATH +activationCode);
        Map<String,String> values = jsonMapper.fromJson(value,Map.class);
        return values;
    }

    @Override
    public void cleanActivationCode(Map<String,String> map) {
        String userCode = map.get("userCode");
        this.template.delete(CODE_PATH+userCode);
        String activationCode = map.get("activationCode");
        this.template.delete(INFO_PATH+activationCode);
    }

}
