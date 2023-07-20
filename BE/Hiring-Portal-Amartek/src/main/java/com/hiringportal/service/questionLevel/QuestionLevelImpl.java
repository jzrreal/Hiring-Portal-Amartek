package com.hiringportal.service.questionLevel;

import com.hiringportal.entities.QuestionLevel;
import com.hiringportal.repository.QuestionLevelRepository;
import com.hiringportal.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
@RequiredArgsConstructor
public class QuestionLevelImpl implements QuestionLevelService{

    private final QuestionLevelRepository questionLevelRepository;
    private final ValidationService validationService;
    @Override
    public List<QuestionLevel> getAll() {
        return questionLevelRepository.findAll();
    }

    @Override
    public QuestionLevel getById(Integer id) {
        return questionLevelRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Question Level with Id " + id + " not found")
        );
    }

    @Override
    public QuestionLevel save(QuestionLevel entity) {
        validationService.validate(entity);
        return questionLevelRepository.save(entity);
    }

    @Override
    public QuestionLevel update(QuestionLevel entity) {
        validationService.validate(entity);
        return questionLevelRepository.save(entity);
    }

    @Override
    public Boolean delete(Integer id) {
        questionLevelRepository.deleteById(id);
        return questionLevelRepository.findById(id).isEmpty();
    }
}
