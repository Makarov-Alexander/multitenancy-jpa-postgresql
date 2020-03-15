package ru.home.multitenancyjpapostgresql.admin.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Map;

@Slf4j
@Service
public class RoleDao {

    @PersistenceContext(unitName = "admin")
    private EntityManager entityManager;

    public RoleDao(
            @Qualifier("adminEntityManagerFactory") EntityManagerFactory adminEntityManagerFactory
    ) {
        this.entityManager = adminEntityManagerFactory.createEntityManager();
        Map<String, Object> properties = adminEntityManagerFactory.getProperties();
        properties.forEach((s, o) -> System.out.printf("+++ %s: %s\n", s, o.toString()));
    }

    public void setRole(String role) {
        Query nativeQuery = entityManager.createNativeQuery("SET ROLE " + role);
        nativeQuery.executeUpdate();
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
