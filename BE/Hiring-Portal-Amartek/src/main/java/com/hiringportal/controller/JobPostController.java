package com.hiringportal.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hiringportal.dto.CustomResponse;
import com.hiringportal.dto.JobPostRequest;
import com.hiringportal.entities.JobPost;
import com.hiringportal.entities.User;
import com.hiringportal.service.jobFunction.JobFunctionService;
import com.hiringportal.service.jobLevel.JobLevelService;
import com.hiringportal.service.jobPost.JobPostService;
import com.hiringportal.service.user.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/job-posts")
public class JobPostController {
    private final JobPostService jobPostService;
    private final JobLevelService jobLevelService;
    private final JobFunctionService jobFunctionService;
    private final UserService userService;

    // get all
    @GetMapping("")
    public ResponseEntity<Object> get() {
        List<JobPost> jobPosts = jobPostService.getAll();

        return CustomResponse.generateResponse("Data found with total amount : " + jobPosts.size(), HttpStatus.OK, jobPosts);
    }
    // get id
    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable(required = true) Integer id) {
        JobPost jobPost = jobPostService.getById(id);
        return CustomResponse.generateResponse("Data found", HttpStatus.OK, jobPost);
    }
    // add
    @PostMapping("")
    public ResponseEntity<Object> post(@RequestBody JobPostRequest jobPostRequest, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        jobPostRequest.setUser_id(user.getId());
        JobPost newJobPost = jobPostProcess(jobPostRequest);
        newJobPost.setPost_at(new Date(System.currentTimeMillis()));
        jobPostService.save(newJobPost);
        return CustomResponse.generateResponse("Success save", HttpStatus.OK);
    }
    // update
    @PutMapping("/{id}")
    public ResponseEntity<Object> put(
        @PathVariable(required = true) Integer id,
        @RequestBody JobPostRequest jobPostRequest
    ) {
        JobPost oldJobPost = jobPostService.getById(id);
        JobPost newJobPost = jobPostProcess(jobPostRequest);
        newJobPost.setId(oldJobPost.getId());
        newJobPost.setPost_at(oldJobPost.getPost_at());
        jobPostService.save(newJobPost);
        return CustomResponse.generateResponse("Success edit", HttpStatus.OK);
    }
    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(required = true) Integer id) {
        jobPostService.delete(id);
        return CustomResponse.generateResponse("Success delete", HttpStatus.OK);
    }

    // save or update proccess
    private JobPost jobPostProcess(JobPostRequest jobPostRequest) {
        JobPost newJobPost = JobPost
            .builder()
            .title(jobPostRequest.getTitle())
            .description(jobPostRequest.getDescription())
            .requirement(jobPostRequest.getRequirements())
            .jobLevel(jobLevelService.getById(jobPostRequest.getJob_level_id()))
            .jobFunction(jobFunctionService.getById(jobPostRequest.getJob_function_id()))
            .open_until(jobPostRequest.getOpen_until())
            .updated_at(new Date(System.currentTimeMillis()))
            .vacancy(jobPostRequest.getVacancy())
            .closed(jobPostRequest.getClosed())
            .user(userService.getById(jobPostRequest.getUser_id()))
            .build();
        return newJobPost;
    }
}
