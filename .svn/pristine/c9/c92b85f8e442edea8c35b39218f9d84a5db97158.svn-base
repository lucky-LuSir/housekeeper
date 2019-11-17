package com.kfwy.hkp.common.utils;

import com.kfwy.hkp.common.annotion.DataSource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 多数据源切换的aop
 * @author liwensihan
 */
@Aspect
@Component
public class MultiSourceExAop implements Ordered {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	
	@Pointcut(value = "@annotation(com.kfwy.hkp.common.annotion.DataSource)")
	private void cut() {

	}
	
	@Around("cut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		
		Signature signature = point.getSignature();
        MethodSignature methodSignature = null;
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        methodSignature = (MethodSignature) signature;
        
        Object target = point.getTarget();
        Method currentMethod = target.getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
        
        DataSource datasource = currentMethod.getAnnotation(DataSource.class);
        if(datasource != null){
			DynamicDataSource.setDataSource(datasource.name());
			log.debug("设置数据源为：" + datasource.name());
        }else{
			DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getDefault());
			log.debug("设置数据源为：dataSourceCurrent");
        }
        
        try {
        	return point.proceed();
		} finally {
			log.debug("清空数据源信息！");
			DynamicDataSource.clearDataSource();
		}
	}
	
	
	/**
	 * aop的顺序要早于spring的事务
	 */
	@Override
	public int getOrder() {
		return 1;
	}

}
