package com.hiringportal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddQuestionDTO {
    private Integer questionId;
    private String question;
    private Integer questionLevelId;
    private String segment;
    private Date createdAt;
    private List<InsertChoiceQuestionDTO> choices;


}
