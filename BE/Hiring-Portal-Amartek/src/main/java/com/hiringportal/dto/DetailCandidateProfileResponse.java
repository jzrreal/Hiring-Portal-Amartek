package com.hiringportal.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DetailCandidateProfileResponse {
    private Integer id;
    private String email;
    private String fullName;
    private String phone;
    private String summary;
    private Date birthDate;
    private Integer age;
    private String gender;
    private String lastEducation;
}
