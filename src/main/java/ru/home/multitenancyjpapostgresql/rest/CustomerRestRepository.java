package ru.home.multitenancyjpapostgresql.rest;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.home.multitenancyjpapostgresql.model.Customer;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "customer", path = "user")
public interface CustomerRestRepository extends PagingAndSortingRepository<Customer, Long> {
    List<Customer> findByFirstName(@Param("name") String name);
}
