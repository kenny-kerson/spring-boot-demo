package com.kenny.testwas.learning.completablefuture;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@Slf4j
public class CompletableFutureController {

    @GetMapping("/user/{id}")
    public CompletableFuture<User> getUser(@PathVariable("id") final String id) {
        log.warn("# getUser({}) START!!", id);

        return CompletableFuture.supplyAsync(() -> {
                log.warn( "# supplyAsync() START!!");

                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            log.warn( "# supplyAsync() RETURN!!");
                return new User("kenny", "고케니");
        });
    }
}
