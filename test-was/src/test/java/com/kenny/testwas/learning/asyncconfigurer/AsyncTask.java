package com.kenny.testwas.learning.asyncconfigurer;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncTask {

    @Async(value = "testExecutor")
    public void doAsync1() {
        System.out.println("__KENNY__ doAsync1() : " + Thread.currentThread() + " / " + Thread.currentThread().getId());
    }

    @Async
    public void doAsync2() {
        System.out.println("__KENNY__ doAsync2() : " + Thread.currentThread() + " / " + Thread.currentThread().getId());
    }
}
