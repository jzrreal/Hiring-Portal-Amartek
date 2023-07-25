package com.hiringportal.dto;

import com.hiringportal.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidateProfileQuery {

    private String fullName;
    private Date birthDate;
    private Gender gender;
    private Integer id;
}
