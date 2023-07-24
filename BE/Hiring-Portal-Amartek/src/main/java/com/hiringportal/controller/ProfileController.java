package com.hiringportal.controller;

import com.hiringportal.dto.CustomResponse;
import com.hiringportal.entities.User;
import com.hiringportal.utils.WordUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "api/profiles")
public class ProfileController {

    @GetMapping
    public ResponseEntity<Object> getProfilesByToken(Authentication authentication) {

        User user = (User) authentication.getPrincipal();
        Map<String, String> response = new HashMap<>(Map.of(
                "full_name", user.getFullName(),
                "role", WordUtil.capitalizeEachLetter(user.getRole().getName())
        ));

        return CustomResponse.generateResponse(
                "Data found",
                HttpStatus.OK,
                response
        );
    }
}
