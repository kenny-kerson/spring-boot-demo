package com.kenny.testwas.learning.completablefuture;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@Slf4j
public class CompletableFutureConfig {

    @Bean
    public Executor completableFutureExecutor() {
        log.warn("# Executor Bean 생성!!");

        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setQueueCapacity(100);
        executor.setMaxPoolSize(Integer.MAX_VALUE);
        executor.setKeepAliveSeconds(60);
        executor.setAllowCoreThreadTimeOut(false);
        executor.setThreadNamePrefix("thead-pool-cf-");
        executor.initialize();
        return executor;
    }
}
