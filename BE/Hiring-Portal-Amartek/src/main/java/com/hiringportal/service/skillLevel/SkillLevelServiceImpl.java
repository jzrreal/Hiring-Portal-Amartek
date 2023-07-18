package com.hiringportal.service.skillLevel;

import com.hiringportal.entity.SkillLevel;
import com.hiringportal.repository.SkillLevelRepository;
import com.hiringportal.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillLevelServiceImpl implements SkillLevelService{

    private final SkillLevelRepository skillLevelRepository;
    private final ValidationService validationService;

    @Override
    public List<SkillLevel> getAll() {
        return skillLevelRepository.findAll();
    }

    @Override
    public SkillLevel getById(Integer id) {
        return skillLevelRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Skill Level with Id " + id + " not found")
        );
    }

    @Override
    public SkillLevel save(SkillLevel entity) {
        validationService.validate(entity);

        return skillLevelRepository.save(entity);
    }

    @Override
    public SkillLevel update(SkillLevel entity) {
        validationService.validate(entity);

        return skillLevelRepository.save(entity);
    }

    @Override
    public Boolean delete(Integer id) {
        skillLevelRepository.deleteById(id);
        return skillLevelRepository.findById(id).isEmpty();
    }
}
