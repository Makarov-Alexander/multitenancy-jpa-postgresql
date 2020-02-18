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
    public Iterable<Customer> getCustomers(String department) {
        sessionDao.setRole(department);
        return repository.findAll();
    }

    @Transactional
    public Iterable<Customer> getCustomersByFirstName(String department, String firstName) {
        sessionDao.setRole(department);
        return repository.findByFirstName(firstName);
    }


    @Transactional
    public Iterable<Customer> getCustomersByLastName(String department, String lastName) {
        sessionDao.setRole(department);
        return repository.findByLastName(lastName);
    }
}
