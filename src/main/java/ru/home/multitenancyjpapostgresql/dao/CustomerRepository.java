package ru.home.multitenancyjpapostgresql.dao;

import org.springframework.data.repository.CrudRepository;
import ru.home.multitenancyjpapostgresql.model.Customer;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Customer findById(long id);
    List<Customer> findByLastName(String lastName);
    List<Customer> findByFirstName(String firstName);
}
