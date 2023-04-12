package com.kenny.testwas.learning.asyncconfigurer.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncTaskSpringBean {

    @Async
    public void doAsyncTaskForAsyncConfigurer(final Thread thread) {
        System.out.println("__KENNY__ AsyncTaskSpringBean doAsyncTaskForAsyncConfigurer() Thread Info : " + Thread.currentThread());

        if( thread.getId() == Thread.currentThread().getId()) {
            throw new IllegalCallerException();
        }

        if( thread.getName().equals(Thread.currentThread().getName()) ) {
            throw new IllegalCallerException();
        }
    }

    @Async("async-thread-Configuration")
    public void doAsyncTaskForConfiguration(final Thread thread) {
        System.out.println("__KENNY__ AsyncTaskSpringBean doAsyncTaskForConfiguration() Thread Info : " + Thread.currentThread());

        if( thread.getId() == Thread.currentThread().getId()) {
            throw new IllegalCallerException();
        }

        if( thread.getName().equals(Thread.currentThread().getName()) ) {
            throw new IllegalCallerException();
        }
    }
}
