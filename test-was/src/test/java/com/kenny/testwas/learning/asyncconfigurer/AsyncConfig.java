package com.kenny.testwas.learning.asyncconfigurer;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@TestConfiguration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        System.out.println( "__KENNY__ getAsyncExecutor()");

        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(50);
        executor.setQueueCapacity(20);
        executor.setThreadNamePrefix("async-thread-");
        executor.initialize();
        return executor;
    }
}
