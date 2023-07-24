package com.hiringportal.service.testParameter;

import com.hiringportal.entities.TestParameter;
import com.hiringportal.repository.TestParameterRepository;
import com.hiringportal.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestParameterServiceImpl implements TestParameterService {

    private final TestParameterRepository testParameterRepository;
    private final ValidationService validationService;

    @Override
    public List<TestParameter> getAll() {
        return testParameterRepository.findAll();
    }

    @Override
    public TestParameter getById(Integer id) {
        return testParameterRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Test Parameter with Id " + id + " not found"));
    }

    @Override
    public TestParameter save(TestParameter entity) {
        validationService.validate(entity);

        return testParameterRepository.save(entity);
    }

    @Override
    public TestParameter update(TestParameter entity) {
        validationService.validate(entity);

        TestParameter testParameter = testParameterRepository.findById(entity.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Test Parameter with Id " + entity.getId() + " not found"));

        testParameter.setTestTimeMinute(entity.getTestTimeMinute());
        testParameter.setExpirationHour(entity.getExpirationHour());

        return testParameterRepository.save(testParameter);
    }

    @Override
    public Boolean delete(Integer id) {
        testParameterRepository.deleteById(id);
        return testParameterRepository.findById(id).isEmpty();
    }
}
