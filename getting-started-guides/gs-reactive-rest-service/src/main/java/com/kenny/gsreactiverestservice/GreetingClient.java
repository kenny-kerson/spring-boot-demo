package com.kenny.gsreactiverestservice;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

// The Spring RestTemplate class is, by nature, blocking.
// Consequently, we do not want to use it in a reactive application.
// For reactive applications, Spring offers the WebClient class, which is non-blocking.
// We use a WebClient-based implementation to consume our RESTful service
@Component
public class GreetingClient {

    private final WebClient webClient;

    // Spring Boot auto-configures a `WebClient.Builder` instance with nice defaults and customizations.
    // We can use it to create a dedicated `WebClient` for our component.
    public GreetingClient( final WebClient.Builder builder ) {
        this.webClient = builder.baseUrl("http://localhost:8080").build();
    }

    // The WebClient class uses reactive features, in the form of a Mono to hold the content of the message (returned by the getMessage method).
    // This is using a function API, rather than an imperative one, to chain reactive operators.
    public Mono<String> getMessage() {
        return this.webClient
                .get()
                .uri("/hello")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Greeting.class)
                .map(Greeting::getMessage);
    }
}
