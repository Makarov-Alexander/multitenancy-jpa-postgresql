package ru.home.multitenancyjpapostgresql.customer.service;

import org.springframework.stereotype.Service;
import ru.home.multitenancyjpapostgresql.customer.dao.CustomerRepository;
import ru.home.multitenancyjpapostgresql.customer.dao.RoleDao;
import ru.home.multitenancyjpapostgresql.customer.model.Customer;

import javax.transaction.Transactional;

@Service
public class CustomerService {
    private CustomerRepository repository;

    private RoleDao sessionDao;

    public CustomerService(CustomerRepository repository, RoleDao sessionDao) {
        this.repository = repository;
        this.sessionDao = sessionDao;
    }

    @Transactional
    public Iterable<Customer> getCustomers(
            String department,
            String firstName,
            String lastName
    ) {
        sessionDao.setRole(department);

        if (!firstName.isEmpty() && !lastName.isEmpty()) {
            return repository.findByFirstNameAndLastName(firstName, lastName);
        }

        if (!firstName.isEmpty()) {
            return repository.findByFirstName(firstName);
        }

        if (!lastName.isEmpty()) {
            return repository.findByLastName(lastName);
        }

        return repository.findAll();
    }

    @Transactional
    public Customer createCustomer(String department, String firstName, String lastName) {
        sessionDao.setRole(department);
        Customer customer = new Customer(firstName, lastName, department);
        return repository.save(customer);
    }
}
