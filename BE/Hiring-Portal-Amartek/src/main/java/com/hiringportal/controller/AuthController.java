package com.hiringportal.controller;

import com.hiringportal.dto.CustomResponse;
import com.hiringportal.dto.LoginRequest;
import com.hiringportal.dto.RegisterRequest;
import com.hiringportal.dto.ResendVerificationRequest;
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
     * GET
     * localhost:port/api/auth/verify-email?token
     */
    @GetMapping(value = "verify-email")
    public ResponseEntity<Object> verifyEmail(
            @RequestParam(name = "token") String token
    ) {
        String response = authService.verifyEmail(token);

        return CustomResponse.generateResponse(
                response,
                HttpStatus.OK
        );
    }

    /**
     * resend email verification
     * POST
     * localhost:port/api/auth/resend-verification
     * payload
     * - email
     * response message success, check your email
     */
    @PostMapping(value = "resend-verification")
    public ResponseEntity<Object> resendVerification(
            @RequestBody ResendVerificationRequest request
    ){
        String response = authService.resendVerification(request);

        return CustomResponse.generateResponse(
                response,
                HttpStatus.OK
        );
    }
}
