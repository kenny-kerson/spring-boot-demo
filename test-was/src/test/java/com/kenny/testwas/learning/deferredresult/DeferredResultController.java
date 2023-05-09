package com.kenny.testwas.learning.deferredresult;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import javax.swing.*;
import java.util.concurrent.ForkJoinPool;

@RestController
@Slf4j
public class DeferredResultController {

    @GetMapping("/async-deferredresult")
    public DeferredResult<User> handleReqDeferredResult() {
        log.warn("# handleReqDeferredResult START!");

        final DeferredResult<User> response = new DeferredResult<>();

        ForkJoinPool.commonPool().submit(() -> {
            log.warn("# ForkJoinPool START!!");

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            response.setResult(new User("kenny", "고케니"));
        });

        response.onCompletion(() -> log.warn("# onCompletion called"));
        response.onTimeout(() -> {});
        response.onError((error) -> {});
        return response;
    }
}
