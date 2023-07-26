package com.hiringportal.service.onlineTest;

import com.hiringportal.repository.QuestionRepository;
import com.hiringportal.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OnlineTestServiceImpl implements OnlineTestService{

    private final TestRepository testRepository;
    private final QuestionRepository questionRepository;
}
