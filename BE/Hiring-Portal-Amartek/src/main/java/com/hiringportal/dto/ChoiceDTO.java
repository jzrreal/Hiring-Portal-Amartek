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
public class ChoiceDTO {
    private Integer choiceId;
    private String choice;
    private Integer questionId;
    private Boolean correct;

}
