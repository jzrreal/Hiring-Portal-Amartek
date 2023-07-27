package com.hiringportal.service.choice;

import com.hiringportal.entities.Choice;
import com.hiringportal.repository.ChoiceRepository;
import com.hiringportal.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
public class ChoiceServiceImpl implements ChoiceService{
    @Autowired
    ChoiceRepository choiceRepository;
    @Autowired
    ValidationService validationService;
    @Override
    public List<Choice> getAll() {
        return choiceRepository.findAll();
    }

    @Override
    public Choice getById(Integer id) {
        return choiceRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Choice  with Id " + id + " not found")
        );
    }

    @Override
    public Choice save(Choice entity) {
        validationService.validate(entity);
        return choiceRepository.save(entity);
    }

    @Override
    public Choice update(Choice entity) {
        validationService.validate(entity);
        return choiceRepository.save(entity);
    }

    @Override
    public Boolean delete(Integer id) {
        choiceRepository.deleteById(id);
        return choiceRepository.findById(id).isEmpty();
    }
}
