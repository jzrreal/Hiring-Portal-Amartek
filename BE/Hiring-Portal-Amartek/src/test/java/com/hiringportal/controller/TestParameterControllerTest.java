package com.hiringportal.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hiringportal.dto.WebResponse;
import com.hiringportal.entities.TestParameter;
import com.hiringportal.repository.TestParameterRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@AutoConfigureMockMvc
public class TestParameterControllerTest {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestParameterRepository testParameterRepository;

    private TestParameter testParameter;

    @BeforeEach
    void setUp() {
        testParameterRepository.deleteAll();

        testParameter = testParameterRepository.save(
                TestParameter.builder()
                        .expirationHour(48)
                        .testTimeMinute(60)
                        .build()
        );
    }

    @Test
    void getAll() throws Exception {
        for (int i = 0; i < 10; i++) {
            testParameterRepository.save(
                    TestParameter.builder()
                            .testTimeMinute(60)
                            .expirationHour(10)
                            .build()
            );
        }

        mockMvc.perform(
                get("/api/test-parameters")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<List<TestParameter>> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertNotNull(response.getData());
            assertEquals(11, response.getData().size());
            assertEquals("Data found with total amount : 11", response.getMessage());
        });
    }

    @Test
    void getByIdNotFound() throws Exception {
        mockMvc.perform(
                get("/api/test-parameters/-1")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isNotFound()
        ).andDo(result -> {
            WebResponse<List<TestParameter>> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertNull(response.getData());
            assertEquals("Test Parameter with Id -1 not found", response.getMessage());
        });
    }

    @Test
    void getByIdSuccess() throws Exception {
        mockMvc.perform(
                get("/api/test-parameters/" + testParameter.getId())
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<TestParameter> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertNotNull(response.getData());
            assertEquals(60, response.getData().getTestTimeMinute());
            assertEquals(48, response.getData().getExpirationHour());
            assertEquals("Data found", response.getMessage());
        });
    }

    @Test
    void updateSuccess() throws Exception {
        TestParameter request = TestParameter.builder()
                .expirationHour(24)
                .testTimeMinute(30)
                .build();

        String stringJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                put("/api/test-parameters/" + testParameter.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stringJson)
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<TestParameter> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertNotNull(response.getData());
            assertEquals(30, response.getData().getTestTimeMinute());
            assertEquals(24, response.getData().getExpirationHour());
            assertEquals("Success update data", response.getMessage());
        });
    }


}
