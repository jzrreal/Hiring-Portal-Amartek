package com.hiringportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hiringportal.entity.JobApplicationStatus;

public interface JobApplicationStatusRepository extends JpaRepository<JobApplicationStatus, Integer>{

}