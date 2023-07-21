package com.hiringportal.service.auth;

import com.hiringportal.dto.LoginRequest;
import com.hiringportal.dto.RegisterRequest;
import org.springframework.transaction.annotation.Transactional;

public interface AuthService {

    //Register proceed email verification and return response message to check email
    @Transactional
    String register(RegisterRequest request);

    //Login proceed check email and password then return response token
    String login(LoginRequest request);

}
