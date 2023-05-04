package com.kenny.testwas.learning.completablefuture;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@Slf4j
public class CompletableFutureController {

    private final ExternalApiClient externalApiClient = new ExternalApiClient();
    private final Executor executor;

    public CompletableFutureController(final Executor executor) {
        this.executor = executor;
    }

    // Guide To CompletableFuture( Baeldung ) : https://www.baeldung.com/java-completablefuture
    @GetMapping("/user/{id}")
    public CompletableFuture<User> getUser(@PathVariable("id") final String id) {
        log.warn("# getUser({}) START!!", id);

        final CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> proccessInternalTask(id));
        final CompletableFuture<User> future2 = CompletableFuture.supplyAsync(() -> externalApiClient.getUserInfo(id));

        final CompletableFuture<User> combineFuture = future1
                .thenCombineAsync(future2
                        , (f1, f2) -> f2
                        , executor
                );

        // 여기서도 특정작업이 4초가 걸린다고 가정한다.
        try {
            Thread.sleep(4000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        // thenCombine으로 리턴하고 있어서, 이 아래부분의 list 관련 로직은 의미없는 부분임
        // join() 메서드를 테스트하기위한 용도
        final List<?> list = Stream.of(future1, future2)
                .map(CompletableFuture::join)
                .toList()
        ;

        log.warn( "# list : {}", list);

        return combineFuture;
    }


    // 외부연동과 별개로, 내부로직으로 처리하는 로직이 있다고 가정한다.
    // 이 작업은 3초가 걸린다고 가정한다.
    private String proccessInternalTask( final String id ) {
        log.warn( "# proccessInternalTask START!!");

        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        log.warn( "# proccessInternalTask END!!");
        return id;
    }
}
