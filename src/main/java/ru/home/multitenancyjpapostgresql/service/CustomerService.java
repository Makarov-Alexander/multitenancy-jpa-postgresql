package ru.home.multitenancyjpapostgresql.service;

import org.springframework.stereotype.Service;
import ru.home.multitenancyjpapostgresql.dao.CustomerRepository;
import ru.home.multitenancyjpapostgresql.dao.SessionDao;
import ru.home.multitenancyjpapostgresql.model.Customer;

import javax.transaction.Transactional;

@Service
public class CustomerService {

    private CustomerRepository repository;
    private SessionDao sessionDao;

    public CustomerService(CustomerRepository repository, SessionDao sessionDao) {
        this.repository = repository;
        this.sessionDao = sessionDao;
    }

    @Transactional
    public Iterable<Customer> getCustomers(String department) {
        sessionDao.setRole(department);
        return repository.findAll();
    }
}
