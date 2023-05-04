package com.kenny.testwas.learning.completablefuture;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@Slf4j
public class CompletableFutureController {

    private final ExternalApiClient externalApiClient = new ExternalApiClient();
    @Qualifier("completableFutureExecutor")
    @Autowired
    private Executor executor;

    // Guide To CompletableFuture( Baeldung ) : https://www.baeldung.com/java-completablefuture
    @GetMapping("/user/{id}/{time}/{status}")
    public CompletableFuture<User> getUser( @PathVariable("id") final String id
            , @PathVariable("time") final String time
            , @PathVariable("status") final String status
    ) {
        log.warn("# getUser({}) START!!", id);

        final CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> proccessInternalTask(id));
        final CompletableFuture<User> future2 = CompletableFuture.supplyAsync(() -> externalApiClient.getUserInfo(id, time, status));

        // TODO : 쓰레드풀이 별도로 지정한 쓰레드풀로 동작하는지 확인필요
        // TODO : 예외처리를 어떻게 할 수 있는지 확인필요
        // TODO : 타임아웃처리 어떻게 할 수 있는지 확인필요
        // 예외
        //      completeExceptionally() : 완료되지 않은 상태에서, get() 및 관련메서드 호출시, 지정된 예외 발생
        //      exceptionally() : 예외발생하면, 전달한 콜백을 실행시킴
        // 타임아웃
        //      orTimeout() : 주어진 시간안에 완료되지 않으면, TimeoutException을 쓰로우함.
        //      complelteOnTimeout() : 주어진 시간안에 완료되지 않으면, 주어진 값으로 CF를 완료함.
        final CompletableFuture<User> combineFuture = future1
                .thenCombineAsync(future2
                        , (f1, f2) -> f2
                        , executor
                )
                .orTimeout(5000L, TimeUnit.MILLISECONDS)
                .completeOnTimeout(new User("timeout", "timeout"), 10000L, TimeUnit.MILLISECONDS)
                .exceptionally(e -> new User("error", "error"))
//                .completeExceptionally( new RuntimeException("CF가 완료되기전에 호출했음!!!"))
        ;

        // 여기서도 특정작업이 2초가 걸린다고 가정한다.
        try {
            Thread.sleep(2000L);
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
