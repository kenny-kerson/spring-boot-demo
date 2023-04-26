package com.kenny.springwebflux.learning.hotandcold;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Duration;
import java.util.Arrays;

@Slf4j
public class HotSequenceTest {

    @Test
    void Hot_Sequence_Basic() {
        final Flux<String> hotFlux = Flux.fromIterable(Arrays.asList("Singer A", "Singer B", "Singer C", "Singer D", "Singer E"))
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(singer -> log.warn("# doOnNext : " + singer))
                .share();

        hotFlux.subscribe(singer -> log.warn(("# subscribe1 : " + singer)));

        try {
            Thread.sleep(2500L);
        } catch (InterruptedException e) {

        }

        hotFlux.subscribe(singer -> log.warn(("# subscribe2 : " + singer)));

        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {

        }
    }

    @Test
    void HTTP_Request_Hot_Sequence() {
        final URI uri = UriComponentsBuilder.newInstance().scheme("http")
                .host("worldtimeapi.org")
                .port(80)
                .path("/api/timezone/Asia/Seoul")
                .build()
                .encode()
                .toUri();

        final Mono<String> mono = getWorldTime(uri)
                .cache();
        mono.subscribe(dateTime -> log.warn("# dateTime 1: {}", dateTime));

        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        mono.subscribe(dateTime -> log.warn("# dateTime 2: {}", dateTime));

        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static Mono<String> getWorldTime(final URI uri) {
        return WebClient.create()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class)
                .map(response -> {
                    final DocumentContext jsonContext = JsonPath.parse(response);
                    return jsonContext.read("$.datetime");
                });
    }
}
