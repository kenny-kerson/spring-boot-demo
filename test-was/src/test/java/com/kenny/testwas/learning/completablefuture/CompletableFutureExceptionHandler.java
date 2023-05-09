package com.kenny.testwas.learning.completablefuture;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;

@RestControllerAdvice
@Slf4j
public class CompletableFutureExceptionHandler {

    @ExceptionHandler({TimeoutException.class})
    public User timeoutException(Exception e, WebRequest webRequest) {
        log.error( "# timeoutException thrown : ", e);
        return new User("timeoutException", "timeoutException");
    }

    @ExceptionHandler({RuntimeException.class})
    public User completeExceptionally(Exception e, WebRequest webRequest) {
        log.error("# completeExceptionally thrown : ", e);
        return new User("completeExceptionally", "completeExceptionally");
    }
}
