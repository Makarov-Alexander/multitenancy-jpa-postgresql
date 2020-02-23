package ru.home.multitenancyjpapostgresql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.home.multitenancyjpapostgresql.dao.CustomerRepository;
import ru.home.multitenancyjpapostgresql.model.Customer;

@SpringBootApplication
public class MultitenancyJpaPostgresqlApplication {
	public static void main(String[] args) {
		SpringApplication.run(MultitenancyJpaPostgresqlApplication.class, args);
	}
}
