package com.kenny.springwebflux.learning.backpressure;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Slf4j
public class BackpressureTest {

    @Test
    void Subscriber가_적절한_데이터개수를_Publisher에게_요청() {
        Flux.range(1, 5)
                .doOnRequest(data -> log.warn("# doOnRequest : {}", data))
                .subscribe(new BaseSubscriber<Integer>() {
                        @Override
                        protected void hookOnSubscribe(final Subscription subscription) {
                            request(2);
                        }

                        @SneakyThrows
                        @Override
                        protected void hookOnNext(final Integer value) {
                            Thread.sleep(2000L);
                            log.warn("# hookOnNext : {}", value);
                            request(2);
                        }
                });
    }

    @Test
    @SneakyThrows
    void Backpressure_ERROR전략() {
        Flux.interval(Duration.ofMillis(1L))
                .onBackpressureError()
                .doOnNext(data -> log.warn("# doOnNext : {}", data))
                .publishOn(Schedulers.parallel())
                .subscribe(data -> {
                    try {
                        Thread.sleep(5L);
                    } catch (InterruptedException e) {}

                    log.warn("# onNext : {}", data);
                },
                error -> log.warn("# onError", error));

        Thread.sleep(2000L);
    }

    @Test
    @SneakyThrows
    void Backpressure_DROP전략() {
        Flux.interval(Duration.ofMillis(1L))
                .onBackpressureDrop(dropped -> log.info("# dropped : {}", dropped))
                .publishOn(Schedulers.parallel())
                .subscribe(
                        data -> {
                            try {
                                Thread.sleep(5L);
                            } catch (InterruptedException e) {}

                            log.warn("# onNext : {}", data);
                        },
                        error -> log.error("# onError", error)
                );

        Thread.sleep(2000L);
    }
}
