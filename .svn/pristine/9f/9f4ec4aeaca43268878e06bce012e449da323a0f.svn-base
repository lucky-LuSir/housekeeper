package com.kfwy.hkp.common.utils;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * The type Spring config tool.
 *
 * @AUTHOR 李文思汗
 * @Description 类描述 :
 */
@Component
public class SpringConfigTool   implements ApplicationContextAware {


    private static ApplicationContext context = null;
    private static SpringConfigTool stools = null;

    /**
     * Init spring config tool.
     *
     * @return the spring config tool
     */
    public synchronized static SpringConfigTool init() {
        if (stools == null) {
            stools = new SpringConfigTool();
        }
        return stools;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        context = applicationContext;
    }

    /**
     * Gets bean.
     *
     * @param beanName the bean name
     * @return the bean
     */
    public synchronized static Object getBean(String beanName) {
        return context.getBean(beanName);

    }

    public synchronized static T getBean(String beanName,T t){
        return (T)context.getBean(beanName);
    }

    public synchronized static <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }

}
