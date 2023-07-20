package com.hiringportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hiringportal.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> { ;
}
