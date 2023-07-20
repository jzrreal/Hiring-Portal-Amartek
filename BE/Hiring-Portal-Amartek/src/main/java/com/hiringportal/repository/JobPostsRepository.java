package com.hiringportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hiringportal.entities.JobPosts;

public interface JobPostsRepository extends JpaRepository<JobPosts, Integer> {

}