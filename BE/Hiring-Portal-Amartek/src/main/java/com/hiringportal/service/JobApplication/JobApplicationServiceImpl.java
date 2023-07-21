package com.hiringportal.service.JobApplication;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.hiringportal.entities.CandidateProfile;
import com.hiringportal.entities.JobApplication;
import com.hiringportal.entities.JobApplicationStatus;
import com.hiringportal.repository.CandidateProfileRepository;
import com.hiringportal.repository.JobApplicationRepository;
import com.hiringportal.repository.JobApplicationStatusRepository;
import com.hiringportal.service.ValidationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JobApplicationServiceImpl implements JobApplicationService {
    private final JobApplicationRepository jobApplicationRepository;
    private final CandidateProfileRepository candidateProfileRepository;
    private final JobApplicationStatusRepository jobApplicationStatusRepository;

    private final ValidationService validationService;

    @Override
    public List<JobApplication> getAll() {
        return jobApplicationRepository.findAll();
    }

    @Override
    public JobApplication getById(Integer id) {
        return jobApplicationRepository
            .findById(id)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job application Id : " + id + " not found")
            );
    }

    @Override
    public JobApplication save(JobApplication entity) {
        validationService.validate(entity);

        return jobApplicationRepository.save(entity);
    }

    @Override
    public JobApplication update(JobApplication entity) {
        validationService.validate(entity);

        return jobApplicationRepository.save(entity);
    }

    @Override
    public Boolean delete(Integer id) {
        jobApplicationRepository.deleteById(id);

        return jobApplicationRepository
            .findById(id)
            .isEmpty();
    }

    @Override
    public CandidateProfile getCandidateProfileById(Integer id) {
        return candidateProfileRepository
            .findById(id)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Applicant with Id : " + id + " not found")
            );
    }

    @Override
    public JobApplicationStatus getJobApplicationStatusById(Integer id) {
        return jobApplicationStatusRepository
            .findById(id)
            .orElseThrow(
                () -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Application status with Id : " + id + " not found")
            );
    }
    
}
