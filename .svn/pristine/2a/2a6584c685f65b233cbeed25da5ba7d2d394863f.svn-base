package com.kfwy.hkp.common.utils;

import com.gniuu.framework.logging.Logger;
import com.gniuu.framework.logging.LoggerFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/11/20 14:37
 */
public class ApiConfigUtils {
    private static Logger logger = LoggerFactory.getLogger(ApiConfigUtils.class);

    private static Properties properties;

    static {
        try {
            properties = PropertiesLoaderUtils.loadAllProperties("api.properties");
        } catch (IOException e) {
            logger.error("ConfigUtils load gniuu.properties error",e);
        }
    }

    public static String getMessage(String code){
        return properties.getProperty(code, code);
    }

    public static String getMessage(String code,String... args){
        return MessageFormat.format(properties.getProperty(code, code),args);
    }
}
