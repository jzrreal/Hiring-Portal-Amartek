package com.hiringportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hiringportal.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    
}
