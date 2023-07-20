package com.hiringportal.controller;

import com.hiringportal.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Object> login() {
        /**
         * TODO : create DTO for LoginRequest
         * TODO : create service to JWT service
         * TODO : response JWT token to user with message login success
         */
        return null;
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
    public ResponseEntity<Object> register() {
        return null;
    }

    /**
     * Logout
     * POST
     * localhost:port/api/auth/logout
     */
    @PostMapping(value = "logout")
    public ResponseEntity<Object> logout() {
        return null;
    }

    /**
     * verify email
     * POST
     * localhost:port/api/auth/verify-email?token
     */
    @PostMapping(value = "logout")
    public ResponseEntity<Object> verifyEmail(@RequestParam(name = "token") String token) {
        return null;
    }
}
