package com.hiringportal.service.dashboard;

import com.hiringportal.dto.ApplicantResponse;
import com.hiringportal.dto.DashboardIndexResponse;
import com.hiringportal.repository.CandidateProfileRepository;
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
}
