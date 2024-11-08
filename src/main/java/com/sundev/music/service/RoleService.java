package com.sundev.music.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sundev.music.model.Role;
import com.sundev.music.repository.RoleRepository;

@Service
public class RoleService {
    
     @Autowired
    private RoleRepository roleRepository;

    // Create or update a role
    public Role saveOrUpdateRole(Role role) {
        return roleRepository.save(role);
    }

    // Get all roles
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    // Get role by ID
    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    // Delete role
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

}
