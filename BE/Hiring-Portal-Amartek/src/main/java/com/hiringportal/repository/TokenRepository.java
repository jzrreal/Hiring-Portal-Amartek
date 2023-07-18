package com.hiringportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hiringportal.entities.Token;

public interface TokenRepository extends JpaRepository<Token, Integer> {
    
}
