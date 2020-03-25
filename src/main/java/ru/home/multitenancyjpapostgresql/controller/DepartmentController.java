package ru.home.multitenancyjpapostgresql.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.home.multitenancyjpapostgresql.admin.service.RoleService;

@RestController
@RequestMapping("/departments")
public class RoleController {

    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/create/{name}")
    public String createRole(@PathVariable String name) {
        roleService.createRole(name);
        return "Role " + name + " created.";
    }
}
