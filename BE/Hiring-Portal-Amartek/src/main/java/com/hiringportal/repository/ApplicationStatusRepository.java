package com.hiringportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hiringportal.entities.ApplicationStatus;

public interface ApplicationStatusRepository extends JpaRepository<ApplicationStatus, Integer>{

}