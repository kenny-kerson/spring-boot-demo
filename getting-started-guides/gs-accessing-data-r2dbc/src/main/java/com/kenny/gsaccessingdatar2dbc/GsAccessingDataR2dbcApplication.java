package com.kenny.gsaccessingdatar2dbc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Duration;
import java.util.Arrays;

@SpringBootApplication
public class GsAccessingDataR2dbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(GsAccessingDataR2dbcApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(final CustomerRepository repository) {
        return (args) -> {
            repository.saveAll(Arrays.asList( new Customer("Jack", "Bauer"),
                    new Customer("Chloe", "O'Brian"),
                    new Customer("Kim", "Bauer"),
                    new Customer("David", "Palmer"),
                    new Customer("Michelle", "Palmer")))
                    .blockLast(Duration.ofSeconds(10));

        };
    }
}
