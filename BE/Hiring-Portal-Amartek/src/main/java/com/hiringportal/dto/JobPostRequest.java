package com.hiringportal.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobPostRequest {
    private String title;
    private String description;
    private String requirements;
    private Integer job_level_id;
    private Integer job_function_id;
    private Date open_until;
    private Integer vacancy;
    private Boolean closed;
    private Integer user_id;
}
