package com.kenny.testwas.learning.asyncconfigurer;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@TestConfiguration

public class TestConfig {

    @Bean
    public Executor testExecutor() {
        System.out.println( "__KENNY__ testExecutor()");

        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(50);
        executor.setQueueCapacity(20);
        executor.setThreadNamePrefix("test-async-thread-");
        executor.initialize();
        return executor;
    }
}
