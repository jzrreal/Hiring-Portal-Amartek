package com.hiringportal.service.jobPost;

import com.hiringportal.dto.JobPostChartResponse;
import org.springframework.stereotype.Service;

import com.hiringportal.entities.JobPost;
import com.hiringportal.service.GenericService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface JobPostService extends GenericService<JobPost, Integer> {
    @Transactional
    void schedulingForExpiredJobPost();

    List<JobPostChartResponse> getChartJobPostByJobFunction();
}
