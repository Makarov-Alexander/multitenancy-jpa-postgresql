package ru.home.multitenancyjpapostgresql.service;

import org.springframework.stereotype.Service;
import ru.home.multitenancyjpapostgresql.dao.CustomerRepository;
import ru.home.multitenancyjpapostgresql.model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Service
public class CustomerService {
    private CustomerRepository repository;
    private EntityManager entityManager;

    public CustomerService(
            CustomerRepository repository,
            EntityManager entityManager) {
        this.repository = repository;
        this.entityManager = entityManager;
    }

    @Transactional
    public Iterable<Customer> getCustomers(
            String department,
            String firstName,
            String lastName
    ) {
        entityManager.createNamedQuery("role.set")
                .setParameter(
                        "role",
                        department
                )
                .getResultList();

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
        entityManager.createNamedQuery("role.set")
                .setParameter(
                        1,
                        department
                )
                .getSingleResult();

        Customer customer = new Customer(firstName, lastName, department);
        return repository.save(customer);
    }
}
