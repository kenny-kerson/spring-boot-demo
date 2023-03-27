package com.kenny.gsspringdatareactiveredis.config;

import com.kenny.gsspringdatareactiveredis.domain.Coffee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class CoffeeConfiguration {

    @Bean
    public ReactiveRedisOperations<String, Coffee> redisOperations(final ReactiveRedisConnectionFactory factory) {
        final Jackson2JsonRedisSerializer<Coffee> serializer = new Jackson2JsonRedisSerializer<>(Coffee.class);
        final RedisSerializationContext.RedisSerializationContextBuilder<String, Coffee> builder
                = RedisSerializationContext.newSerializationContext(new StringRedisSerializer());
        final RedisSerializationContext<String, Coffee> context = builder.value(serializer).build();
        return new ReactiveRedisTemplate<>(factory, context);
    }
}
