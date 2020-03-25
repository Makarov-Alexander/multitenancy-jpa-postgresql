package ru.home.multitenancyjpapostgresql.admin.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.home.multitenancyjpapostgresql.admin.dao.AdminRoleDao;

import javax.persistence.PersistenceException;

@Slf4j
@Service
public class RoleService {

    private AdminRoleDao roleDao;

    public RoleService(AdminRoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Transactional(value = "adminTransactionManager")
    public void createRole(String name) {
        try {
            roleDao.createRole(name);
        } catch (PersistenceException e) {
            LOGGER.error("Create role exception", e);
            String message = e.getMessage();
            if (e.getCause() != null) {
                message = e.getCause().getMessage();
                if (e.getCause().getCause() != null) {
                    message = e.getCause().getCause().getMessage();
                }
            }

            throw new RuntimeException("Error, while creating role: " + message, e);
        }

    }
}
