package com.hiringportal.service.dashboard;

import com.hiringportal.dto.*;
import com.hiringportal.entities.JobPost;
import com.hiringportal.repository.JobApplicationRepository;
import com.hiringportal.repository.JobPostRepository;
import com.hiringportal.repository.QuestionRepository;
import com.hiringportal.utils.WordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final JobPostRepository jobPostRepository;
    private final JobApplicationRepository jobApplicationRepository;
    private final QuestionRepository questionRepository;

    @Override
    public DashboardIndexResponse getDashboardIndexData() {

        List<ApplicantApplyResponse> applicantsApplyResponses =
                jobApplicationRepository.findFirstFiveJobApplication(PageRequest.of(0, 5, Sort.Direction.DESC, "id"))
                        .stream().map(jobApplication -> ApplicantApplyResponse.builder()
                                .jobApplicationId(jobApplication.getId())
                                .applicantName(jobApplication.getCandidateProfile().getUser().getFullName())
                                .title(jobApplication.getJobPost().getTitle())
                                .applyDate(jobApplication.getApply_date())
                                .jobLevel(jobApplication.getJobPost().getJobLevel().getName())
                                .jobFunction(jobApplication.getJobPost().getJobFunction().getName())
                                .status(jobApplication.getApplicationStatus().getName())
                                .jobPostId(jobApplication.getJobPost().getId())
                                .build()).toList();

        Integer totalApplicationApply = jobApplicationRepository.getTotalApply();

        return DashboardIndexResponse.builder()
                .applicantsApplyResponses(applicantsApplyResponses)
                .totalApplicantsApply(totalApplicationApply)
                .jobPostResponses(getFiveLatestJobPosts())
                .totalJobPost(jobPostRepository.getTotalJobPost())
                .build();
    }

    @Override
    public DashboardIndexApplicantResponse getDashboardIndexDataApplicant(Integer candidateProfileId) {
        List<JobApplicationResponse> jobApplicationResponses =
                jobApplicationRepository.findFirstFiveJobApplicationByCandidateProfileId(
                        candidateProfileId, PageRequest.of(0, 5, Sort.Direction.DESC, "id"));


        return DashboardIndexApplicantResponse.builder()
                .totalApplyJob(jobApplicationResponses.size())
                .jobApplicationResponses(jobApplicationResponses)
                .totalJobPost(jobPostRepository.getTotalJobPost())
                .jobPostResponses(getFiveLatestJobPosts())
                .build();
    }

    @Override
    public DashboardIndexTrainerResponse getDashboardIndexDataTrainer() {
        Integer totalEasy = questionRepository.getTotalEasyQuestion();
        Integer totalMedium= questionRepository.getTotalMediumQuestion();
        Integer totalHard = questionRepository.getTotalHardQuestion();

        List<QuestionResponse> questionResponses =
                questionRepository.findFiveLatestQuestion(PageRequest.of(0, 5, Sort.Direction.DESC, "questionId"))
                        .stream().map(
                                questions -> QuestionResponse.builder()
                                        .id(questions.getQuestionId())
                                        .question(questions.getQuestion())
                                        .segment(WordUtil.capitalizeEachLetter(questions.getSegment().toString().replace("_", " ")))
                                        .createdAt(questions.getCreatedAt().toLocalDateTime())
                                        .questionLevel(questions.getQuestionLevel().getName())
                                        .build()
                        ).toList();

        return DashboardIndexTrainerResponse.builder()
                .totalEasyQuestions(totalEasy)
                .totalMediumQuestions(totalMedium)
                .totalHardQuestions(totalHard)
                .questionResponses(questionResponses)
                .build();
    }

    private List<JobPostResponse> getFiveLatestJobPosts() {
        List<JobPost> jobPosts =
                jobPostRepository.findAllFiveNewestJobPost(
                        PageRequest.of(0, 5, Sort.Direction.DESC, "id")
                );

        return jobPosts.stream().map(
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
    }
}
