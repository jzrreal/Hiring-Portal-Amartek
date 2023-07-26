package com.hiringportal.service.onlineTest;

import com.hiringportal.dto.QuestionTestResponse;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OnlineTestService {
    @Transactional
    void generateTestForCandidateProfile(Integer jobApplicationId);

    List<QuestionTestResponse> getAllQuestionTestByToken(String token);
}
