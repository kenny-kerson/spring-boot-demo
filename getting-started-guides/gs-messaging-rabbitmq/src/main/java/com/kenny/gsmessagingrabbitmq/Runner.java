package com.kenny.gsmessagingrabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class Runner implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;

    public Runner(final RabbitTemplate rabbitTemplate, final Receiver receiver) {
        this.rabbitTemplate = rabbitTemplate;
        this.receiver = receiver;
    }

    @Override
    public void run(final String... args) throws Exception {
        System.out.println("Sending message...");

        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS );

        rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE_NAME
                , "foo.bar.baz"
                , "Hello from RabbitMQ!");


    }
}
