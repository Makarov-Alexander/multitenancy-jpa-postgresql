package ru.home.multitenancyjpapostgresql.dao;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.DataException;
import org.postgresql.util.PSQLException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

@Slf4j
@Service
public class SessionDao {

    @PersistenceContext
    private EntityManager entityManager;

    public SessionDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void setRole(String role) {
        Query nativeQuery = entityManager.createNativeQuery("set role " + role);
        nativeQuery.executeUpdate();
    }
}
