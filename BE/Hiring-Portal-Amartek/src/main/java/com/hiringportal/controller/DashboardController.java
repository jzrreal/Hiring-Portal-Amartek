package com.hiringportal.controller;

import com.hiringportal.dto.CustomResponse;
import com.hiringportal.dto.DashboardIndexResponse;
import com.hiringportal.service.dashboard.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/dashboards")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<Object> getIndexDashboard(){

        DashboardIndexResponse response = dashboardService.getDashboardIndexData();

        return CustomResponse.generateResponse(
                "Data found",
                HttpStatus.OK,
                response
        );
    }
}
