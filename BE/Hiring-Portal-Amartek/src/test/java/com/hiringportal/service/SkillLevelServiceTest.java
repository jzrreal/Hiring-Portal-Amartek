package com.hiringportal.service;

import com.hiringportal.entities.SkillLevel;
import com.hiringportal.repository.SkillLevelRepository;
import com.hiringportal.service.skillLevel.SkillLevelService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.validation.ConstraintViolationException;
import java.util.List;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
public class SkillLevelServiceTest {
    @Autowired
    private SkillLevelService skillLevelService;
    @Autowired
    private SkillLevelRepository skillLevelRepository;

    @BeforeEach
    void setUp() {
        skillLevelRepository.deleteAll();
    }

    @Test
    void saveSuccess() {
        SkillLevel skillLevel = SkillLevel.builder()
                .name("Java")
                .build();

        skillLevelService.save(skillLevel);

        Assertions.assertNotNull(skillLevel.getId());
        Assertions.assertEquals("Java", skillLevel.getName());
    }

    @Test
    void saveFailed() {
        SkillLevel skillLevel = SkillLevel.builder()
                .name("")
                .build();

        Assertions.assertThrows(ConstraintViolationException.class,
                () -> skillLevelService.save(skillLevel));
    }

    @Test
    void getAll(){
        for (int i = 0; i < 10; i++) {
            SkillLevel skillLevel = SkillLevel.builder()
                    .name("Skill-" + i)
                    .build();

            skillLevelService.save(skillLevel);
        }

        List<SkillLevel> skillLevels = skillLevelService.getAll();

        Assertions.assertNotNull(skillLevels);
        Assertions.assertEquals(10, skillLevels.size());
    }

    @Test
    void getById() {
        SkillLevel skillLevel = SkillLevel.builder()
                .name("Java")
                .build();

        skillLevelService.save(skillLevel);

        skillLevel = skillLevelService.getById(skillLevel.getId());

        Assertions.assertNotNull(skillLevel);
    }

    @Test
    void delete() {
        SkillLevel skillLevel = SkillLevel.builder()
                .name("Java")
                .build();

        skillLevelService.save(skillLevel);

        skillLevel = skillLevelService.getById(skillLevel.getId());

        Assertions.assertNotNull(skillLevel);

        Assertions.assertTrue(skillLevelService.delete(skillLevel.getId()));
    }

    @Test
    void updateSuccess() {
        SkillLevel skillLevel = SkillLevel.builder()
                .name("Java")
                .build();

        skillLevelService.save(skillLevel);

        skillLevel = skillLevelService.getById(skillLevel.getId());

        Assertions.assertNotNull(skillLevel);

        skillLevel.setName("PostgreSQL");
        skillLevelService.update(skillLevel);
        Assertions.assertEquals(skillLevel.getName(), skillLevelService.getById(skillLevel.getId()).getName());
    }

    @Test
    void updateFailed() {
        SkillLevel skillLevel = SkillLevel.builder()
                .name("Java")
                .build();

        skillLevelService.save(skillLevel);

        skillLevel = skillLevelService.getById(skillLevel.getId());

        Assertions.assertNotNull(skillLevel);

        skillLevel.setName("");
        SkillLevel finalSkillLevel = skillLevel;
        Assertions.assertThrows(ConstraintViolationException.class,
                () -> skillLevelService.update(finalSkillLevel));
    }
}
