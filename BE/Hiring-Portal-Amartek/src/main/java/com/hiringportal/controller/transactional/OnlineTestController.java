package com.hiringportal.controller.transactional;

import com.hiringportal.service.onlineTest.OnlineTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/online-tests")
@RequiredArgsConstructor
public class OnlineTestController {

    private final OnlineTestService onlineTestService;
}
