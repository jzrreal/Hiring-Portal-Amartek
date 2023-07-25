package com.hiringportal.service.applicationStatus;

import com.hiringportal.entities.ApplicationStatus;
import com.hiringportal.repository.ApplicationStatusRepository;
import com.hiringportal.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationStatusService {
    private final ApplicationStatusRepository applicationStatusRepository;
    private final ValidationService validationService;

    @Override
    public List<ApplicationStatus> getAll() {
        return applicationStatusRepository.findAll();
    }

    @Override
    public ApplicationStatus getById(Integer id) {
        return applicationStatusRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Application Status with Id " + id + " not found"));
    }

    @Override
    public ApplicationStatus save(ApplicationStatus entity) {
        validationService.validate(entity);

        return applicationStatusRepository.save(entity);
    }

    @Override
    public ApplicationStatus update(ApplicationStatus entity) {
        validationService.validate(entity);

        ApplicationStatus status = applicationStatusRepository.findById(entity.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Application Status with Id " + entity.getId() + " not found"));

        status.setName(entity.getName());

        return applicationStatusRepository.save(entity);
    }

    @Override
    public Boolean delete(Integer id) {
        applicationStatusRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Application Status with Id " + id + " not found"));

        applicationStatusRepository.deleteById(id);

        return applicationStatusRepository.findById(id).isEmpty();
    }
}
