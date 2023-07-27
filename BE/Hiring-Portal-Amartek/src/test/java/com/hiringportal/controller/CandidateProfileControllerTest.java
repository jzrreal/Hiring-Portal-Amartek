package com.hiringportal.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hiringportal.dto.CandidateProfileRequest;
import com.hiringportal.dto.CandidateProfileResponse;
import com.hiringportal.dto.WebResponse;
import com.hiringportal.entities.CandidateProfile;
import com.hiringportal.entities.Role;
import com.hiringportal.entities.User;
import com.hiringportal.repository.CandidateProfileRepository;
import com.hiringportal.repository.RoleRepository;
import com.hiringportal.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@AutoConfigureMockMvc
public class CandidateProfileControllerTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CandidateProfileRepository candidateProfileRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    private Role role;

    private List<User> users = new ArrayList<>();

    private CandidateProfile candidateProfile;

    @BeforeEach
    void setUp() {
        candidateProfileRepository.deleteAll();
        userRepository.deleteAll();

        role = roleRepository.findById(4).orElse(null);

        for (int i = 0; i < 4; i++) {
            User user = userRepository.save(User.builder()
                    .email("test@gmail" + i + ".com")
                    .password("rahasia")
                    .fullName("Tonike-" + i)
                    .role(role)
                    .build()
            );

            users.add(user);
        }

        candidateProfile = candidateProfileRepository.save(CandidateProfile.builder()
                .user(users.get(0))
                .summary("Huwaaa")
                .phone("193819831")
                .token(UUID.randomUUID().toString())
                .build());
    }

    @Test
    void getAllCandidateProfile() throws Exception {

        AtomicInteger counter = new AtomicInteger(1);
        users.forEach(user -> {
            if (!users.get(0).getId().equals(user.getId()) && !users.get(3).getId().equals(user.getId())) {
                candidateProfileRepository.save(CandidateProfile.builder()
                        .user(user)
                        .summary("Huwaaa")
                                .token(UUID.randomUUID().toString())
                        .phone(counter.toString())
                        .build());
                counter.getAndIncrement();
            }
        });

        mockMvc.perform(
                get("/api/candidate-profiles")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<List<CandidateProfileResponse>> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<List<CandidateProfileResponse>>>() {
            });

            assertNotNull(response.getData());
            assertEquals(counter.toString(), String.valueOf(response.getData().size()));
        });
    }

    @Test
    void getCandidateProfileByIdSuccess() throws Exception {
        mockMvc.perform(
                get("/api/candidate-profiles/" + candidateProfile.getId())
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<CandidateProfileResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<CandidateProfileResponse>>() {
            });

            assertNotNull(response.getData());
            assertEquals("Huwaaa", response.getData().getSummary());
            assertEquals("193819831", response.getData().getPhone());
        });
    }

    @Test
    void getCandidateProfileByIdFailed() throws Exception {
        mockMvc.perform(
                get("/api/candidate-profiles/-1")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isNotFound()
        ).andDo(result -> {
            WebResponse<CandidateProfileResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<CandidateProfileResponse>>() {
            });

            assertNull(response.getData());
            assertEquals("Candidate Profile with Id -1 not found", response.getMessage());
        });
    }

    @Test
    void addCandidateProfileByIdSuccess() throws Exception {
        CandidateProfileRequest request = CandidateProfileRequest.builder()
                .phone("17863038971")
                .userId(users.get(3).getId())
                .summary("Haiyaa")
                .birthDate(java.sql.Date.valueOf("2001-10-07"))
                .build();

        String requestJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                post("/api/candidate-profiles")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<CandidateProfile> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<CandidateProfile>>() {
            });

            assertNotNull(response.getData());
            assertEquals("17863038971", response.getData().getPhone());
            candidateProfileRepository.deleteById(response.getData().getId());
        });
    }

    @Test
    void addCandidateProfileByIdFailed() throws Exception {
        CandidateProfileRequest request = CandidateProfileRequest.builder()
                .phone("17863038971")
                .userId(users.get(3).getId())
                .summary("Haiyaa")
                .build();

        String requestJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                post("/api/candidate-profiles")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<CandidateProfile> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<CandidateProfile>>() {
            });

            assertNull(response.getData());
            assertEquals("birthDate: must not be null", response.getMessage());
        });
    }

    @Test
    void updateCandidateProfileByIdSuccess() throws Exception {
        User user = User.builder()
                .role(role)
                .fullName("Jiqhofqw")
                .password("rahasia")
                .email("hsas@gmail.com")
                .build();

        userRepository.save(user);

        candidateProfile = candidateProfileRepository.save(CandidateProfile.builder()
                .summary("asasdasd")
                .phone("19087012")
                        .token(UUID.randomUUID().toString())
                .user(user)
                .build());

        CandidateProfileRequest request = CandidateProfileRequest.builder()
                .birthDate(new java.sql.Date(new Date().getTime()))
                .summary("Huwalahumba")
                .phone("09472701972902")
                .build();

        String requestJson = objectMapper.writeValueAsString(request);
        System.out.println(requestJson);

        mockMvc.perform(
                put("/api/candidate-profiles/" + candidateProfile.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<CandidateProfile> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertNotNull(response.getData());
            assertNotNull(response.getMessage());
        });
    }

    @Test
    void updateCandidateProfileByIdFailed() throws Exception {
        CandidateProfileRequest request = CandidateProfileRequest.builder()
                .birthDate(new java.sql.Date(new Date().getTime()))
                .summary("Huwalahumba")
                .phone(null)
                .build();

        String requestJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                put("/api/candidate-profiles/" + candidateProfile.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<CandidateProfile> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<CandidateProfile>>() {
            });

            assertNull(response.getData());
            assertEquals("phone: must not be blank", response.getMessage());
        });
    }

    @Test
    void deleteById() throws Exception {
        mockMvc.perform(
                delete("/api/candidate-profiles/" + candidateProfile.getId())
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<CandidateProfile> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<CandidateProfile>>() {
            });

            assertNull(response.getData());
            assertEquals("success delete data", response.getMessage());
        });
    }
}
