package com.hiringportal.dto;

import com.hiringportal.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private Integer user_id;
    private String email;
    private String full_name;
    private String role_name;
    private Gender gender;
}
