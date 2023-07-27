package com.hiringportal.service.educationHistory;

import com.hiringportal.dto.EducationHistoriesResponse;
import com.hiringportal.dto.QuestionResponse;
import com.hiringportal.entities.EducationHistory;
import com.hiringportal.service.GenericService;

import java.util.List;

public interface EducationHistoriesService extends GenericService<EducationHistory,Integer> {
    List<EducationHistoriesResponse> getEducationList();
//    EducationHistoriesResponse getEducation(Integer id);
    List<EducationHistoriesResponse> getEducationListCandidate(Integer candidateId);
}
