package com.kfwy.hkp.base.impl;

import com.gniuu.framework.dataaccess.redis.AbstractRedisDao;
import com.kfwy.hkp.base.ISystemConfigRedisDao;
import com.kfwy.hkp.base.SystemConfigEntity;
import com.kfwy.hkp.base.SystemConfigRedisHelper;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Map;
import java.util.Set;

@Repository
public class SystemConfigRedisDaoImpl extends AbstractRedisDao<SystemConfigEntity> implements ISystemConfigRedisDao {

    @Override
    public void addConfig(SystemConfigEntity sce) {
        if (sce != null && StringUtils.isNotEmpty(sce.getKey())) {
            BoundHashOperations<String, String, String> ops =
                    template.boundHashOps(SystemConfigRedisHelper.getConfigKey(sce.getKey()));
            Map beanMap = new BeanMap(sce);
            Set<String> keys = beanMap.keySet();
            for (Map.Entry entry : (Set<Map.Entry>) beanMap.entrySet()) {
                Object value = entry.getValue();
                if (value == null || "class".equals(entry.getKey())) {
                    continue;
                }

                if (value instanceof Date) {
                    Date date = (Date) value;
                    ops.put(entry.getKey().toString(), date.getTime() + "");
                } else {
                    ops.put(entry.getKey().toString(), value.toString());
                }
            }
        }
    }

    @Override
    public SystemConfigEntity getConfig(String key) {
        SystemConfigEntity sce = null;
        if (StringUtils.isNotEmpty(key)) {
            BoundHashOperations<String, String, String> ops =
                    template.boundHashOps(SystemConfigRedisHelper.getConfigKey(key));
            if (!ops.entries().isEmpty()){
              sce =   mapper.fromHash(ops.entries());
            }else {
                sce = null;
            }
        }
        return sce;
    }

    @Override
    public String getConfigValue(String key) {
        BoundHashOperations<String, String, String> ops =
                template.boundHashOps(SystemConfigRedisHelper.getConfigKey(key));
        String value = ops.get("value");
        return value;
    }

    @Override
    public void deleted(String key) {
        if (StringUtils.isNotEmpty(key)) {
            template.delete(SystemConfigRedisHelper.getConfigKey(key));
        }
    }
}
