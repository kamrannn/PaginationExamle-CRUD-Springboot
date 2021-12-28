package org.app.JakubsztubaSpringBoot.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.app.JakubsztubaSpringBoot.model.Permission;
import org.app.JakubsztubaSpringBoot.service.PermissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/permissions")
public class PermissionController {
    private static final Logger LOG = LogManager.getLogger(PermissionController.class);

    PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping("/list")
    public ResponseEntity<Object> list() {
        return permissionService.listAllPermissions();
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(@RequestBody Permission permission) {
        return permissionService.addNewPermission(permission);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> delete(@RequestParam Long permissionId) {
        return permissionService.deletePermission(permissionId);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> update(@RequestBody Permission permission) {
        return permissionService.updatePermission(permission);
    }
}