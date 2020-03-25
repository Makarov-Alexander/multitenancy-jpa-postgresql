package ru.home.multitenancyjpapostgresql;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.home.multitenancyjpapostgresql.admin.service.RoleService;
import ru.home.multitenancyjpapostgresql.customer.service.CustomerService;

@Slf4j
@Component
public class MultitenancyApplicationRunner implements ApplicationRunner {

    private RoleService roleService;
    private CustomerService customerService;

    public MultitenancyApplicationRunner(
            RoleService roleService,
            CustomerService customerService) {
        this.roleService = roleService;
        this.customerService = customerService;
    }

    @Override
    public void run(ApplicationArguments args) {
        try {
            roleService.createRole("tomsk");
            roleService.createRole("omsk");
        } catch (Exception e) {
            LOGGER.error("Could not create PostgreSQL roles {}", e.getMessage(), e);
        }

        customerService.createCustomer("tomsk", "Tralin", "Verdik");
        customerService.createCustomer("tomsk", "Tralin", "Verdik");
        customerService.createCustomer("tomsk", "Tralin", "Verdik");
        customerService.createCustomer("omsk", "Bralin", "Gerdik");
        customerService.createCustomer("omsk", "Bralin", "Gerdik");
        customerService.createCustomer("omsk", "Bralin", "Gerdik");

        LOGGER.info("All found customers: ");
        customerService.getCustomers("tomsk", "", "").forEach(c -> LOGGER.info(c.toString()));
        customerService.getCustomers("omsk", "", "").forEach(c -> LOGGER.info(c.toString()));
    }
}
