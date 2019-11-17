package com.kfwy.hkp.common.executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2019/1/19 16:37
 */
@Configuration
public class ExecutorConfig {

    @Bean
    public TaskExecutor taskExecutor(){
        int core = Runtime.getRuntime().availableProcessors();
        ThreadPoolTaskExecutor
                executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("hkp-executor");
        executor.setCorePoolSize(core);
        executor.setMaxPoolSize(core * 2 + 1);
        executor.setKeepAliveSeconds(5);
        executor.setQueueCapacity(50);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

    @Bean
    public ScheduledExecutorService scheduledExecutorService(){
        ScheduledExecutorService mScheduledExecutorService = new ScheduledThreadPoolExecutor(16);
        return mScheduledExecutorService;
    }
}
