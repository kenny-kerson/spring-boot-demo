package com.kenny.gsmessagingrabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Spring Boot automatically creates a connection factory and a RabbitTemplate, reducing the amount of code you have to write.
// Spring AMQP’s RabbitTemplate provides everything you need to send and receive messages with RabbitMQ
// However, you need to:
//      Configure a message listener container.
//      Declare the queue, the exchange, and the binding between them.
//      Configure a component to send some messages to test the listener.

@Configuration
public class RabbitMQConfig {

    public static final String TOPIC_EXCHANGE_NAME = "spring-boot-exchange";
    private static final String QUEUE_NAME = "spring-boot";

    // JMS queues and AMQP queues have different semantics.
    // For example, JMS sends queued messages to only one consumer.
    // While AMQP queues do the same thing, AMQP producers do not send messages directly to queues.
    // Instead, a message is sent to an exchange, which can go to a single queue or fan out to multiple queues, emulating the concept of JMS topics.
    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }

    // The queue() method creates an AMQP queue.
    // The exchange() method creates a topic exchange.
    // The binding() method binds these two together, defining the behavior that occurs when RabbitTemplate publishes to an exchange.
    @Bean
    public Binding binding(final Queue queue, final TopicExchange exchange) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with("foo.bar.#");
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(final Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    @Bean
    public SimpleMessageListenerContainer container(final ConnectionFactory connectionFactory
            , final MessageListenerAdapter messageListenerAdapter) {
        final SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_NAME);
        container.setMessageListener(messageListenerAdapter);
        return container;
    }
}
