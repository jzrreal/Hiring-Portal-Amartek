package com.hiringportal.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hiringportal.dto.WebResponse;
import com.hiringportal.entities.SkillLevel;
import com.hiringportal.repository.SkillLevelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@AutoConfigureMockMvc
public class SkillLevelControllerTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private SkillLevelRepository skillLevelRepository;

    @BeforeEach
    void setUp() {
        skillLevelRepository.deleteAll();
    }

    @Test
    void getAll() throws Exception {
        for (int i = 0; i < 10; i++) {
            SkillLevel skillLevel = SkillLevel.builder()
                    .name("skill-" + i)
                    .build();

            skillLevelRepository.save(skillLevel);
        }

        mockMvc.perform(
                get("/api/skill-levels")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<List<SkillLevel>> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<List<SkillLevel>>>() {
            });

            assertNotNull(response.getData());
            assertEquals(10, response.getData().size());
        });
    }

    @Test
    void getByIdSuccess() throws Exception {
        SkillLevel skillLevel = SkillLevel.builder()
                .name("Test Add Skill Level")
                .build();

        skillLevelRepository.save(skillLevel);

        mockMvc.perform(
                get("/api/skill-levels/" + skillLevel.getId())
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<SkillLevel> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<SkillLevel>>() {
            });

            assertNotNull(response.getData());
            assertEquals("Data found", response.getMessage());
            assertEquals("Test Add Skill Level", response.getData().getName());
        });
    }

    @Test
    void getByIdFailed() throws Exception {

        mockMvc.perform(
                get("/api/skill-levels/1")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isNotFound()
        ).andDo(result -> {
            WebResponse<SkillLevel> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<SkillLevel>>() {
            });

            assertNull(response.getData());
            assertEquals("Skill Level with Id 1 not found", response.getMessage());
        });
    }

    @Test
    void createSkillLevelBadRequest() throws Exception {
       SkillLevel request = SkillLevel.builder()
               .name("")
               .build();
        String requestJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                post("/api/skill-levels")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<SkillLevel> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<SkillLevel>>() {
            });

            assertNull(response.getData());
            assertNotNull(response.getMessage());
        });
    }

    @Test
    void createSkillLevelSuccess() throws Exception {
        SkillLevel request = SkillLevel.builder()
                .name("Test Ges")
                .build();
        String requestJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                post("/api/skill-levels")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<SkillLevel> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<SkillLevel>>() {
            });

            assertNotNull(response.getData());
            assertEquals("Test Ges", response.getData().getName());
            assertNotNull(response.getMessage());
        });
    }

    @Test
    void updateSkillLevelBadRequest() throws Exception {
        SkillLevel skillLevel = SkillLevel.builder()
                .name("Test Add Skill Level")
                .build();

        skillLevelRepository.save(skillLevel);

        skillLevel.setName("");

        String requestJson = objectMapper.writeValueAsString(skillLevel);

        mockMvc.perform(
                put("/api/skill-levels/" + skillLevel.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<SkillLevel> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<SkillLevel>>() {
            });

            assertNull(response.getData());
            assertNotNull(response.getMessage());
        });
    }

    @Test
    void updatSkillLevelSuccess() throws Exception {
        SkillLevel skillLevel = SkillLevel.builder()
                .name("Test Add Skill Level")
                .build();

        skillLevelRepository.save(skillLevel);

        skillLevel.setName("Test True");

        String requestJson = objectMapper.writeValueAsString(skillLevel);

        mockMvc.perform(
                put("/api/skill-levels/" + skillLevel.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<SkillLevel> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<SkillLevel>>() {
            });

            assertNotNull(response.getData());
            assertEquals("Test True", response.getData().getName());
        });
    }

    @Test
    void deleteSkillLevelSuccess() throws Exception {
        SkillLevel skillLevel = SkillLevel.builder()
                .name("Test Add Skill Level")
                .build();

        skillLevelRepository.save(skillLevel);

        mockMvc.perform(
                delete("/api/skill-levels/" + skillLevel.getId())
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<SkillLevel> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<SkillLevel>>() {
            });

            assertNull(response.getData());
            assertEquals("Success delete data", response.getMessage());
        });
    }

    @Test
    void deleteSkillLevelFailed() throws Exception {
        mockMvc.perform(
                delete("/api/skill-levels/1")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isNotFound()
        ).andDo(result -> {
            WebResponse<SkillLevel> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<SkillLevel>>() {
            });

            assertNull(response.getData());
            assertEquals("Skill Level with Id 1 not found", response.getMessage());
        });
    }
}
