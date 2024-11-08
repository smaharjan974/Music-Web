package com.sundev.music.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sundev.music.model.Permission;
import com.sundev.music.repository.PermissionRepository;

@Service
public class PermissionService {
    
@Autowired
    private PermissionRepository permissionRepository;

    // Create or update a permission
    public Permission saveOrUpdatePermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    // Get all permissions
    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    // Get permission by ID
    public Optional<Permission> getPermissionById(Long id) {
        return permissionRepository.findById(id);
    }

    // Delete permission
    public void deletePermission(Long id) {
        permissionRepository.deleteById(id);
    }

}
