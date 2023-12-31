package com.hiringportal.service.onlineTest;

import com.hiringportal.dto.QuestionTestResponse;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface OnlineTestService {
    @Transactional
    void generateTestForCandidateProfile(Integer jobApplicationId);

    List<QuestionTestResponse> getAllQuestionTestByToken(String token);
    void saveEachOnlineTestAnswer(Integer testQuestionId, Integer questionId, Integer choiceId);
    LocalDateTime getEndTestByToken(String token);
    @Transactional
    void saveAllAnswerAndGetResult(String token);
    @Transactional
    void schedulingForExpiredTestToGetResult();
}
