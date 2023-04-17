package com.kenny.springwebflux.learning.basic;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class ReactorBasic {

    @Test
    void Reactor_Flux_구성요소_확인() {
        Flux.just("Hello", "Reactor")
                .map(String::toLowerCase)
                .subscribe(System.out::println);
    }

    @Test
    void Reactor_Mono_구성요소_확인() {
        Mono.just("Hello Reactor")
                .map(String::toLowerCase)
                .subscribe(System.out::println);
    }

    @Test
    void Mono_Empty_오퍼레이터() {
        Mono.empty()
                .subscribe(
                        next -> System.out.println("# emitted onNext signal"),
                        error -> {},
                        () -> System.out.println("# emitted onComplete signal")
                );
    }

    @Test
    void Flux_fromArray() {
        Flux.fromArray(new Integer[]{ 3, 6, 7, 9})
                .filter(num -> num > 6)
                .doOnNext(num -> System.out.println("# doOnNext : " + num))
                .map(num -> num * 2)
                .subscribe(System.out::println);
    }

    @Test
    void Mono_concatWith() {
        Mono.justOrEmpty("Steve")
                .concatWith(Mono.justOrEmpty("Jobs"))
                .subscribe(System.out::println);
    }

    @Test
    void Flux_concat_collectList() {
        Flux.concat(
                        Flux.just("Mercury", "Venus", "Earth"),
                        Flux.just("Mars", "Jupiter", "Saturn"),
                        Flux.just("Uranus", "Neptune", "Pluto")
                )
                .collectList()
                .subscribe(System.out::println);
    }
}
