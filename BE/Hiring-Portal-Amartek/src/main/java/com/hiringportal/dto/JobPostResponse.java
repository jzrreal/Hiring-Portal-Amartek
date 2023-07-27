package com.hiringportal.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class JobPostResponse {
    private Integer id;
    private String title;
    private String jobLevel;
    private String jobFunction;
    private Date postAt;
    private Boolean closed;
    private Date openUntil;
    private Integer vacancy;
}
