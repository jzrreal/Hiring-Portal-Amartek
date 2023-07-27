package com.hiringportal.service.dashboard;

import com.hiringportal.dto.DashboardIndexApplicantResponse;
import com.hiringportal.dto.DashboardIndexResponse;

public interface DashboardService {

    DashboardIndexResponse getDashboardIndexData();
    DashboardIndexApplicantResponse getDashboardIndexDataApplicant(Integer candidateProfileId);

}
