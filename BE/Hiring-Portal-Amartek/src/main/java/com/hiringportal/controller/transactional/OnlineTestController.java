package com.hiringportal.controller.transactional;

import com.hiringportal.dto.CustomResponse;
import com.hiringportal.dto.QuestionTestResponse;
import com.hiringportal.service.onlineTest.OnlineTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/online-tests")
@RequiredArgsConstructor
public class OnlineTestController {
    private final OnlineTestService onlineTestService;

    @PostMapping("applications/{jobApplicationId}")
    public ResponseEntity<Object> generateOnlineTest(
            @PathVariable(name = "jobApplicationId") Integer jobApplicationId
    ) {
        onlineTestService.generateTestForCandidateProfile(jobApplicationId);
        return CustomResponse.generateResponse(
                "Success generate test",
                HttpStatus.OK
        );
    }

    @GetMapping()
    public ResponseEntity<Object> getOnlineTest(
            @RequestParam(name = "token") String token
    ) {
        List<QuestionTestResponse> questionTestResponses = onlineTestService.getAllQuestionTestByToken(token);

        Map<String, Object> responses = new HashMap<>();
        responses.put("finish_at", onlineTestService.getEndTestByToken(token));
        responses.put("data_questions", questionTestResponses);

        return CustomResponse.generateResponse(
                "Your token : " + token,
                HttpStatus.OK,
                responses
        );
    }

    @PutMapping("test-questions/{testQuestionId}/questions/{questionId}/choices/{choiceId}")
    public ResponseEntity<Object> updateAnswer(
            @PathVariable(name = "testQuestionId") Integer testQuestionId,
            @PathVariable(name = "questionId") Integer questionId,
            @PathVariable(name = "choiceId") Integer choiceId
    ) {
        onlineTestService.saveEachOnlineTestAnswer(testQuestionId, questionId, choiceId);

        return CustomResponse.generateResponse(
                "Success save answer",
                HttpStatus.OK
        );
    }

    @PutMapping("{token}")
    public ResponseEntity<Object> finishTestAndGetResult(
            @PathVariable(name = "token") String token
    ) {
        onlineTestService.saveAllAnswerAndGetResult(token);

        return CustomResponse.generateResponse(
                "Success finish test",
                HttpStatus.OK
        );
    }
}
