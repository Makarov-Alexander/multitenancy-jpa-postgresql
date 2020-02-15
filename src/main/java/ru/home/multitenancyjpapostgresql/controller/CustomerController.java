package ru.home.multitenancyjpapostgresql.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.home.multitenancyjpapostgresql.dao.CustomerRepository;
import ru.home.multitenancyjpapostgresql.model.Customer;

@RestController
public class CustomerController {

    private CustomerRepository repository;

    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/all")
    public Iterable<Customer> getCustomer() {
        return repository.findAll();
    }
}
