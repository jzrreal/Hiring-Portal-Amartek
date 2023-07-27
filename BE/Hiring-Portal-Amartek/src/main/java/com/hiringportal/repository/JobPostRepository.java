package com.hiringportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hiringportal.entities.JobPost;

public interface JobPostRepository extends JpaRepository<JobPost, Integer> {

}