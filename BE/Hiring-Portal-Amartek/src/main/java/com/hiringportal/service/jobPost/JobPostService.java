package com.hiringportal.service.jobPost;

import org.springframework.stereotype.Service;

import com.hiringportal.entities.JobPost;
import com.hiringportal.service.GenericService;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface JobPostService extends GenericService<JobPost, Integer> {
    @Transactional
    void schedulingForExpiredJobPost();
}
