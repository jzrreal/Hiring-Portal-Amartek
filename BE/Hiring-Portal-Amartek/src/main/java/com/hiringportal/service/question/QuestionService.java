package com.hiringportal.service.question;

import com.hiringportal.dto.QuestionDetailResponse;
import com.hiringportal.dto.QuestionRequest;
import com.hiringportal.dto.QuestionResponse;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface QuestionService {

    List<QuestionResponse> getAllQuestion();

    QuestionDetailResponse getQuestionById(Integer questionId);

    @Transactional
    QuestionDetailResponse saveQuestionAndChoice(QuestionRequest request);

    @Transactional
    QuestionDetailResponse updateQuestionAndChoice(QuestionRequest request);

    @Transactional
    void deleteQuestionAndChoice(Integer questionId);
}
