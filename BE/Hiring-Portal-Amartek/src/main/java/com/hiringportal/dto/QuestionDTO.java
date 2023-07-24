package com.hiringportal.dto;

import com.hiringportal.entities.Choice;
import com.hiringportal.enums.Segment;
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
public class QuestionDTO {
    private Integer questionId;
    private String question;
    private Segment segment;
    private Date createdAt;
    private List<ChoiceDTO> choices;
    private List<TestQuestionDTO> testQuestion;
}
