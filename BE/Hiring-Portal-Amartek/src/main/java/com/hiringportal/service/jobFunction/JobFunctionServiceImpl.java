package com.hiringportal.service.jobFunction;

import com.hiringportal.entities.JobFunction;
import com.hiringportal.repository.JobFunctionRepository;
import com.hiringportal.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
@RequiredArgsConstructor
public class JobFunctionServiceImpl implements JobFunctionService{

    private final JobFunctionRepository jobFunctionRepository;
    private final ValidationService validationService;
    @Override
    public List<JobFunction> getAll() {
        return jobFunctionRepository.findAll();
    }

    @Override
    public JobFunction getById(Integer id) {
        return jobFunctionRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job Function with Id " + id + " not found")
        );
    }

    @Override
    public JobFunction save(JobFunction entity) {
        validationService.validate(entity);

        return jobFunctionRepository.save(entity);
    }

    @Override
    public JobFunction update(JobFunction entity) {
        validationService.validate(entity);

        return jobFunctionRepository.save(entity);    }

    @Override
    public Boolean delete(Integer id) {
        jobFunctionRepository.deleteById(id);
        return jobFunctionRepository.findById(id).isEmpty();
    }
}
