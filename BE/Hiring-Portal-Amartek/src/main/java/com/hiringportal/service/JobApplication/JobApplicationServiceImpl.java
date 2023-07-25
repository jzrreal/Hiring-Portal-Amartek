package com.hiringportal.service.JobApplication;

import java.util.List;

import com.hiringportal.repository.EducationHistoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.hiringportal.entities.CandidateProfile;
import com.hiringportal.entities.JobApplication;
import com.hiringportal.entities.ApplicationStatus;
import com.hiringportal.repository.CandidateProfileRepository;
import com.hiringportal.repository.JobApplicationRepository;
import com.hiringportal.repository.ApplicationStatusRepository;
import com.hiringportal.service.ValidationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JobApplicationServiceImpl implements JobApplicationService {
    private final JobApplicationRepository jobApplicationRepository;
    private final CandidateProfileRepository candidateProfileRepository;
    private final ApplicationStatusRepository applicationStatusRepository;
    private final EducationHistoryRepository educationHistoryRepository;

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
    public ApplicationStatus getJobApplicationStatusById(Integer id) {
        return applicationStatusRepository
            .findById(id)
            .orElseThrow(
                () -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Application status with Id : " + id + " not found")
            );
    }

    @Override
    public CandidateProfile getCandidateProfileByEmail(String email) {
        return candidateProfileRepository.findCandidateProfileByUser_Email(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Applicant not found"));
    }

    @Override
    public void getApplicantsByJobPost(Integer jobPostId) {

    }
}
