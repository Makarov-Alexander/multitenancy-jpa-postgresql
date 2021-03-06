package ru.home.multitenancyjpapostgresql.customer.dao;

import org.springframework.data.repository.CrudRepository;
import ru.home.multitenancyjpapostgresql.customer.model.Customer;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    List<Customer> findByFirstName(String firstName);
    List<Customer> findByLastName(String lastName);
    List<Customer> findByFirstNameAndLastName(String firstName, String lastName);
}
