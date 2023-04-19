package com.kenny.springwebflux.learning.hotandcold;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;

public class HotSequenceTest {

    @Test
    void Hot_Sequence_Basic() {
        final Flux<String> hotFlux = Flux.fromIterable(Arrays.asList("Singer A", "Singer B", "Singer C", "Singer D", "Singer E"))
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(singer -> System.out.println("# doOnNext : " + singer + " Thread : " + Thread.currentThread()))
                .share();

        hotFlux.subscribe(singer -> System.out.println("# subscribe1 : " + singer + " Thread : " + Thread.currentThread()));

        try {
            Thread.sleep(2500L);
        } catch (InterruptedException e) {

        }

        hotFlux.subscribe(singer -> System.out.println("# subscribe2 : " + singer + " Thread : " + Thread.currentThread()));

        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {

        }
    }
}
