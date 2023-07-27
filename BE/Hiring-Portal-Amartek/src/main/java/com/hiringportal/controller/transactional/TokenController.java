package com.hiringportal.controller.transactional;

import com.hiringportal.dto.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/tokens")
public class TokenController {

    @GetMapping
    public ResponseEntity<Object> checkToken() {
        return CustomResponse.generateResponse(
                "Valid",
                HttpStatus.OK
        );
    }
}
