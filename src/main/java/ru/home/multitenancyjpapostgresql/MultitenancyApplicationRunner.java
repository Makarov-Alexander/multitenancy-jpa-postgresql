package ru.home.multitenancyjpapostgresql;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.home.multitenancyjpapostgresql.dao.CustomerRepository;
import ru.home.multitenancyjpapostgresql.model.Customer;
import ru.home.multitenancyjpapostgresql.service.RoleService;

@Slf4j
@Component
public class MultitenancyApplicationRunner implements ApplicationRunner {

    private CustomerRepository customerRepository;
    private RoleService roleService;

    public MultitenancyApplicationRunner(CustomerRepository customerRepository, RoleService roleService) {
        this.customerRepository = customerRepository;
        this.roleService = roleService;
    }

    @Override
    public void run(ApplicationArguments args) {
        try {
            roleService.createRole("tomsk");
            roleService.createRole("omsk");
        } catch (Exception e) {
            log.error("Could not create PostgreSQL role", e);
        }

        customerRepository.save(new Customer("Tralin", "Verdik", "tomsk"));
        customerRepository.save(new Customer("Tralin", "Verdik", "tomsk"));
        customerRepository.save(new Customer("Tralin", "Verdik", "tomsk"));
        customerRepository.save(new Customer("Bralin", "Gerdik", "omsk"));
        customerRepository.save(new Customer("Bralin", "Gerdik", "omsk"));
        customerRepository.save(new Customer("Bralin", "Gerdik", "omsk"));

        log.info("All found customers: ");
        customerRepository.findAll().forEach(c -> log.info(c.toString()));
    }
}
