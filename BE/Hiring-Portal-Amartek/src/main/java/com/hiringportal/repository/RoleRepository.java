package com.hiringportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hiringportal.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    
}
