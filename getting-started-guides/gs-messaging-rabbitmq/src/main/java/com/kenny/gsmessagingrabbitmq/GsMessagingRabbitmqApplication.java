package com.kenny.gsmessagingrabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GsMessagingRabbitmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(GsMessagingRabbitmqApplication.class, args).close();
    }

}
