package com.hiringportal.controller;

import com.hiringportal.dto.CustomResponse;
import com.hiringportal.entities.ApplicationStatus;
import com.hiringportal.service.applicationStatus.ApplicationStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "application-status")
@RequiredArgsConstructor
public class ApplicationStatusController {
    private final ApplicationStatusService applicationStatusService;

    @GetMapping
    public ResponseEntity<Object> getAll() {
        List<ApplicationStatus> response = applicationStatusService.getAll();

        return CustomResponse.generateResponse(
                "Data found with total amount : " + response.size(),
                HttpStatus.OK,
                response
        );
    }

    @GetMapping(value = "{applicationStatusId}")
    public ResponseEntity<Object> getById(@PathVariable(name = "applicationStatusId") Integer applicationStatusId) {
        ApplicationStatus response = applicationStatusService.getById(applicationStatusId);

        return CustomResponse.generateResponse(
                "Data found",
                HttpStatus.OK,
                response
        );
    }

    @PostMapping
    public ResponseEntity<Object> postData(@RequestBody ApplicationStatus request) {
        ApplicationStatus response = applicationStatusService.save(request);

        return CustomResponse.generateResponse(
                "Success save data",
                HttpStatus.OK,
                response
        );
    }

    @PutMapping(value = "{applicationStatusId}")
    public ResponseEntity<Object> updateData(
            @RequestBody ApplicationStatus request,
            @PathVariable(name = "applicationStatusId") Integer applicationStatusId
    ) {
        request.setId(applicationStatusId);

        ApplicationStatus response = applicationStatusService.update(request);

        return CustomResponse.generateResponse(
                "Success update data",
                HttpStatus.OK,
                response
        );
    }

    @DeleteMapping(value = "{applicationStatusId}")
    public ResponseEntity<Object> deleteData(@PathVariable(name = "applicationStatusId") Integer applicationStatusId) {
        applicationStatusService.delete(applicationStatusId);

        return CustomResponse.generateResponse(
                "Success delete data",
                HttpStatus.OK
        );
    }
}
