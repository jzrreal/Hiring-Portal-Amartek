package com.hiringportal.controller.master;

import com.hiringportal.dto.CustomResponse;
import com.hiringportal.entities.TestParameter;
import com.hiringportal.service.testParameter.TestParameterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/test-parameters")
@RequiredArgsConstructor
public class TestParameterController {

    private final TestParameterService testParameterService;

    @GetMapping
    public ResponseEntity<Object> getAllTestParameter() {
        List<TestParameter> response = testParameterService.getAll();

        return CustomResponse.generateResponse(
                "Data found with total amount : " + response.size(),
                HttpStatus.OK,
                response
        );
    }

    @GetMapping(value = "{testParameterId}")
    public ResponseEntity<Object> getTestParameterById(@PathVariable(name = "testParameterId") Integer testParameterId) {
        TestParameter response = testParameterService.getById(testParameterId);

        return CustomResponse.generateResponse(
                "Data found",
                HttpStatus.OK,
                response
        );
    }

    @PostMapping
    public ResponseEntity<Object> postTestParameter(@RequestBody TestParameter request) {
        TestParameter response = testParameterService.save(request);

        return CustomResponse.generateResponse(
                "Success save data",
                HttpStatus.OK,
                response
        );
    }

    @PutMapping(value = "{testParameterId}")
    public ResponseEntity<Object> updateTestParameter(
            @PathVariable(name = "testParameterId") Integer testParameterId,
            @RequestBody TestParameter request
    ) {
        request.setId(testParameterId);

        TestParameter response = testParameterService.update(request);

        return CustomResponse.generateResponse(
                "Success update data",
                HttpStatus.OK,
                response
        );
    }

    @DeleteMapping(value = "{testParameterId}")
    public ResponseEntity<Object> deleteTestParameter(@PathVariable(name = "testParameterId") Integer testParameterId) {
        testParameterService.delete(testParameterId);

        return CustomResponse.generateResponse(
                "Success delete data",
                HttpStatus.OK
        );
    }

}
