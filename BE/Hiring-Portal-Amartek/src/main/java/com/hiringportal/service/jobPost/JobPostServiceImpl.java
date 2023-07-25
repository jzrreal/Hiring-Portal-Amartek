package com.hiringportal.service.jobPost;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.hiringportal.entities.JobPost;
import com.hiringportal.repository.JobPostRepository;
import com.hiringportal.service.ValidationService;

import lombok.RequiredArgsConstructor;

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
    
}
