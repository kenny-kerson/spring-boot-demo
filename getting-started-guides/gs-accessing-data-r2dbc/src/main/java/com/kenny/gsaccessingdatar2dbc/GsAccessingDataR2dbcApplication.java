package com.kenny.gsaccessingdatar2dbc;

import com.kenny.gsaccessingdatar2dbc.domain.Customer;
import com.kenny.gsaccessingdatar2dbc.domain.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Duration;
import java.util.Arrays;

// By default, Spring Boot enables R2DBC repository support and looks in the package (and its subpackages) where @SpringBootApplication is located.
// If your configuration has R2DBC repository interface definitions located in a package that is not visible,
// you can point out alternate packages by using @EnableR2dbcRepositories and its type-safe basePackageClasses=MyRepository.class parameter.
@SpringBootApplication
public class GsAccessingDataR2dbcApplication {

    private static final Logger log = LoggerFactory.getLogger(GsAccessingDataR2dbcApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(GsAccessingDataR2dbcApplication.class, args);
    }

    // R2DBC is a reactive programming technology.
    // At the same time we’re using it in a synchronized, imperative flow and that is why we’re required to synchronize each call with a variant of the block(…) method.
    // In a typical reactive application, the resulting Mono or Flux would represent a pipeline of operators that is handed back to a web controller or
    // event processor that subscribes to the reactive sequence without blocking the calling thread.
    @Bean
    public CommandLineRunner demo(final CustomerRepository repository) {
        return (args) -> {
            // save a few customers
            repository.saveAll(Arrays.asList( new Customer("Jack", "Bauer"),
                    new Customer("Chloe", "O'Brian"),
                    new Customer("Kim", "Bauer"),
                    new Customer("David", "Palmer"),
                    new Customer("Michelle", "Palmer")))
                    .blockLast(Duration.ofSeconds(10));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");

            repository.findAll()
                    .doOnNext( customer -> {
                            log.info(customer.toString());
                    })
                    .blockLast(Duration.ofSeconds(10));

            // fetch an individual customer by ID
            repository.findById(1L)
                    .doOnNext(customer -> {
                            log.info("Customer found with findById(1L):");
                            log.info("--------------------------------");
                            log.info(customer.toString());
                            log.info("");
                    })
                    .block(Duration.ofSeconds(10));

            // fetch customers by last name
            log.info("Customer found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            repository.findByLastName("Bauer")
                    .doOnNext(bauer -> {
                            log.info(bauer.toString());
                    })
                    .blockLast(Duration.ofSeconds(10));
            log.info("");
        };
    }
}
