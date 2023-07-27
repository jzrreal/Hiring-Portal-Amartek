package com.hiringportal.controller.transactional;

import com.hiringportal.dto.CustomResponse;
import com.hiringportal.dto.EducationHistoriesResponse;
import com.hiringportal.dto.EducationHistoriesUpsert;
import com.hiringportal.entities.CandidateProfile;
import com.hiringportal.entities.EducationHistory;
import com.hiringportal.entities.User;
import com.hiringportal.repository.CandidateProfileRepository;
import com.hiringportal.service.ValidationService;
import com.hiringportal.service.educationHistory.EducationHistoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("api/education-histories")
public class EducationHistoriesController {
    private final EducationHistoriesService educationHistoriesService;
    private final CandidateProfileRepository candidateProfileRepository;
    private final ValidationService validationService;

    @GetMapping
    public ResponseEntity<Object> get() {
        List<EducationHistoriesResponse> educationHistoryList = educationHistoriesService.getEducationList();
        return CustomResponse.generateResponse("Data found with total amount : " + educationHistoryList.size(), HttpStatus.OK,
                educationHistoryList);
    }

    @GetMapping("/applicants/{candidateId}")
    public ResponseEntity<Object> getByCandidate(@PathVariable Integer candidateId) {
        candidateProfileRepository.findById(candidateId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Applicants with Id " + candidateId + " not found"));
        List<EducationHistoriesResponse> educationHistoryList = educationHistoriesService.getEducationListCandidate(candidateId);
        return CustomResponse.generateResponse("Data found with total amount : " + educationHistoryList.size(), HttpStatus.OK,
                educationHistoryList);
    }

    @GetMapping("{historiesId}")
    public ResponseEntity<Object> get(@PathVariable(required = true) Integer historiesId) {
        EducationHistory educationHistory = educationHistoriesService.getById(historiesId);
        return CustomResponse.generateResponse("Data found", HttpStatus.OK, educationHistory);
    }

    @PostMapping()
    public ResponseEntity<Object> post(@RequestBody EducationHistoriesUpsert upsert, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        validationService.validate(upsert);

        Integer upsertCandidateId = user.getCandidateProfile().getId();
        CandidateProfile candidateProfile = candidateProfileRepository.findById(upsertCandidateId).orElse(null);

        Date yearStart = new Date(upsert.getYearStart() - 1900, 0, 1); // Format: year - 1900, month (0-11), day
        Date yearEnd = new Date(upsert.getYearEnd() - 1900, 0, 1); // Format: year - 1900, month (0-11), day

        EducationHistory educationHistory = new EducationHistory(
                upsert.getId(),
                upsert.getLevel(),
                upsert.getName(),
                upsert.getMajor(),
//                yearStart,
//                yearEnd,
                upsert.getYearStart(),
                upsert.getYearEnd(),
                candidateProfile);
        educationHistoriesService.save(educationHistory);
        return CustomResponse.generateResponse("Success save", HttpStatus.OK);
    }

    @PutMapping("/{historiesId}")
    public ResponseEntity<Object> put(@Valid @RequestBody EducationHistoriesUpsert upsert,
                                      @PathVariable Integer historiesId,
                                      Authentication authentication
    ) {
        User user = (User) authentication.getPrincipal();

        validationService.validate(upsert);

        Integer candidateId = user.getCandidateProfile().getId();

        CandidateProfile candidateProfile = candidateProfileRepository.findById(candidateId).orElse(null);

        educationHistoriesService.getById(historiesId);

        upsert.setId(historiesId);
        EducationHistory educationHistory = new EducationHistory(
                historiesId,
                upsert.getLevel(),
                upsert.getName(),
                upsert.getMajor(),
                upsert.getYearStart(),
                upsert.getYearEnd(),
                candidateProfile);
        educationHistoriesService.update(educationHistory);
        return CustomResponse.generateResponse("Success update", HttpStatus.OK);
    }

    @DeleteMapping("/{historiesId}")
    public ResponseEntity<Object> delete(@PathVariable(required = true) Integer historiesId) {
        educationHistoriesService.delete(historiesId);
        return CustomResponse.generateResponse("Success delete data", HttpStatus.OK);
    }
}
