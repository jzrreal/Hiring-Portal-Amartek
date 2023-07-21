package com.hiringportal.service.JobApplication;

import org.springframework.stereotype.Service;

import com.hiringportal.entities.CandidateProfile;
import com.hiringportal.entities.JobApplication;
import com.hiringportal.entities.JobApplicationStatus;
import com.hiringportal.service.GenericService;

@Service
public interface JobApplicationService extends GenericService<JobApplication, Integer> {
    public CandidateProfile getCandidateProfileById(Integer id);
    public JobApplicationStatus getJobApplicationStatusById(Integer id);
}
