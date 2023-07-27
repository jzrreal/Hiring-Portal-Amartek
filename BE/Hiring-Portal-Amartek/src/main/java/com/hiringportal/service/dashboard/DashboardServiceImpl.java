package com.hiringportal.service.dashboard;

import com.hiringportal.dto.*;
import com.hiringportal.entities.JobPost;
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
                .totalJobPost(jobPostRepository.getTotalJobPost())
                .build();
    }

    @Override
    public DashboardIndexApplicantResponse getDashboardIndexDataApplicant(Integer candidateProfileId) {
        List<JobApplicationResponse> jobApplicationResponses =
                jobApplicationRepository.findFirstFiveJobApplicationByCandidateProfileId(
                        candidateProfileId, PageRequest.of(0, 5, Sort.Direction.DESC, "id"));

        List<JobPost> jobPosts =
                jobPostRepository.findAllFiveNewestJobPost(
                        PageRequest.of(0, 5, Sort.Direction.DESC, "id")
                );

        List<JobPostResponse> jobPostResponses = jobPosts.stream().map(
                jobPost -> JobPostResponse.builder()
                        .id(jobPost.getId())
                        .title(jobPost.getTitle())
                        .jobLevel(jobPost.getJobLevel().getName())
                        .jobFunction(jobPost.getJobFunction().getName())
                        .postAt(jobPost.getPost_at())
                        .closed(jobPost.getClosed())
                        .openUntil(jobPost.getOpen_until())
                        .build()
        ).toList();

        return DashboardIndexApplicantResponse.builder()
                .totalApplyJob(jobApplicationResponses.size())
                .jobApplicationResponses(jobApplicationResponses)
                .totalJobPost(jobPostRepository.getTotalJobPost())
                .jobPostResponses(jobPostResponses)
                .build();
    }
}
