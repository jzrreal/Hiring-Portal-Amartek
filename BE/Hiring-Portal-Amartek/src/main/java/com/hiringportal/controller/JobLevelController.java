package com.hiringportal.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hiringportal.dto.CustomResponse;
import com.hiringportal.entities.JobLevel;
import com.hiringportal.service.jobLevel.JobLevelService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/job-levels")
public class JobLevelController {
    private final JobLevelService jobLevelService;

    @GetMapping("")
    public ResponseEntity<Object> get() {
        List<JobLevel> jobLevels = jobLevelService.getAll();
        return CustomResponse.generateResponse("Data found with total amount : " + jobLevels.size(), HttpStatus.OK,
                jobLevels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getId(@PathVariable(required = true) Integer id) {
        JobLevel jobLevel = jobLevelService.getById(id);
        return CustomResponse.generateResponse("Data Found", HttpStatus.OK, jobLevel);
    }

    @PostMapping("")
    public ResponseEntity<Object> post(@RequestBody JobLevel jobLevel) {
        jobLevelService.save(jobLevel);
        return CustomResponse.generateResponse("Success save data", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> put(@PathVariable(required = true) Integer id, @RequestBody JobLevel jobLevel) {
        jobLevel.setId(id);
        jobLevelService.update(jobLevel);
        return CustomResponse.generateResponse("Success update data", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(required = true) Integer id) {
        jobLevelService.delete(id);
        return CustomResponse.generateResponse("Success delete data", HttpStatus.OK);
    }
}
