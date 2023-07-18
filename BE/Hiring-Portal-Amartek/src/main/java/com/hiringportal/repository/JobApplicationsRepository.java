package com.hiringportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hiringportal.entity.JobApplications;

public interface JobApplicationsRepository extends JpaRepository<JobApplications, Integer>{

}