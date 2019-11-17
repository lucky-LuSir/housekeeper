package com.kfwy.hkp.base;

import com.gniuu.framework.utils.JsonMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * @author baiye
 */
@Service
public class SystemConfig implements ApplicationContextAware {

    @Autowired
    private ISystemConfigManager systemConfigManager;

    private static SystemConfig systemConfig;

    private JsonMapper mapper;

    private SystemConfig() {
        mapper = JsonMapper.nonDefaultMapper();
    }

    public static final SystemConfig create() {
        return systemConfig;
    }

    public final String getString(String key) {
        SystemConfigEntity sce = this.systemConfigManager.getKey(key);
        if (sce == null || sce.getStatus() != SystemConfigStatus.YouXiao.getCode() || sce.getIsDeleted()) {
            return null;
        }
        return sce.getValue();
    }

    public final int getInt(String key) {
        String value = this.getString(key);
        if (StringUtils.isNotEmpty(value)) {
            return Integer.parseInt(value);
        }
        return 0;
    }

    public final String[] getArrayValue(String key) {
        String value = this.systemConfigManager.getValue(key);
        if (StringUtils.isNotEmpty(value)) {
            return value.split(";");
        }
        return null;
    }

    public final int getValueInt(String key) {
        String value = this.systemConfigManager.getValue(key);
        if (StringUtils.isNotEmpty(value)) {
            return Integer.parseInt(value);
        }
        return 0;
    }

    public final boolean getBoolean(String key) {
        String value = this.getString(key);
        if (StringUtils.isNotEmpty(value)) {
            return Boolean.parseBoolean(value);
        }
        return false;
    }

    public final String[] getArray(String key) {
        String value = this.getString(key);
        if (StringUtils.isNotEmpty(value)) {
            return value.split(";");
        }
        return null;
    }

    public final <T> T getObject(String key, Class<T> cls) {

        String value = this.getString(key);
        if (StringUtils.isNotEmpty(value)) {
            return mapper.fromJson(value, cls);
        }
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext app) throws BeansException {
        systemConfig = app.getBean(SystemConfig.class);
    }
}
