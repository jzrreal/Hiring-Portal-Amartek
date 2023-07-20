package com.hiringportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hiringportal.entities.JobApplication;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Integer>{

}