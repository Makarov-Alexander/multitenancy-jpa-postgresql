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
                "RESET ROLE;\n" +
                "CREATE ROLE " + role + ";\n" +
                "GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE customer TO " + role + ";\n" +
                "GRANT USAGE, SELECT ON SEQUENCE hibernate_sequence TO " + role + ";");
        nativeQuery.executeUpdate();
    }
}
