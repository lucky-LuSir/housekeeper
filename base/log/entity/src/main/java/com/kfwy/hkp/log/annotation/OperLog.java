package com.kfwy.hkp.log.annotation;

import java.lang.annotation.*;

/**
 * @author 李文思汗
 * @Descirption 日志记录,自定义注解
 * @Date 2019/1/22 11:46
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface OperLog {
    /**
     * 操作编码
     * @return
     */
    String optCode() default "";

    /**
     * 操作名称
     * @return
     */
    String opt() default "";

    /**
     * 操作类型
     * @return
     */
    String optType() default "";

    /**
     * 业务编码
     * @return
     */
    String bizCode() default "";

    /**
     * 请求url
     */
    String url()  default "";

    /**
     * 操作内容
     */
    String content() default "";

}
