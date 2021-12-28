package org.app.JakubsztubaSpringBoot.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.app.JakubsztubaSpringBoot.model.Permission;
import org.app.JakubsztubaSpringBoot.repository.PermissionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService {
    private static final Logger LOG = LogManager.getLogger(PermissionService.class);
    PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public ResponseEntity<Object> listAllPermissions() {
        try {
            List<Permission> permissionList = permissionRepository.findAll();
            if (permissionList.isEmpty()) {
                return new ResponseEntity<>("There are no permissions in the Database", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(permissionList, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            LOG.info("Error: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> addNewPermission(Permission permission) {
        try {
            permissionRepository.save(permission);
            return new ResponseEntity<>("Permission has been successfully Added", HttpStatus.OK);
        } catch (Exception e) {
            LOG.info("Error: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> deletePermission(Long permissionId) {
        Optional<Permission> optionalPermission = permissionRepository.findById(permissionId);
        if (optionalPermission.isPresent()) {
            permissionRepository.delete(optionalPermission.get());
            return new ResponseEntity<>("Permission Successfully deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Permission against this Id", HttpStatus.OK);
        }
    }

    public ResponseEntity<Object> updatePermission(Permission permission) {
        Optional<Permission> optionalPermission = permissionRepository.findById(permission.getId());
        if (optionalPermission.isPresent()) {
            permissionRepository.save(permission);
            return new ResponseEntity<>("Permission Successfully updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Permission against this Id", HttpStatus.OK);
        }
    }
}
