package ru.home.multitenancyjpapostgresql.customer.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Slf4j
@Service
public class RoleDao {

    private EntityManager entityManager;

    @Autowired
    public RoleDao(@Qualifier("entityManagerFactory") EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void setRole(String role) {
        Query nativeQuery = entityManager.createNativeQuery("SET ROLE " + role);
        nativeQuery.executeUpdate();
    }

}
