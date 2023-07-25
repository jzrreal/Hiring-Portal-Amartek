package com.hiringportal.dto;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.hiringportal.enums.EducationLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EducationHistoryQuery {
    private EducationLevel level;
    private String name;
    private String major;
    private Integer candidateProfileId;
}
