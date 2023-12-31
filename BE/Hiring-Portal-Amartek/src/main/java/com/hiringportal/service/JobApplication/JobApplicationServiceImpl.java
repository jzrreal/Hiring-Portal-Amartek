package com.hiringportal.service.JobApplication;

import com.hiringportal.dto.*;
import com.hiringportal.entities.ApplicationStatus;
import com.hiringportal.entities.CandidateProfile;
import com.hiringportal.entities.EducationHistory;
import com.hiringportal.entities.JobApplication;
import com.hiringportal.enums.EducationLevel;
import com.hiringportal.repository.ApplicationStatusRepository;
import com.hiringportal.repository.CandidateProfileRepository;
import com.hiringportal.repository.EducationHistoryRepository;
import com.hiringportal.repository.JobApplicationRepository;
import com.hiringportal.service.ValidationService;
import com.hiringportal.utils.DateUtil;
import com.hiringportal.utils.WordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
                    Integer age = DateUtil.getAge(candidateProfile.getBirthDate());
                    return GetApplicationByJobPostResponse.builder()
                            .age(age)
                            .major(major)
                            .gender(candidateProfile.getGender())
                            .name(candidateProfile.getFullName())
                            .schoolName(schoolName)
                            .applyDate(jobApplication.getApply_date())
                            .jobApplicationId(jobApplication.getId())
                            .status(applicationStatus.get(jobApplication.getApplicationStatus().getId()))
                            .build();
                }).toList();

    }

    @Override
    public DetailCandidateProfileResponse getProfileByJobApplicationId(Integer jobApplicationId) {
        JobApplication jobApplication = jobApplicationRepository.findById(jobApplicationId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job Application with Id : " + jobApplicationId + " not found")
        );

        //If status submitted, when HR see applicant detail, the status change to reviewed
        if (jobApplication.getApplicationStatus().getId() == 1) {
            jobApplication.setApplicationStatus(applicationStatusRepository.findById(2).orElse(null));
            jobApplicationRepository.save(jobApplication);
        }

        CandidateProfile candidateProfile = candidateProfileRepository.findById(jobApplication.getCandidateProfile().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Applicant with Id : " + jobApplication.getCandidateProfile().getId() + " not found"));

        Map<EducationLevel, EducationHistory> educationHistoryMap =
                educationHistoryRepository.findAllByCandidateProfile_Id(candidateProfile.getId())
                        .stream().collect(Collectors.toMap(EducationHistory::getLevel, value -> value));

        return DetailCandidateProfileResponse.builder()
                .id(candidateProfile.getId())
                .age(DateUtil.getAge(candidateProfile.getBirthDate()))
                .lastEducation(WordUtil.getLastEducation(educationHistoryMap))
                .birthDate(candidateProfile.getBirthDate())
                .phone(candidateProfile.getPhone())
                .summary(candidateProfile.getSummary())
                .fullName(candidateProfile.getUser().getFullName())
                .email(candidateProfile.getUser().getEmail())
                .gender(WordUtil.capitalizeEachLetter(candidateProfile.getUser().getGender().toString().toLowerCase()))
                .build();
    }

    @Override
    public List<ApplicationHistoryResponse> getAllApplicationHistory(Integer candidateProfileId) {
        //Query all application status and transform into map with id as key
        Map<Integer, String> applicationStatus =
                applicationStatusRepository.findAll().stream()
                        .collect(Collectors.toMap(ApplicationStatus::getId, ApplicationStatus::getName));

        return jobApplicationRepository.findAllByCandidateProfileId(candidateProfileId).stream()
                .map(jobApplication -> ApplicationHistoryResponse.builder()
                        .jobFunction(jobApplication.getJobPost().getJobFunction().getName())
                        .jobLevel(jobApplication.getJobPost().getJobLevel().getName())
                        .title(jobApplication.getJobPost().getTitle())
                        .jobPostId(jobApplication.getJobPost().getId())
                        .status(applicationStatus.get(jobApplication.getApplicationStatus().getId()))
                        .jobApplicationId(jobApplication.getId())
                        .applyDate(jobApplication.getApply_date())
                        .build()
                ).toList();
    }
}
