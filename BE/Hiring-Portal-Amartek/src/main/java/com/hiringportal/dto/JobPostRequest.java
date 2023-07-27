package com.hiringportal.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobPostRequest {
    @NotBlank
    private String title;
    private String description;
    @NotBlank
    private String requirements;
    @NotNull
    private Integer job_level_id;
    @NotNull
    private Integer job_function_id;
    @NotNull
    private Date open_until;
    @NotNull
    private Integer vacancy;
    private Boolean closed;
    @JsonIgnore
    private Integer user_id;
}
