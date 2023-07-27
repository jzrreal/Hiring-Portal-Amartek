package com.hiringportal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TestQuestionQuery {

    private Integer testQuestionId;
    private Integer questionId;
    private Integer answer;
}
