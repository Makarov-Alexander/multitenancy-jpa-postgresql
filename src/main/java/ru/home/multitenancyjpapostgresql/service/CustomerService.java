package ru.home.multitenancyjpapostgresql.service;

import org.springframework.stereotype.Service;
import ru.home.multitenancyjpapostgresql.dao.CustomerRepository;
import ru.home.multitenancyjpapostgresql.dao.RoleDao;
import ru.home.multitenancyjpapostgresql.model.Customer;

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
}
