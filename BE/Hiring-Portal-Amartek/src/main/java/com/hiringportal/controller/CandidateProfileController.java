package com.hiringportal.controller;

import com.hiringportal.dto.CandidateProfileRequest;
import com.hiringportal.dto.CandidateProfileResponse;
import com.hiringportal.dto.CustomResponse;
import com.hiringportal.entities.CandidateProfile;
import com.hiringportal.service.candidateProfile.CandidateProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/candidate-profiles")
@RequiredArgsConstructor
public class CandidateProfileController {

    private final CandidateProfileService candidateProfileService;

    @GetMapping
    public ResponseEntity<Object> getAllCandidateProfile() {
        List<CandidateProfileResponse> responses = candidateProfileService.getAll();

        return CustomResponse.generateResponse(
                "Data found with total amount : " + responses.size(),
                HttpStatus.OK,
                responses
        );
    }

    @GetMapping(value = "{candidateProfileId}")
    public ResponseEntity<Object> getCandidateProfileById(@PathVariable(name = "candidateProfileId") Integer candidateProfileId) {
        CandidateProfileResponse response = candidateProfileService.getById(candidateProfileId);

        return CustomResponse.generateResponse(
                "Data found",
                HttpStatus.OK,
                response
        );
    }

    @PostMapping
    public ResponseEntity<Object> postCandidateProfile(@RequestBody CandidateProfileRequest request) {
        CandidateProfile response = candidateProfileService.save(request);

        return CustomResponse.generateResponse(
                "Success save data",
                HttpStatus.OK,
                response
        );
    }

    @PutMapping(value = "{candidateProfileId}")
    public ResponseEntity<Object> updateCandidateProfile(
            @PathVariable(name = "candidateProfileId") Integer candidateProfileId,
            @RequestBody CandidateProfileRequest request
    ) {
        request.setId(candidateProfileId);

        CandidateProfile response = candidateProfileService.update(request);

        return CustomResponse.generateResponse(
                "Success update data",
                HttpStatus.OK,
                response
        );
    }

    @DeleteMapping(value = "{candidateProfileId}")
    public ResponseEntity<Object> deleteCandidateProfiel(@PathVariable(name = "candidateProfileId") Integer candidateProfileId){
        candidateProfileService.delete(candidateProfileId);

        return CustomResponse.generateResponse(
                "success delete data",
                HttpStatus.OK
        );
    }
}
