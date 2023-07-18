package com.hiringportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hiringportal.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    
}
