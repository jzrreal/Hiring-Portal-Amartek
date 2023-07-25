package com.hiringportal.service.educationHistory;

import com.hiringportal.dto.EducationHistoriesResponse;
import com.hiringportal.dto.QuestionResponse;
import com.hiringportal.entities.EducationHistory;
import com.hiringportal.repository.EducationHistoryRepository;
import com.hiringportal.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EducationHistoriesServiceImpl implements EducationHistoriesService {
    private final EducationHistoryRepository educationHistoryRepository;
    private final ValidationService validationService;

    @Override
    public List<EducationHistory> getAll() {
        return educationHistoryRepository.findAll();
    }

    @Override
    public EducationHistory getById(Integer id) {
        return educationHistoryRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Education Histories With ID  " + id + " Not Found")
        );
    }

    @Override
    public EducationHistory save(EducationHistory entity) {
        validationService.validate(entity);
        return educationHistoryRepository.save(entity);
    }

    @Override
    public EducationHistory update(EducationHistory entity) {
        validationService.validate(entity);
        return educationHistoryRepository.save(entity);
    }

    @Override
    public Boolean delete(Integer id) {
        educationHistoryRepository.deleteById(id);
        return educationHistoryRepository.findById(id).isEmpty();
    }


    @Override
    public List<EducationHistoriesResponse> getEducationList() {
        List<EducationHistoriesResponse> educationHistoriesResponses = educationHistoryRepository.findAll()
                .stream().map(educationList -> EducationHistoriesResponse.builder()
                        .historiesId(educationList.getId())
                        .level(educationList.getLevel().name())
                        .name(educationList.getName())
                        .major(educationList.getMajor())
                        .yearStart(educationList.getYearStart())
                        .yearEnd(educationList.getYearEnd())
                        .candidateId(educationList.getCandidateProfile().getId())
                        .build())
                .collect(Collectors.toList());
        return educationHistoriesResponses;
    }



    @Override
    public List<EducationHistoriesResponse> getEducationListCandidate(Integer candidateId) {
        List<EducationHistoriesResponse> educationHistoriesResponses = educationHistoryRepository.findByCandidateProfileId(candidateId)
                .stream().map(educationList -> EducationHistoriesResponse.builder()
                        .historiesId(educationList.getId())
                        .level(educationList.getLevel().name())
                        .name(educationList.getName())
                        .major(educationList.getMajor())
                        .yearStart(educationList.getYearStart())
                        .yearEnd(educationList.getYearEnd())
                        .candidateId(educationList.getCandidateProfile().getId())
                        .build())
                .collect(Collectors.toList());

        return educationHistoriesResponses;
    }
}
