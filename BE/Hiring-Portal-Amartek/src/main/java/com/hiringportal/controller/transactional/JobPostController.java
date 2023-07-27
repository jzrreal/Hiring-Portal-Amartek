package com.hiringportal.controller.transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.hiringportal.dto.JobPostDetailResponse;
import com.hiringportal.dto.JobPostResponse;
import com.hiringportal.entities.User;
import com.hiringportal.service.ValidationService;
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
    private final ValidationService validationService;

    // get all
    @GetMapping("")
    public ResponseEntity<Object> get() {
        List<JobPostResponse> responses =
                jobPostService.getAll().stream().map(
                        this::setJobPostToJobPostResponse
                ).toList();

        return CustomResponse.generateResponse(
                "Data found with total amount : " + responses.size(),
                HttpStatus.OK,
                responses
        );
    }

    // get id
    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable(required = true) Integer id) {
        JobPostDetailResponse response = setJobPostToJobPostDetailResponse(jobPostService.getById(id));
        return CustomResponse.generateResponse("Data found", HttpStatus.OK, response);
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
        validationService.validate(jobPostRequest);

        JobPost oldJobPost = jobPostService.getById(id);
        jobPostRequest.setUser_id(oldJobPost.getUser().getId());
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

    private JobPostDetailResponse setJobPostToJobPostDetailResponse(JobPost jobPost) {
        return JobPostDetailResponse.builder()
                .id(jobPost.getId())
                .title(jobPost.getTitle())
                .description(jobPost.getDescription())
                .requirements(jobPost.getRequirement())
                .jobLevel(jobPost.getJobLevel().getName())
                .jobFunction(jobPost.getJobFunction().getName())
                .postAt(jobPost.getPost_at())
                .openUntil(jobPost.getOpen_until())
                .updatedAt(jobPost.getUpdated_at())
                .vacancy(jobPost.getVacancy())
                .closed(jobPost.getClosed())
                .build();
    }

    private JobPostResponse setJobPostToJobPostResponse(JobPost jobPost) {
        return JobPostResponse.builder()
                .id(jobPost.getId())
                .title(jobPost.getTitle())
                .jobLevel(jobPost.getJobLevel().getName())
                .jobFunction(jobPost.getJobFunction().getName())
                .postAt(jobPost.getPost_at())
                .closed(jobPost.getClosed())
                .openUntil(jobPost.getOpen_until())
                .vacancy(jobPost.getVacancy())
                .build();
    }
}
