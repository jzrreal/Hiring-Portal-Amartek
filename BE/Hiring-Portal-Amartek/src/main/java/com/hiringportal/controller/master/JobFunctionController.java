package com.hiringportal.controller.master;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hiringportal.dto.CustomResponse;
import com.hiringportal.entities.JobFunction;
import com.hiringportal.service.jobFunction.JobFunctionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("api/job-functions")
public class JobFunctionController {
    private final JobFunctionService jobFunctionService;

    @GetMapping("")
    public ResponseEntity<Object> get() {
        List<JobFunction> jobFunctions =  jobFunctionService.getAll();
        return CustomResponse.generateResponse("Data found with total amount : " + jobFunctions.size(), HttpStatus.OK, jobFunctions);
    }

    //get by id
    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable(required = true) Integer id) {
        JobFunction jobFunction = jobFunctionService.getById(id);
        return CustomResponse.generateResponse("Data found", HttpStatus.OK, jobFunction);
    }
    // add role
    @PostMapping("")
    public ResponseEntity<Object> post(@RequestBody JobFunction jobFunction) {
        jobFunctionService.save(jobFunction);
        return CustomResponse.generateResponse("Success save data", HttpStatus.OK);
    }
    // update
    @PutMapping("/{id}")
    public ResponseEntity<Object> put(
        @PathVariable(required = true) Integer id,
        @RequestBody JobFunction jobFunction) {
        jobFunction.setId(id);
        jobFunctionService.update(jobFunction);
        return CustomResponse.generateResponse("Success update data", HttpStatus.OK);
    }
    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(required = true) Integer id) {
        jobFunctionService.delete(id);
        return CustomResponse.generateResponse("Success delete data", HttpStatus.OK);
    }
    
}
