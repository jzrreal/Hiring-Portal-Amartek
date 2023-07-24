package com.hiringportal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestQuestionDTO {
    private Integer testId;
    private Integer questionId;
    private Integer answer;
    private Integer testQuestionId;
}
