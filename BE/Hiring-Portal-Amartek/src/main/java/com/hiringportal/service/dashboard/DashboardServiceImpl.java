package com.hiringportal.service.dashboard;

import com.hiringportal.dto.ApplicantResponse;
import com.hiringportal.dto.DashboardIndexApplicantResponse;
import com.hiringportal.dto.DashboardIndexResponse;
import com.hiringportal.dto.JobApplicationResponse;
import com.hiringportal.repository.CandidateProfileRepository;
import com.hiringportal.repository.JobApplicationRepository;
import com.hiringportal.repository.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final JobPostRepository jobPostRepository;
    private final CandidateProfileRepository candidateProfileRepository;
    private final JobApplicationRepository jobApplicationRepository;

    @Override
    public DashboardIndexResponse getDashboardIndexData() {
        List<ApplicantResponse> applicantResponses = candidateProfileRepository.findFirstFiveApplicant(
                PageRequest.of(0, 5, Sort.Direction.DESC, "id"));

        return DashboardIndexResponse.builder()
                .applicantResponses(applicantResponses)
                .totalApplicants(applicantResponses.size())
                .totalJobPost(jobPostRepository.findAll().size())
                .build();
    }

    @Override
    public DashboardIndexApplicantResponse getDashboardIndexDataApplicant(Integer candidateProfileId) {
        List<JobApplicationResponse> jobApplicationResponses =
                jobApplicationRepository.findFirstFiveJobApplicationByCandidateProfileId(
                        candidateProfileId, PageRequest.of(0, 1, Sort.Direction.DESC, "id"));

        return DashboardIndexApplicantResponse.builder()
                .totalApplyJob(jobApplicationResponses.size())
                .jobApplicationResponses(jobApplicationResponses)
                .build();
    }
}
