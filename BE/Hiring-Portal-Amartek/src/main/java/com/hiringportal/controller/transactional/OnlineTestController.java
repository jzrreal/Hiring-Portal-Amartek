package com.hiringportal.controller.transactional;

import com.hiringportal.dto.CustomResponse;
import com.hiringportal.dto.QuestionTestResponse;
import com.hiringportal.service.onlineTest.OnlineTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        List<QuestionTestResponse> responses = onlineTestService.getAllQuestionTestByToken(token);

        return CustomResponse.generateResponse(
                "Your token : " + token,
                HttpStatus.OK,
                responses
        );
    }
}
