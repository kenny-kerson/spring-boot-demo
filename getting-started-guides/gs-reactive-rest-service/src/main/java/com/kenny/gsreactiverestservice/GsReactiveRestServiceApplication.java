package com.kenny.gsreactiverestservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class GsReactiveRestServiceApplication {

    public static void main(String[] args) {
        final ConfigurableApplicationContext context = SpringApplication.run(GsReactiveRestServiceApplication.class, args);

        final GreetingClient greetingClient = context.getBean(GreetingClient.class);
        System.out.println(">> message = " + greetingClient.getMessage().block());
    }

}
