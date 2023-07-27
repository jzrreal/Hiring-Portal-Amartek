package com.hiringportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hiringportal.entities.JobLevel;

public interface JobLevelRepository extends JpaRepository<JobLevel, Integer>{

}