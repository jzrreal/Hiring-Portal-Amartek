package com.hiringportal.controller;

import com.hiringportal.dto.CustomResponse;
import com.hiringportal.dto.LoginRequest;
import com.hiringportal.dto.RegisterRequest;
import com.hiringportal.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * Login
     * POST
     * localhost:port/api/auth/login
     * payload
     * - email
     * - password
     */
    @PostMapping(value = "login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest request) {
        String token = authService.login(request);
        /**
         * TODO : create service to JWT service
         * TODO : response JWT token to user with message login success
         */
        return CustomResponse.generateResponse(
                "Success login",
                HttpStatus.OK,
                token
        );
    }

    /**
     * Register
     * POST
     * localhost:port/api/auth/register
     * payload
     * - email
     * - password
     * - full_name
     */
    @PostMapping(value = "register")
    public ResponseEntity<Object> register(@RequestBody RegisterRequest request) {
        String message = authService.register(request);

        return CustomResponse.generateResponse(
                message,
                HttpStatus.OK
        );
    }

    /**
     * verify email
     * POST
     * localhost:port/api/auth/verify-email?token
     */
    @PostMapping(value = "verify-email")
    public ResponseEntity<Object> verifyEmail() {
        return null;
    }
}
