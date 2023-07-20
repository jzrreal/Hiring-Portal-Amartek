package com.hiringportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hiringportal.entities.JobFunction;

public interface JobFunctionRepository extends JpaRepository<JobFunction, Integer>{

}