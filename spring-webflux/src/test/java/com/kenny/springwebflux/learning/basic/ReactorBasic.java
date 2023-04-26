package com.kenny.springwebflux.learning.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class ReactorBasic {

    @Test
    void Reactor_Flux_구성요소_확인() {
        Flux.just("Hello", "Reactor")
                .map(String::toLowerCase)
                .subscribe(log::warn);
    }

    @Test
    void Reactor_Mono_구성요소_확인() {
        Mono.just("Hello Reactor")
                .map(String::toLowerCase)
                .subscribe(log::warn);
    }

    @Test
    void Mono_Empty_오퍼레이터() {
        Mono.empty()
                .subscribe(
                        next -> log.warn("# emitted onNext signal"),
                        error -> {},
                        () -> log.warn("# emitted onComplete signal")
                );
    }

    @Test
    void Flux_fromArray() {
        Flux.fromArray(new Integer[]{ 3, 6, 7, 9})
                .filter(num -> num > 6)
                .doOnNext(num -> System.out.println("# doOnNext : " + num))
                .map(num -> num * 2)
                .subscribe(complete -> log.warn(String.valueOf(complete)));
    }

    @Test
    void Mono_concatWith() {
        Mono.justOrEmpty("Steve")
                .concatWith(Mono.justOrEmpty("Jobs"))
                .subscribe(complete -> log.warn(String.valueOf(complete)));
    }

    @Test
    void Flux_concat_collectList() {
        Flux.concat(
                        Flux.just("Mercury", "Venus", "Earth"),
                        Flux.just("Mars", "Jupiter", "Saturn"),
                        Flux.just("Uranus", "Neptune", "Pluto")
                )
                .collectList()
                .subscribe(complete -> log.warn(String.valueOf(complete)));
    }
}
