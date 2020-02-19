package ru.home.multitenancyjpapostgresql.controller;

import org.springframework.web.bind.annotation.*;
import ru.home.multitenancyjpapostgresql.model.Customer;
import ru.home.multitenancyjpapostgresql.service.CustomerService;

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
}
