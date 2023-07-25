package com.hiringportal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EducationHistoriesResponse {
    private Integer historiesId;
    private String level;
    private String name;
    private String major;
    private Date yearStart;
    private Date yearEnd;
    private Integer candidateId;

}
