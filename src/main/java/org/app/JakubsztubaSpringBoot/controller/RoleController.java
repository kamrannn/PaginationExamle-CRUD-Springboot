package org.app.JakubsztubaSpringBoot.controller;

import org.app.JakubsztubaSpringBoot.model.Role;
import org.app.JakubsztubaSpringBoot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RoleController {

    RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/list")
    public ResponseEntity<Object> list() {
        return roleService.listAllRoles();
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(@RequestBody Role role) {
        return roleService.addNewRole(role);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> delete(@RequestParam Long roleId) {
        return roleService.deleteRole(roleId);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> update(@RequestBody Role role) {
        return roleService.updateRole(role);
    }
}
