package com.kenny.testwas.learning.completablefuture;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class CompletableFutureConfig {

    @Bean
    public Executor executor() {
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setQueueCapacity(100);
        executor.setMaxPoolSize(Integer.MAX_VALUE);
        executor.setKeepAliveSeconds(60);
        executor.setAllowCoreThreadTimeOut(false);
        executor.setThreadNamePrefix("cf-thead-pool-");
        executor.initialize();
        return executor;
    }
}
