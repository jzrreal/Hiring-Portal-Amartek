package com.hiringportal.controller;

import java.sql.Date;
import java.util.List;

import com.hiringportal.dto.CandidateProfileResponse;
import com.hiringportal.dto.GetApplicationByJobPostResponse;
import com.hiringportal.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.hiringportal.dto.CustomResponse;
import com.hiringportal.entities.JobApplication;
import com.hiringportal.service.JobApplication.JobApplicationService;
import com.hiringportal.service.jobPost.JobPostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/applications")
public class ApplicationController {
    private final JobApplicationService jobApplicationService;
    private final JobPostService jobPostService;

    // post
    @PostMapping("/{jobId}")
    public ResponseEntity<Object> post(@PathVariable(required = true) Integer jobId, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        JobApplication jobApplication = JobApplication
                .builder()
                .apply_date(new Date(System.currentTimeMillis()))
                .jobPost(jobPostService.getById(jobId))
                .candidateProfile(jobApplicationService.getCandidateProfileByEmail(user.getEmail()))
                .applicationStatus(jobApplicationService.getJobApplicationStatusById(1)) // default, 1 = submitted
                .build();
        jobApplicationService.save(jobApplication);
        return CustomResponse.generateResponse("Success save", HttpStatus.OK);
    }

    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(required = true) Integer id) {
        jobApplicationService.delete(id);
        return CustomResponse.generateResponse("Success delete", HttpStatus.OK);
    }

    /**
     * See all applicants on the job post
     */
    @GetMapping("{jobPostId}/job-post")
    public ResponseEntity<Object> getAllApplicantOnSpecificJobPost(
            @PathVariable(name = "jobPostId") Integer jobPostId
    ) {
        List<GetApplicationByJobPostResponse> responses = jobApplicationService.getApplicantsByJobPost(jobPostId);

        return CustomResponse.generateResponse(
                "Data found with total amount : " + responses.size(),
                HttpStatus.OK,
                responses
        );
    }

    @GetMapping("{jobApplicationId}/profiles")
    public ResponseEntity<Object> getCandidateProfileById(@PathVariable(name = "jobApplicationId") Integer jobApplicationId) {
        CandidateProfileResponse response = jobApplicationService.getProfileByJobApplicationId(jobApplicationId);

        return CustomResponse.generateResponse(
                "Data found",
                HttpStatus.OK,
                response
        );
    }
}
