package com.hiringportal.controller;

import java.sql.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Object> post(@PathVariable(required = true) Integer jobId) {
        JobApplication jobApplication = JobApplication
            .builder()
            .apply_date(new Date(System.currentTimeMillis()))
            .jobPost(jobPostService.getById(jobId))
            .candidateProfile(jobApplicationService.getCandidateProfileById(1)) // Hardcode here, waiting for authentication
            .jobApplicationStatus(jobApplicationService.getJobApplicationStatusById(1)) // default, 1 = submitted
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
}
