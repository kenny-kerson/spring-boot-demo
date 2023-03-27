package com.kenny.gsaccessingdatar2dbc.domain;

import com.kenny.gsaccessingdatar2dbc.domain.Customer;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

// CustomerRepository extends the ReactiveCrudRepository interface.
// The type of entity and ID that it works with, Customer and Long, are specified in the generic parameters on ReactiveCrudRepository.
// By extending ReactiveCrudRepository, CustomerRepository inherits several methods for working with Customer persistence, including methods for saving, deleting, and finding Customer entities using reactive types.
// Spring Data R2DBC also lets you define other query methods by annotating these with @Query. For example, CustomerRepository includes the findByLastName() method.
public interface CustomerRepository extends ReactiveCrudRepository<Customer, Long> {

    @Query("select * from customer where last_name = :lastname")
    Flux<Customer> findByLastName(String lastName);
}
