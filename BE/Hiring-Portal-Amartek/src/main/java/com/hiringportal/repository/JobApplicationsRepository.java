package com.hiringportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hiringportal.entities.JobApplications;

public interface JobApplicationsRepository extends JpaRepository<JobApplications, Integer>{

}