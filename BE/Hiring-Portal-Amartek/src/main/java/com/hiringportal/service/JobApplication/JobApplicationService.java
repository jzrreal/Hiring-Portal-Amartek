package com.hiringportal.service.JobApplication;

import com.hiringportal.dto.GetApplicationByJobPostResponse;
import org.springframework.stereotype.Service;

import com.hiringportal.entities.CandidateProfile;
import com.hiringportal.entities.JobApplication;
import com.hiringportal.entities.ApplicationStatus;
import com.hiringportal.service.GenericService;

import java.util.List;

@Service
public interface JobApplicationService extends GenericService<JobApplication, Integer> {
    public CandidateProfile getCandidateProfileById(Integer id);
    public ApplicationStatus getJobApplicationStatusById(Integer id);
    CandidateProfile getCandidateProfileByEmail(String email);

    List<GetApplicationByJobPostResponse> getApplicantsByJobPost(Integer jobPostId);
}
