package com.hiringportal.controller.transactional;

import com.hiringportal.dto.CustomResponse;
import com.hiringportal.dto.DetailCandidateProfileResponse;
import com.hiringportal.entities.User;
import com.hiringportal.service.profile.ProfileService;
import com.hiringportal.utils.WordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "api/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

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

    @GetMapping("detail")
    public ResponseEntity<Object> getDetailProfileByToken(Authentication authentication){
        User user = (User) authentication.getPrincipal();

        if (user.getCandidateProfile() == null){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Not allowed");
        }

        DetailCandidateProfileResponse response = profileService.getDetailCandidateProfileById(user.getCandidateProfile().getId());

        return CustomResponse.generateResponse(
                "Data found",
                HttpStatus.OK,
                response
        );
    }
}
