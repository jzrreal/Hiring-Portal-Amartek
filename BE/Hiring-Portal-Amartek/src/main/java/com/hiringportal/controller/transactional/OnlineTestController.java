package com.hiringportal.controller.transactional;

import com.hiringportal.service.onlineTest.OnlineTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/online-tests")
@RequiredArgsConstructor
public class OnlineTestController {
    private final OnlineTestService onlineTestService;

    @PostMapping("applicants/{candidateProfileId}")
    public ResponseEntity<Object> generateOnlineTest(
            @PathVariable(name = "candidateProfileId") Integer candidateProfileId
    ){
        return null;
    }
}
