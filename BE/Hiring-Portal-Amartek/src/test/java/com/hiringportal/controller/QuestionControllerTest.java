package com.hiringportal.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hiringportal.dto.ChoiceRequest;
import com.hiringportal.dto.QuestionRequest;
import com.hiringportal.dto.QuestionDetailResponse;
import com.hiringportal.dto.WebResponse;
import com.hiringportal.entities.Choice;
import com.hiringportal.entities.Questions;
import com.hiringportal.enums.Segment;
import com.hiringportal.repository.ChoiceRepository;
import com.hiringportal.repository.QuestionLevelRepository;
import com.hiringportal.repository.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@AutoConfigureMockMvc
public class QuestionControllerTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private ChoiceRepository choiceRepository;
    @Autowired
    private QuestionLevelRepository questionLevelRepository;

    private Questions question;
    private List<Choice> choices = new ArrayList<>();

    @BeforeEach
    void setUp() {
        choiceRepository.deleteAll();
        questionRepository.deleteAll();

        question = questionRepository.save(Questions.builder()
                .question("1 + 1 Berapa?")
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .segment(Segment.valueOf("LOGIKA_MATEMATIKA"))
                .questionLevel(questionLevelRepository.findById(1).orElse(null))
                .build());

        choices.addAll(List.of(
                Choice.builder()
                        .choice("2 Ges")
                        .correct(true)
                        .question(question)
                        .build(),
                Choice.builder()
                        .choice("3 Ges")
                        .correct(false)
                        .question(question)
                        .build(),
                Choice.builder()
                        .choice("4 Ges")
                        .correct(false)
                        .question(question)
                        .build(),
                Choice.builder()
                        .choice("5 Ges")
                        .correct(false)
                        .question(question)
                        .build(),
                Choice.builder()
                        .choice("6 Ges")
                        .correct(false)
                        .question(question)
                        .build()
        ));

        choiceRepository.saveAll(choices);
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(
                get("/api/questions")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<List<Questions>> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertEquals("Data found with total amount : 1", response.getMessage());
            assertNotNull(response.getData());
            assertEquals(question.getQuestion(), response.getData().get(0).getQuestion());
        });
    }

    @Test
    void getByIdSuccess() throws Exception {
        mockMvc.perform(
                get("/api/questions/" + question.getQuestionId())
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<QuestionDetailResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertEquals("Data found", response.getMessage());
            assertNotNull(response.getData());
            assertNotNull(response.getData().getChoices());
            assertFalse(response.getData().getChoices().isEmpty());
            assertEquals(question.getQuestion(), response.getData().getQuestion());
            assertEquals(choices.get(0).getChoice(), response.getData().getChoices().get(0).getChoice());
            assertTrue(choices.get(0).isCorrect());
        });
    }

    @Test
    void getByIdNotFound() throws Exception {
        mockMvc.perform(
                get("/api/questions/-1")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isNotFound()
        ).andDo(result -> {
            WebResponse<QuestionDetailResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertEquals("Question with Id -1 not found", response.getMessage());
            assertNull(response.getData());
        });
    }

    @Test
    void postQuestionSuccess() throws Exception {
        List<ChoiceRequest> choiceRequests = new ArrayList<>(List.of(
                ChoiceRequest.builder()
                        .choice("Order By")
                        .correct(false)
                        .build(),
                ChoiceRequest.builder()
                        .choice("Join")
                        .correct(false)
                        .build(),
                ChoiceRequest.builder()
                        .choice("Select")
                        .correct(false)
                        .build(),
                ChoiceRequest.builder()
                        .choice("From")
                        .correct(false)
                        .build(),
                ChoiceRequest.builder()
                        .choice("Select * From")
                        .correct(true)
                        .build()
        ));

        QuestionRequest request = QuestionRequest.builder()
                .QuestionLevelId(1)
                .segment(Segment.DATABASE)
                .question("Untuk mendapatkan semua data dalam database pakai apa?")
                .choiceRequests(choiceRequests)
                .build();

        String requestJson = objectMapper.writeValueAsString(request);

        System.out.println(requestJson);

        mockMvc.perform(
                post("/api/questions")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<QuestionDetailResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertEquals("Success save data", response.getMessage());
            assertNotNull(response.getData());
            assertEquals(request.getQuestion(), response.getData().getQuestion());
        });
    }

    @Test
    void postQuestionBadRequest() throws Exception {
        List<ChoiceRequest> choiceRequests = new ArrayList<>(List.of(
                ChoiceRequest.builder()
                        .choice("Order By")
                        .correct(false)
                        .build(),
                ChoiceRequest.builder()
                        .choice("Join")
                        .correct(false)
                        .build(),
                ChoiceRequest.builder()
                        .choice("Select")
                        .correct(false)
                        .build(),
                ChoiceRequest.builder()
                        .choice("From")
                        .correct(false)
                        .build()
        ));

        QuestionRequest request = QuestionRequest.builder()
                .QuestionLevelId(1)
                .segment(Segment.DATABASE)
                .question("Untuk mendapatkan semua data dalam database pakai apa?")
                .choiceRequests(choiceRequests)
                .build();

        String requestJson = objectMapper.writeValueAsString(request);

        System.out.println(requestJson);

        mockMvc.perform(
                post("/api/questions")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<QuestionDetailResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertEquals("choiceRequests: size must be between 5 and 5", response.getMessage());
            assertNull(response.getData());
        });
    }

    @Test
    void updateQuestionSuccess() throws Exception {
        List<ChoiceRequest> choiceRequests = new ArrayList<>(List.of(
                ChoiceRequest.builder()
                        .choice("Tempe")
                        .correct(false)
                        .build(),
                ChoiceRequest.builder()
                        .choice("Tahu")
                        .correct(false)
                        .build(),
                ChoiceRequest.builder()
                        .choice("Batu")
                        .correct(false)
                        .build(),
                ChoiceRequest.builder()
                        .choice("Rumput")
                        .correct(true)
                        .build(),
                ChoiceRequest.builder()
                        .choice("Daging")
                        .correct(false)
                        .build()
        ));

        QuestionRequest request = QuestionRequest.builder()
                .question("Sapi makannya apa?")
                .segment(Segment.BASIC_PROGRAMMING)
                .QuestionLevelId(2)
                .choiceRequests(choiceRequests)
                .build();

        String requestJson = objectMapper.writeValueAsString(request);

        System.out.println(requestJson);

        mockMvc.perform(
                put("/api/questions/" + question.getQuestionId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<QuestionDetailResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertEquals("Success update data", response.getMessage());
            assertNotNull(response.getData());
            assertEquals(request.getQuestion(), response.getData().getQuestion());
            assertNotNull(response.getData().getChoices());
        });
    }

    @Test
    void updateQuestionBadRequest() throws Exception {
        List<ChoiceRequest> choiceRequests = new ArrayList<>(List.of(
                ChoiceRequest.builder()
                        .choice("Tempe")
                        .correct(false)
                        .build(),
                ChoiceRequest.builder()
                        .choice("Tahu")
                        .correct(false)
                        .build(),
                ChoiceRequest.builder()
                        .choice("Batu")
                        .correct(false)
                        .build(),
                ChoiceRequest.builder()
                        .choice("Rumput")
                        .correct(true)
                        .build()
        ));

        QuestionRequest request = QuestionRequest.builder()
                .question("")
                .segment(Segment.BASIC_PROGRAMMING)
                .QuestionLevelId(2)
                .choiceRequests(choiceRequests)
                .build();

        String requestJson = objectMapper.writeValueAsString(request);

        System.out.println(requestJson);

        mockMvc.perform(
                put("/api/questions/" + question.getQuestionId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<QuestionDetailResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertEquals("question: must not be blank, choiceRequests: size must be between 5 and 5", response.getMessage());
            assertNull(response.getData());
        });
    }

    @Test
    void deleteQuestionSuccess() throws Exception {
        mockMvc.perform(
                delete("/api/questions/" + question.getQuestionId())
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<QuestionDetailResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertEquals("Success delete data", response.getMessage());
            assertNull(response.getData());
        });
    }

    @Test
    void deleteQuestionNotFound() throws Exception {
        mockMvc.perform(
                delete("/api/questions/-1")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isNotFound()
        ).andDo(result -> {
            WebResponse<QuestionDetailResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertEquals("Question with Id -1 not found", response.getMessage());
            assertNull(response.getData());
        });
    }
}
