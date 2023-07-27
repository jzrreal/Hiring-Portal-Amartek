package com.hiringportal.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.hiringportal.enums.EducationLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class EducationHistoriesUpsert {
    @JsonIgnore
    private Integer id;
    @NotNull
    private EducationLevel level;
    @NotBlank
    private String name;
    @NotNull
    private String major;
    @NotNull
    private Integer yearStart;
    private Integer yearEnd;
    @JsonIgnore
    private Integer candidateId;
}
