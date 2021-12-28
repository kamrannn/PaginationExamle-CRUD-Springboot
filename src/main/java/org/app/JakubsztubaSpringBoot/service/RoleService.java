package org.app.JakubsztubaSpringBoot.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.app.JakubsztubaSpringBoot.model.Role;
import org.app.JakubsztubaSpringBoot.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    private static final Logger LOG = LogManager.getLogger(RoleService.class);
    RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public ResponseEntity<Object> listAllRoles() {
        try {
            List<Role> roleList = roleRepository.findAll();
            if (roleList.isEmpty()) {
                return new ResponseEntity<>("There are no roles in the Database", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(roleList, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.info("Error: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> addNewRole(Role role) {
        roleRepository.save(role);
        return new ResponseEntity<>("Role added", HttpStatus.CREATED);
    }

    public ResponseEntity<Object> deleteRole(Long roleId) {
        Optional<Role> optionalRole = roleRepository.findById(roleId);
        if (optionalRole.isPresent()) {
            roleRepository.delete(optionalRole.get());
            return new ResponseEntity<>("Role Successfully deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No role against this Id", HttpStatus.OK);
        }
    }

    public ResponseEntity<Object> updateRole(Role role) {
        Optional<Role> optionalRole = roleRepository.findById(role.getId());
        if (optionalRole.isPresent()) {
            roleRepository.save(role);
            return new ResponseEntity<>("Role Successfully updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No role against this Id", HttpStatus.OK);
        }
    }
}