package ru.home.multitenancyjpapostgresql.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Slf4j
@Service
public class RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    public RoleDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void setRole(String role) {
        Query nativeQuery = entityManager.createNativeQuery("SET ROLE " + role);
        nativeQuery.executeUpdate();
    }

    public void createRole(String role) {
        Query nativeQuery = entityManager.createNativeQuery(
                String.format(
                    "RESET ROLE;\n" +
                    "CREATE ROLE %1$s;\n" +
                    "GRANT %1$s TO customer_none;\n" +
                    "CREATE TABLE customer_%1$s PARTITION OF customer FOR VALUES IN (\'%1$s\');", role));
        nativeQuery.executeUpdate();
    }

}
