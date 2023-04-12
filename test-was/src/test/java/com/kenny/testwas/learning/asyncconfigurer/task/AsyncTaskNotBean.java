package com.kenny.testwas.learning.asyncconfigurer.task;

import org.springframework.scheduling.annotation.Async;

public class AsyncTaskNotBean {

    @Async
    public void doAsyncTask(final Thread callerThread) {
        System.out.println("__KENNY__ AsyncTaskNotBean doAsyncTask() Thread Info : " + Thread.currentThread());

        if( callerThread.getId() != Thread.currentThread().getId()) {
            throw new IllegalCallerException();
        }
    }
}
