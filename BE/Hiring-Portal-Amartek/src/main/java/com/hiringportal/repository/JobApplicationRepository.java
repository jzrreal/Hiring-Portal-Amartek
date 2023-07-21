package com.hiringportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hiringportal.entities.JobApplication;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Integer>{

}