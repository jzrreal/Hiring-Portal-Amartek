package com.hiringportal.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ApplicantApplyResponse {
    private Integer jobApplicationId;
    private String applicantName;
    private String title;
    private Date applyDate;
    private String jobLevel;
    private String jobFunction;
    private String status;
    private Integer jobPostId;
}
