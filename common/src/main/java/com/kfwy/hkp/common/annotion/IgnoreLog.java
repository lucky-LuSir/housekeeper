package com.kfwy.hkp.common.annotion;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface IgnoreLog {

}
