package com.hiringportal.service.jobLevel;

import com.hiringportal.entities.JobLevel;
import com.hiringportal.repository.JobLevelRepository;
import com.hiringportal.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobLevelServiceImpl implements JobLevelService{

    private final JobLevelRepository jobLevelRepository;
    private final ValidationService validationService;
    @Override
    public List<JobLevel> getAll() {
        return jobLevelRepository.findAll();
    }

    @Override
    public JobLevel getById(Integer id) {
        return jobLevelRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job Level with Id " + id + " not found")
        );
    }

    @Override
    public JobLevel save(JobLevel entity) {
        validationService.validate(entity);

        return jobLevelRepository.save(entity);
    }

    @Override
    public JobLevel update(JobLevel entity) {
        validationService.validate(entity);

        return jobLevelRepository.save(entity);    }

    @Override
    public Boolean delete(Integer id) {
        jobLevelRepository.deleteById(id);
        return jobLevelRepository.findById(id).isEmpty();
    }
}
