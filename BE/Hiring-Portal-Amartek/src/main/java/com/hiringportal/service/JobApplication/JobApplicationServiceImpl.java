package com.hiringportal.service.JobApplication;

import com.hiringportal.dto.CandidateProfileQuery;
import com.hiringportal.dto.EducationHistoryQuery;
import com.hiringportal.dto.GetApplicationByJobPostResponse;
import com.hiringportal.entities.ApplicationStatus;
import com.hiringportal.entities.CandidateProfile;
import com.hiringportal.entities.JobApplication;
import com.hiringportal.repository.ApplicationStatusRepository;
import com.hiringportal.repository.CandidateProfileRepository;
import com.hiringportal.repository.EducationHistoryRepository;
import com.hiringportal.repository.JobApplicationRepository;
import com.hiringportal.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public List<GetApplicationByJobPostResponse> getApplicantsByJobPost(Integer jobPostId) {
        //Query all application status and transform into map with id as key
        Map<Integer, String> applicationStatus =
                applicationStatusRepository.findAll().stream()
                        .collect(Collectors.toMap(ApplicationStatus::getId, ApplicationStatus::getName));

        //Find all job application by jobPostId
        List<JobApplication> jobApplications = jobApplicationRepository.findAllByJobPostId(jobPostId);

        //Get all candidate profile and store its id to list
        List<Integer> idCandidateProfiles =
                jobApplications.stream().map(jobApplication -> jobApplication.getCandidateProfile().getId()).toList();

        //Get all candidate profile by list of id candidate and transform into map with id as key
        Map<Integer, CandidateProfileQuery> candidateProfileMap =
                candidateProfileRepository.findAllCandidateProfileInListId(idCandidateProfiles)
                        .stream().collect(Collectors.toMap(CandidateProfileQuery::getId, value -> value));

        /**
         * get all candidate profile education history by list candidate id where level S1
         * do stream to transform list to map with key candidate profile id
         */

        Map<Integer, EducationHistoryQuery> educationHistoryMap =
                educationHistoryRepository.findAllEducationHistoryInListCandidateIdWhereS1(idCandidateProfiles)
                        .stream().collect(Collectors.toMap(EducationHistoryQuery::getCandidateProfileId, value -> value));


        return jobApplications.stream()
                .map(jobApplication -> {
                    CandidateProfileQuery candidateProfile = candidateProfileMap.get(jobApplication.getCandidateProfile().getId());
                    EducationHistoryQuery educationHistoryQuery = educationHistoryMap.get(jobApplication.getCandidateProfile().getId());
                    String major = educationHistoryQuery != null ? educationHistoryQuery.getMajor() : null;
                    String schoolName = educationHistoryQuery != null ? educationHistoryQuery.getName() : null;
                    LocalDate localDate = Instant.ofEpochMilli(candidateProfile.getBirthDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
                    return GetApplicationByJobPostResponse.builder()
                            .age(Period.between(localDate, LocalDate.now()).getYears())
                            .major(major)
                            .gender(candidateProfile.getGender())
                            .name(candidateProfile.getFullName())
                            .schoolName(schoolName)
                            .applyDate(jobApplication.getApply_date())
                            .id(jobApplication.getCandidateProfile().getId())
                            .status(applicationStatus.get(jobApplication.getApplicationStatus().getId()))
                            .build();
                }).toList();

    }
}
