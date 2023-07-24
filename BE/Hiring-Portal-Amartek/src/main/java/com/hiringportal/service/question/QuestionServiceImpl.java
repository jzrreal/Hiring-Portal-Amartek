package com.hiringportal.service.question;

import com.hiringportal.entities.Questions;
import com.hiringportal.repository.QuestionRepository;
import com.hiringportal.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    ValidationService validationService;
    @Override
    public List<Questions> getAll() {
        return questionRepository.findAll();
    }

    @Override
    public Questions getById(Integer id) {
        return questionRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Question  with Id " + id + " not found")
        );
    }

    @Override
    public Questions save(Questions entity) {
        validationService.validate(entity);
        var saveEntity = questionRepository.save(entity);
        return saveEntity;
    }

    @Override
    public Questions update(Questions entity) {
        validationService.validate(entity);
        var saveEntity = questionRepository.save(entity);
        return saveEntity;
    }

    @Override
    public Boolean delete(Integer id) {
        questionRepository.deleteById(id);
        return questionRepository.findById(id).isEmpty();
    }
}
