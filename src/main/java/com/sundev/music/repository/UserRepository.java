package com.sundev.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sundev.music.model.Users;

public interface UserRepository extends JpaRepository<Users,Long> {
    Users findByUsername(String username);
}
