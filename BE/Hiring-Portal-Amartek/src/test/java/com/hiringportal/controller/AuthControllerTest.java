package com.hiringportal.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hiringportal.dto.LoginRequest;
import com.hiringportal.dto.RegisterRequest;
import com.hiringportal.dto.ResendVerificationRequest;
import com.hiringportal.dto.WebResponse;
import com.hiringportal.entities.CandidateProfile;
import com.hiringportal.entities.User;
import com.hiringportal.repository.CandidateProfileRepository;
import com.hiringportal.repository.RoleRepository;
import com.hiringportal.repository.TokenRepository;
import com.hiringportal.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CandidateProfileRepository candidateProfileRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private User user;
    private CandidateProfile candidateProfile;

    @BeforeEach
    void setUp() {
        candidateProfileRepository.deleteAll();
        tokenRepository.deleteAll();
        userRepository.deleteAll();

        user = userRepository.save(User.builder()
                .role(roleRepository.findById(4).orElse(null))
                .email("same@gmail.com")
                .password(passwordEncoder.encode("rahasia"))
                .fullName("Gayuh")
                .build());

        candidateProfile = candidateProfileRepository.save(CandidateProfile.builder()
                .summary("Huwalahumba")
                .phone("123")
                .user(user)
                .verify(false)
                .token(UUID.randomUUID().toString())
                .birthDate(Date.valueOf("2000-01-09"))
                .build());
    }

    @Test
    void registerSuccess() throws Exception {
        RegisterRequest request = RegisterRequest.builder()
                .email("test1@gmail.com")
                .birthDate(Date.valueOf("2002-10-10"))
                .password("rahasia")
                .fullName("Ahmad")
                .phone("021")
                .build();

        String requestJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                post("/api/auth/register")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertEquals("Success register, check email for verify your account", response.getMessage());
        });
    }

    @Test
    void registerFailedEmailExist() throws Exception {
        RegisterRequest request = RegisterRequest.builder()
                .email(user.getEmail())
                .birthDate(Date.valueOf("2002-10-10"))
                .password("rahasia")
                .fullName("Ahmad")
                .phone("021")
                .build();

        String requestJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                post("/api/auth/register")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertEquals("email already used", response.getMessage());
        });
    }

    @Test
    void registerFailedPhoneExist() throws Exception {
        RegisterRequest request = RegisterRequest.builder()
                .email("test2@gmail.com")
                .birthDate(Date.valueOf("2002-10-10"))
                .password("rahasia")
                .fullName("Ahmad")
                .phone(candidateProfile.getPhone())
                .build();

        String requestJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                post("/api/auth/register")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertEquals("phone number already used", response.getMessage());
        });
    }

    @Test
    void loginWrongEmail() throws Exception {
        LoginRequest request = LoginRequest.builder()
                .password("rahasia")
                .email("salah@gmail.com")
                .build();

        String requestJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                post("/api/auth/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
        ).andExpectAll(
                status().isUnauthorized()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertEquals("email or password wrong", response.getMessage());
        });
    }

    @Test
    void loginWrongPassword() throws Exception {
        LoginRequest request = LoginRequest.builder()
                .password("salah")
                .email(user.getEmail())
                .build();

        String requestJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                post("/api/auth/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
        ).andExpectAll(
                status().isUnauthorized()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertEquals("email or password wrong", response.getMessage());
        });
    }

    @Test
    void loginNotVerify() throws Exception {
        LoginRequest request = LoginRequest.builder()
                .password("rahasia")
                .email(user.getEmail())
                .build();

        String requestJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                post("/api/auth/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
        ).andExpectAll(
                status().isUnauthorized()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertEquals("please verify your account", response.getMessage());
        });
    }

    @Test
    void verifyEmailFalse() throws Exception {
        mockMvc.perform(
                get("/api/auth/verify-email")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("token", "12183")
        ).andExpectAll(
                status().isNotFound()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertEquals("account not found", response.getMessage());
        });
    }

    @Test
    void verifyEmailSuccessAndLoginSuccess() throws Exception {
        mockMvc.perform(
                get("/api/auth/verify-email")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("token", candidateProfile.getToken())
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertEquals("Success verify email", response.getMessage());
        });

        LoginRequest request = LoginRequest.builder()
                .password("rahasia")
                .email(user.getEmail())
                .build();

        String requestJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                post("/api/auth/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertNotNull(response.getData());
            assertEquals("Success login", response.getMessage());
        });
    }
    @Test
    void resendVerifyEmailSuccess() throws Exception {
        ResendVerificationRequest request = new ResendVerificationRequest();
        request.setEmail(user.getEmail());

        String requestJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                post("/api/auth/resend-verification")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertEquals("Success resend verify email, check email for verify your account", response.getMessage());
        });
    }

    @Test
    void resendVerifyEmailFailed() throws Exception {
        mockMvc.perform(
                get("/api/auth/verify-email")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("token", candidateProfile.getToken())
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertEquals("Success verify email", response.getMessage());
        });

        ResendVerificationRequest request = new ResendVerificationRequest();
        request.setEmail(user.getEmail());

        String requestJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                post("/api/auth/resend-verification")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
        ).andExpectAll(
                status().isForbidden()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertEquals("Email already verify", response.getMessage());
        });
    }

    @Test
    void resendVerifyEmailNotFound() throws Exception {
        ResendVerificationRequest request = new ResendVerificationRequest();
        request.setEmail("salah@gmail.com");

        String requestJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                post("/api/auth/resend-verification")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
        ).andExpectAll(
                status().isNotFound()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertEquals("account not found", response.getMessage());
        });
    }

    @Test
    void logoutSuccess() throws Exception {
        AtomicReference<String> token = new AtomicReference<>();

        mockMvc.perform(
                get("/api/auth/verify-email")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("token", candidateProfile.getToken())
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertEquals("Success verify email", response.getMessage());
        });

        LoginRequest request = LoginRequest.builder()
                .password("rahasia")
                .email(user.getEmail())
                .build();

        String requestJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                post("/api/auth/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertNotNull(response.getData());
            assertEquals("Success login", response.getMessage());

            token.set(response.getData());
        });

        mockMvc.perform(
                post("/api/auth/logout")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token.get())
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertEquals("Success logout", response.getMessage());
        });
    }

    @Test
    void logoutFailedWrongToken() throws Exception {
        mockMvc.perform(
                post("/api/auth/logout")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer tokenSalah")
        ).andExpectAll(
                status().isForbidden()
        );
    }
    @Test
    void logoutFailedNoToken() throws Exception {
        mockMvc.perform(
                post("/api/auth/logout")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isForbidden()
        );
    }
}
