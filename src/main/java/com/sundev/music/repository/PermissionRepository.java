package com.sundev.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sundev.music.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission,Long>{
    
}
