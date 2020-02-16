package ru.home.multitenancyjpapostgresql.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.home.multitenancyjpapostgresql.model.Customer;
import ru.home.multitenancyjpapostgresql.service.CustomerService;

@RestController
public class CustomerController {


    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping("/customers/{department}?firstname={firstname}")
    public Iterable<Customer> getBobCustomersByFirstName(
            @PathVariable("department") String department,
            @PathVariable("firstname") String firstName) {
        return customerService.getCustomersByFirstName(department, firstName);
    }

    @GetMapping("/customers/{department}?lastname={lastname}")
    public Iterable<Customer> getBobCustomersByLastName(
            @PathVariable("department") String department,
            @PathVariable("lastname") String lastName) {
        return customerService.getCustomersByLastName(department, lastName);
    }

    @GetMapping("/customers/{department}")
    public Iterable<Customer> getBobCustomers(@PathVariable("department") String department) {
        return customerService.getCustomers(department);
    }
}
