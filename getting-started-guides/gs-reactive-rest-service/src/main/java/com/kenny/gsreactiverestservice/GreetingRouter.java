package com.kenny.gsreactiverestservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

// In this application, we use a router to handle the only route we expose api path
// The router listens for traffic on the /hello path and returns the value provided by our reactive handler class.
@Configuration(proxyBeanMethods = false)
public class GreetingRouter {

    @Bean
    public RouterFunction<ServerResponse> router( final GreetingHandler greetingHandler ) {
        return RouterFunctions.route()
                .GET("/hello", accept(MediaType.APPLICATION_JSON), greetingHandler::hello)
                .build()
        ;
    }
}
