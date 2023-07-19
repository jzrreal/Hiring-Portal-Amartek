package com.hiringportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hiringportal.entities.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {
    
}
