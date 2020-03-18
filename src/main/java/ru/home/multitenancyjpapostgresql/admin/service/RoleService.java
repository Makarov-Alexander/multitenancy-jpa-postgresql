package ru.home.multitenancyjpapostgresql.admin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.home.multitenancyjpapostgresql.admin.dao.AdminRoleDao;

@Service
public class RoleService {

    private AdminRoleDao roleDao;

    public RoleService(AdminRoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Transactional(value = "adminTransactionManager")
    public void createRole(String name) {
        roleDao.createRole(name);
    }
}
