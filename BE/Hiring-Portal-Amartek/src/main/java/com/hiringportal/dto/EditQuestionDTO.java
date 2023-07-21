package com.hiringportal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EditQuestionDTO {
    private String question;
    private Integer questionLevelId;
    private String segment;
}