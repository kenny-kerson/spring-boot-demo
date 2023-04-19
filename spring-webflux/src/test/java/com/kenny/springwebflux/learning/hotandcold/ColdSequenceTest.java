package com.kenny.springwebflux.learning.hotandcold;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.util.Arrays;

public class ColdSequenceTest {

    @Test
    void Cold_Sequence_Basic() {
        final Flux<String> coldFlux = Flux.fromIterable(Arrays.asList("KOREA", "JAPAN", "CHINESE"))
                .doOnNext(country -> System.out.println("# doOnNext : " + country))
                .map(String::toLowerCase)
                .doOnNext(country -> System.out.println("# map : " + country));

        coldFlux.subscribe(country -> System.out.println("# subscribe1 : " + country));

        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {

        }

        coldFlux.subscribe(country -> System.out.println("# subscribe2 : " + country));
    }
}
