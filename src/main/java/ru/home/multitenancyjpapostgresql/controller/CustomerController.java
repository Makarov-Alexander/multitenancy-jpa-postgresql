package ru.home.multitenancyjpapostgresql.controller;

import org.springframework.web.bind.annotation.*;
import ru.home.multitenancyjpapostgresql.customer.model.Customer;
import ru.home.multitenancyjpapostgresql.customer.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{department}")
    public Iterable<Customer> getCustomers(
            @PathVariable("department") String department,
            @RequestParam(value = "firstname", required = false, defaultValue = "") String firstName,
            @RequestParam(value = "lastname", required = false, defaultValue = "") String lastName
    ) {
        return customerService.getCustomers(department, firstName, lastName);
    }

    @GetMapping("/create/{department}/{firstname}/{lastname}")
    public String createCustomer(
            @PathVariable("department") String department,
            @PathVariable("firstname") String firstName,
            @PathVariable("lastname") String lastName
    ) {
        return customerService.createCustomer(department, firstName, lastName).toString();
    }
}
