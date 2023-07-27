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
public class UserRequest {
    private Integer user_id;
    private String email;
    private String password;
    private String full_name;
    private Integer role_id;
    private Gender gender;
}
