package ru.home.multitenancyjpapostgresql;


import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "adminEntityManagerFactory",
        transactionManagerRef = "adminTransactionManager",
        basePackages = { "ru.home.multitenancyjpapostgresql.admin.dao" }
)
public class AdminDbConfig {

    @Bean(name = "adminDataSourceProperties")
    @ConfigurationProperties("admin.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "adminDataSource")
    @ConfigurationProperties("admin.datasource.configuration")
    public DataSource dataSource(
            @Qualifier("adminDataSourceProperties") DataSourceProperties adminDataSourceProperties
    ) {
        return adminDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class)
                .build();
    }

    @Bean(name = "adminEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean adminEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("adminDataSource") DataSource dataSource
    ) {
        return builder
                .dataSource(dataSource)
                .packages("ru.home.multitenancyjpapostgresql.admin.service")
                .persistenceUnit("admin")
                .build();
    }

    @Bean(name = "adminTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("adminEntityManagerFactory") EntityManagerFactory adminEntityManagerFactory
    ) {
        return new JpaTransactionManager(adminEntityManagerFactory);
    }
}
