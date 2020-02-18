package ru.home.multitenancyjpapostgresql.service;

import org.springframework.stereotype.Service;
import ru.home.multitenancyjpapostgresql.dao.RoleDao;

import javax.transaction.Transactional;

@Service
public class RoleService {

    private RoleDao roleDao;

    public RoleService(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Transactional
    public void createRole(String name) {
        roleDao.createRole(name);
    }
}
