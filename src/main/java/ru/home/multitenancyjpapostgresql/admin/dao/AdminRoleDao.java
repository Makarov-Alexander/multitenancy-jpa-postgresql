package ru.home.multitenancyjpapostgresql.admin.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Slf4j
@Service
public class AdminRoleDao {

    private EntityManager entityManager;

    @Autowired
    public AdminRoleDao(@Qualifier("adminEntityManagerFactory") EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void createRole(String role) {
        System.out.println("+++ CURRENT_USER: " +
                entityManager.createNativeQuery("select current_user").getSingleResult().toString());

        Query nativeQuery = entityManager.createNativeQuery(
                String.format(
                    "CREATE ROLE %1$s;\n" +
                    "GRANT %1$s TO customer_none;\n" +
                    "CREATE TABLE customer_%1$s PARTITION OF customer FOR VALUES IN (\'%1$s\');", role));
        nativeQuery.executeUpdate();
    }

}
