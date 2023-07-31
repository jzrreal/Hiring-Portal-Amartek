package com.hiringportal.service.jobPost;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.hiringportal.entities.JobPost;
import com.hiringportal.repository.JobPostRepository;
import com.hiringportal.service.ValidationService;

import lombok.RequiredArgsConstructor;
@Slf4j
@Service
@RequiredArgsConstructor
public class JobPostServiceImpl implements JobPostService {
    private final JobPostRepository jobPostRepository;
    private final ValidationService validationService;

    @Override
    public List<JobPost> getAll() {
        return jobPostRepository.findAll();
    }

    @Override
    public JobPost getById(Integer id) {
        return jobPostRepository
            .findById(id)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job Post ID " + id + " not found")
            );
    }

    @Override
    public JobPost save(JobPost entity) {
        validationService.validate(entity);

        return jobPostRepository.save(entity);
    }

    @Override
    public JobPost update(JobPost entity) {
        validationService.validate(entity);

        return jobPostRepository.save(entity);
    }

    @Override
    public Boolean delete(Integer id) {
        jobPostRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job Post ID " + id + " not found")
                );

        jobPostRepository.deleteById(id);

        return jobPostRepository
            .findById(id)
            .isEmpty();
    }
    @Scheduled(fixedRate = 3600000) //Every one hour do this job
    @Override
    public void schedulingForExpiredJobPost() {
        log.info("Time : {}", LocalDateTime.now());

        LocalDateTime localDateTime = LocalDateTime.now();
        List<JobPost> jobPosts = jobPostRepository.findAllExpiredJobPost(
                Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant())
        );

        log.info("There job in job post with total : {}", jobPosts.size());

        if (jobPosts.isEmpty()) return;

        jobPosts.forEach(this::setJobPostToClosedAndSave);
    }

    private void setJobPostToClosedAndSave(JobPost jobPost){
        jobPost.setClosed(true);
        jobPostRepository.save(jobPost);
    }
}
