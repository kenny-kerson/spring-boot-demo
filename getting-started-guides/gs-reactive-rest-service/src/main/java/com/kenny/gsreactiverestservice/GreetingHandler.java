package com.kenny.gsreactiverestservice;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

// In the Spring Reactive approach, we use a handler to handle the request and create a response
@Component
public class GreetingHandler {

    public Mono<ServerResponse> hello(final ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        BodyInserters.fromValue(new Greeting("Hello, Spring Reactive!"))
                );
    }
}
