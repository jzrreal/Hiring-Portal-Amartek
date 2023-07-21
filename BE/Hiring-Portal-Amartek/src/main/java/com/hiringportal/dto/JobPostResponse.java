package com.hiringportal.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobPostResponse {
    private String title;
    private String description;
    private String requirements;
    private String job_levelName;
    private String job_functionName;
    private Date post_at;
    private Date open_until;
    private Date updated_at;
    private Integer vacancy;
    private Boolean closed;
}
