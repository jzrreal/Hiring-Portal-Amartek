package com.hiringportal.dto;

import java.sql.Date;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class JobPostDetailResponse {
    private Integer id;
    private String title;
    private String description;
    private String requirements;
    private String jobLevel;
    private String jobFunction;
    private Date postAt;
    private Date openUntil;
    private Date updatedAt;
    private Integer vacancy;
    private Boolean closed;
}
