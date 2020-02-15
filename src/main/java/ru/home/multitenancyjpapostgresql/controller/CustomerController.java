package ru.home.multitenancyjpapostgresql.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.home.multitenancyjpapostgresql.model.Customer;
import ru.home.multitenancyjpapostgresql.service.CustomerService;

@RestController
public class CustomerController {


    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers/bob")
    public Iterable<Customer> getBobCustomers() {
        return customerService.getBobCustomers();
    }

    @GetMapping("/customers/alice")
    public Iterable<Customer> getAliceCustomers() {
        return customerService.getAliceCustomers();
    }
}
