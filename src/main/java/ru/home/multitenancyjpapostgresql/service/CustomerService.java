package ru.home.multitenancyjpapostgresql.service;

import org.springframework.stereotype.Service;
import ru.home.multitenancyjpapostgresql.dao.CustomerRepository;
import ru.home.multitenancyjpapostgresql.model.Customer;

import javax.transaction.Transactional;

@Service
public class CustomerService {

    private CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Iterable<Customer> getBobCustomers() {
        repository.setCurrentBobRole();
        return repository.findAll();
    }

    @Transactional
    public Iterable<Customer> getAliceCustomers() {
        repository.setCurrentAliceRole();
        return repository.findAll();
    }
}
