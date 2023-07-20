package com.hiringportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hiringportal.entities.JobApplicationStatus;

public interface JobApplicationStatusRepository extends JpaRepository<JobApplicationStatus, Integer>{

}