package com.hiringportal.service.jobPost;

import org.springframework.stereotype.Service;

import com.hiringportal.entities.JobPost;
import com.hiringportal.service.GenericService;

@Service
public interface JobPostService extends GenericService<JobPost, Integer> {
    
}
