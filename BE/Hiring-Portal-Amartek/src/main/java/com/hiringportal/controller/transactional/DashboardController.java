package com.hiringportal.controller.transactional;

import com.hiringportal.dto.CustomResponse;
import com.hiringportal.dto.DashboardIndexApplicantResponse;
import com.hiringportal.dto.DashboardIndexResponse;
import com.hiringportal.dto.DashboardIndexTrainerResponse;
import com.hiringportal.entities.User;
import com.hiringportal.service.dashboard.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/dashboards")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<Object> getIndexDashboard(Authentication authentication) {

        User user = (User) authentication.getPrincipal();

        if (user.getRole().getId() == 4) {

            DashboardIndexApplicantResponse response = dashboardService.getDashboardIndexDataApplicant(user.getCandidateProfile().getId());

            return CustomResponse.generateResponse(
                    "Data found",
                    HttpStatus.OK,
                    response
            );
        }

        if (user.getRole().getId() == 3){
            DashboardIndexTrainerResponse response = dashboardService.getDashboardIndexDataTrainer();

            return CustomResponse.generateResponse(
                    "Data found",
                    HttpStatus.OK,
                    response
            );
        }

        DashboardIndexResponse response = dashboardService.getDashboardIndexData();

        return CustomResponse.generateResponse(
                "Data found",
                HttpStatus.OK,
                response
        );
    }
}
