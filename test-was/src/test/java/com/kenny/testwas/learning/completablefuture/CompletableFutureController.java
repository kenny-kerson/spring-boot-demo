package com.kenny.testwas.learning.completablefuture;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@RestController
@Slf4j
public class CompletableFutureController {

    private final ExternalApiClient externalApiClient = new ExternalApiClient();

    @Qualifier("completableFutureExecutor")
    @Autowired
    private Executor executor;

    // CompletableFuture를 직접 리턴하여, Spring Async Rest Controller 매커니즘을 사용하는것이 아니라,
    // 어플리케이션에서 직접 비동기처리를 하고, 결과값을 리턴하는, Join() 메서드 테스트
    @GetMapping("/user/{id}/{time}/{status}/join")
    public List<?> getUserWithJoin( @PathVariable("id") final String id
            , @PathVariable("time") final String time
            , @PathVariable("status") final String status
    ) {
        log.warn("# getUserWithJoin({}) START!!", id);

        final CompletableFuture<String> future1 = CompletableFuture.supplyAsync(
                () -> proccessInternalTask(id),
                executor
        );
        final CompletableFuture<User> future2 = CompletableFuture.supplyAsync(
                () -> externalApiClient.getUserInfo(id, time, status),
                executor
        );

        final List<?> list = Stream.of(future1, future2)
                .map(CompletableFuture::join)
                .toList()
        ;

        log.warn( "# list : {}", list);

        return list;
    }

    // Spring Async Rest Controller로, 직접 CompletableFuture를 리턴하는 메서드
    // Guide To CompletableFuture( Baeldung ) : https://www.baeldung.com/java-completablefuture
    @GetMapping("/user/{id}/{time}/{status}/{with}")
    public CompletableFuture<User> getUser( @PathVariable("id") final String id
            , @PathVariable("time") final String time
            , @PathVariable("status") final String status
            , @PathVariable("with") final String with
    ) {
        log.warn("# getUser({}) START!!", id);

        final CompletableFuture<String> future1 = CompletableFuture.supplyAsync(
                () -> proccessInternalTask(id),
                executor
        );
        final CompletableFuture<User> future2 = CompletableFuture.supplyAsync(
                () -> externalApiClient.getUserInfo(id, time, status),
                executor
        );

        final CompletableFuture<User> combineFuture = switch (with) {
                case "orTimeout_normal_return" -> getUserCompletableFutureWithOrTimeoutNormalReturn(future1, future2);
                case "orTimeout_error_return" -> getUserCompletableFutureWithOrTimeoutErrorReturn(future1, future2);
                case "completeOnTimeout" -> getUserCompletableFutureWithCompleteOnTimeout(future1, future2);
                case "completeExceptionally" -> getUserCompletableFutureWithCompleteExceptionally(future1, future2);
                default -> throw new RuntimeException("정의되지 않은 케이스");
        };

        // 여기서도 특정작업이 2초가 걸린다고 가정한다.
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return combineFuture;
    }

    // orTimeout() 확인용 메서드 : 예외리턴
    private CompletableFuture<User> getUserCompletableFutureWithOrTimeoutErrorReturn(final CompletableFuture<String> future1, final CompletableFuture<User> future2) {
        // 예외
        //      completeExceptionally() : 완료되지 않은 상태에서, get() 및 관련메서드 호출시, 지정된 예외 발생
        //      exceptionally() : 예외발생하면, 전달한 콜백을 실행시킴
        // 타임아웃
        //      orTimeout() : 주어진 시간안에 완료되지 않으면, TimeoutException을 쓰로우함.
        //      complelteOnTimeout() : 주어진 시간안에 완료되지 않으면, 주어진 값으로 CF를 완료함.

        final CompletableFuture<User> combineFuture = future1.thenCombineAsync( future2
                        , (f1, f2) -> {
                            log.warn("# thenCombineAsync!!");
                            return f2;
                        }
                        , executor
                )
                .orTimeout(5000L, TimeUnit.MILLISECONDS)
        ;

        return combineFuture;
    }

    // orTimeout() 확인용 메서드 : 정상리턴
    private CompletableFuture<User> getUserCompletableFutureWithOrTimeoutNormalReturn(final CompletableFuture<String> future1, final CompletableFuture<User> future2) {
        // 예외
        //      completeExceptionally() : 완료되지 않은 상태에서, get() 및 관련메서드 호출시, 지정된 예외 발생
        //      exceptionally() : 예외발생하면, 전달한 콜백을 실행시킴
        // 타임아웃
        //      orTimeout() : 주어진 시간안에 완료되지 않으면, TimeoutException을 쓰로우함.
        //      complelteOnTimeout() : 주어진 시간안에 완료되지 않으면, 주어진 값으로 CF를 완료함.

        final CompletableFuture<User> combineFuture = future1.thenCombineAsync( future2
                        , (f1, f2) -> {
                                log.warn("# thenCombineAsync!!");
                                return f2;
                        }
                        , executor
                )
                .orTimeout(5000L, TimeUnit.MILLISECONDS)
                .exceptionally(e -> {
                        log.warn( "# Error : ", e);
                        return new User("error", "error");
                })
        ;

        return combineFuture;
    }

    // completeOnTimeout() 확인용 메서드
    private CompletableFuture<User> getUserCompletableFutureWithCompleteOnTimeout(final CompletableFuture<String> future1, final CompletableFuture<User> future2) {
        // TODO : 예외처리를 어떻게 할 수 있는지 확인필요
        // TODO : 타임아웃처리 어떻게 할 수 있는지 확인필요
        // 예외
        //      completeExceptionally() : 완료되지 않은 상태에서, get() 및 관련메서드 호출시, 지정된 예외 발생
        //      exceptionally() : 예외발생하면, 전달한 콜백을 실행시킴
        // 타임아웃
        //      orTimeout() : 주어진 시간안에 완료되지 않으면, TimeoutException을 쓰로우함.
        //      complelteOnTimeout() : 주어진 시간안에 완료되지 않으면, 주어진 값으로 CF를 완료함.
        final CompletableFuture<User> combineFuture = future1.thenCombineAsync( future2
                        , (f1, f2) -> f2
                        , executor
                )
                .completeOnTimeout(new User("timeout", "timeout"), 10000L, TimeUnit.MILLISECONDS)
                .exceptionally(e -> {
                    log.warn( "# Error : ", e);
                    return new User("error", "error");
                })
        ;

        return combineFuture;
    }

    // completeOnTimeout() 확인용 메서드
    private CompletableFuture<User> getUserCompletableFutureWithCompleteExceptionally(final CompletableFuture<String> future1, final CompletableFuture<User> future2) {
        // TODO : 예외처리를 어떻게 할 수 있는지 확인필요
        // TODO : 타임아웃처리 어떻게 할 수 있는지 확인필요
        // 예외
        //      completeExceptionally() : 완료되지 않은 상태에서, get() 및 관련메서드 호출시, 지정된 예외 발생
        //      exceptionally() : 예외발생하면, 전달한 콜백을 실행시킴
        // 타임아웃
        //      orTimeout() : 주어진 시간안에 완료되지 않으면, TimeoutException을 쓰로우함.
        //      complelteOnTimeout() : 주어진 시간안에 완료되지 않으면, 주어진 값으로 CF를 완료함.
        final CompletableFuture<User> combineFuture = future1.thenCombineAsync( future2
                        , (f1, f2) -> f2
                        , executor
                )
                .orTimeout(5000L, TimeUnit.MILLISECONDS)
                .exceptionally(e -> {
                    log.warn( "# Error : ", e);
                    return new User("error", "error");
                })
        ;

        combineFuture.completeExceptionally(new RuntimeException("CF가 완료되지 않은 상황에서 get() 관련 메서드가 호출됨!!"));
        try {
            combineFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

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
