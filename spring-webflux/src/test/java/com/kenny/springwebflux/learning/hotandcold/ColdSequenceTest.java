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
import java.util.Arrays;

@Slf4j
public class ColdSequenceTest {

    @Test
    void Cold_Sequence_Basic() {

        final Flux<String> coldFlux = Flux.fromIterable(Arrays.asList("KOREA", "JAPAN", "CHINESE"))
                .doOnNext(country -> log.warn("# doOnNext : " + country))
                .map(String::toLowerCase)
                .doOnNext(country -> log.warn("# map : " + country));

        coldFlux.subscribe(country -> log.warn("# subscribe1 : " + country));

        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {

        }

        coldFlux.subscribe(country -> log.warn("# subscribe2 : " + country));

        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {

        }
    }

    @Test
    void HTTP_Request_Cold_Sequence() {
        final URI uri = UriComponentsBuilder.newInstance().scheme("http")
                .host("worldtimeapi.org")
                .port(80)
                .path("/api/timezone/Asia/Seoul")
                .build()
                .encode()
                .toUri();

        final Mono<String> mono = getWorldTime(uri);
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
