package com.hiringportal.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.hiringportal.enums.Gender;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
public class CandidateProfileRequest {
    @JsonIgnore
    private Integer id;
    @NotBlank
    private String phone;
    private String summary;
    @NotNull
    private Date birthDate;
    private Integer userId;
    private Gender gender;
}
