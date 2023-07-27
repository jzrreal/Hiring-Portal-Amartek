package com.hiringportal.controller;

import com.hiringportal.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/test")
public class TestingController {

    @GetMapping
    public ResponseEntity<String> testing(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        String response = "Hallo gan " + user.getFullName() + " dengan id : " + user.getId();
        return ResponseEntity.ok(response);
    }
}
