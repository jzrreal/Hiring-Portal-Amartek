package com.hiringportal.service.skillLevel;

import com.hiringportal.entities.SkillLevel;
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

        SkillLevel skillLevel =skillLevelRepository.findById(entity.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Skill Level with Id " + entity.getId() + " not found"));

        skillLevel.setName(entity.getName());

        return skillLevelRepository.save(skillLevel);
    }

    @Override
    public Boolean delete(Integer id) {
        skillLevelRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Skill Level with Id " + id + " not found"));

        skillLevelRepository.deleteById(id);
        return true;
    }
}
