package com.hiringportal.controller;

import com.hiringportal.dto.*;
import com.hiringportal.entities.*;
import com.hiringportal.repository.CandidateProfileRepository;
import com.hiringportal.service.educationHistory.EducationHistoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.Year;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("api/education-history")
public class EducationHistoriesController {
    private final EducationHistoriesService educationHistoriesService;
    private final CandidateProfileRepository candidateProfileRepository;
    @GetMapping
    public ResponseEntity<Object> get() {
        List<EducationHistoriesResponse> educationHistoryList = educationHistoriesService.getEducationList();
        return CustomResponse.generateResponse("Data found with total amount : " + educationHistoryList.size(), HttpStatus.OK,
                educationHistoryList);
    }

    @GetMapping("/candidate/{candidateId}")
    public ResponseEntity<Object> getByCandidate(@PathVariable Integer candidateId ) {
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
    public ResponseEntity<Object> post(@RequestBody EducationHistoriesUpsert upsert){

        Integer upsertCandidateId = upsert.getCandidateId();
        CandidateProfile candidateProfile = candidateProfileRepository.findById(upsertCandidateId).orElse(null);

        Date yearStart = new Date(upsert.getYearStart() - 1900, 0, 1); // Format: year - 1900, month (0-11), day
        Date yearEnd = new Date(upsert.getYearEnd() - 1900, 0, 1); // Format: year - 1900, month (0-11), day
        EducationHistory educationHistory = new EducationHistory(
                upsert.getId(),
                upsert.getLevel(),
                upsert.getName(),
                upsert.getMajor(),
                yearStart,
                yearEnd,
                candidateProfile);
        educationHistoriesService.save(educationHistory);
        return CustomResponse.generateResponse("Success save", HttpStatus.OK);
    }
    @PutMapping("/{historiesId}")
    public ResponseEntity<Object> put(@Valid @RequestBody EducationHistoriesUpsert upsert, @PathVariable Integer historiesId) {
        Integer candidateId = upsert.getCandidateId();
        CandidateProfile candidateProfile = candidateProfileRepository.findById(candidateId).orElse(null);
        upsert.setId(historiesId);
        Date yearStart = new Date(upsert.getYearStart() - 1900, 0, 1); // Format: year - 1900, month (0-11), day
        Date yearEnd = new Date(upsert.getYearEnd() - 1900, 0, 1); // Format: year - 1900, month (0-11), day


        EducationHistory educationHistory = new EducationHistory(
                historiesId,
                upsert.getLevel(),
                upsert.getName(),
                upsert.getMajor(),
                yearStart,
                yearEnd,
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
