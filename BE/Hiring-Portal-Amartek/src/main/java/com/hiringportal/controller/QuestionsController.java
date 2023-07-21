package com.hiringportal.controller;

import com.hiringportal.dto.*;
import com.hiringportal.entities.Choice;
import com.hiringportal.entities.Questions;
import com.hiringportal.entities.TestQuestion;
import com.hiringportal.service.choice.ChoiceService;
import com.hiringportal.service.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionsController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ChoiceService choiceService;

    @GetMapping
    public ResponseEntity<Object> get(){
        try{
            List<Questions> questions = questionService.getAll();
            List<QuestionDTO> dtos = new ArrayList<>();


            for (Questions question : questions) {
                QuestionDTO dto = new QuestionDTO();
                dto.setQuestionId(question.getQuestionId());
                dto.setQuestion(question.getQuestion());
                dto.setSegment(question.getSegment());
                dto.setCreatedAt(question.getCreatedAt());
                List<ChoiceDTO> choiceDTOList = new ArrayList<>();
                for (Choice choice: question.getChoices()
                ) {
                    ChoiceDTO choiceDTO = new ChoiceDTO();
                    choiceDTO.setChoiceId(choice.getChoiceId());
                    choiceDTO.setChoice(choice.getChoice());
                    choiceDTO.setQuestionId(choice.getQuestion().getQuestionId());
                    choiceDTO.setCorrect(choice.isCorrect());

                    choiceDTOList.add(choiceDTO);
                }
                    dto.setChoices(choiceDTOList);

                List<TestQuestionDTO> testQuestionDTOList = new ArrayList<>();
                for (TestQuestion tesQuestion : question.getTestQuestions()
                ) {
                    TestQuestionDTO testQuestionDTO = new TestQuestionDTO();
                    testQuestionDTO.setTestId(tesQuestion.getTest().getTestId());
                    testQuestionDTO.setQuestionId(tesQuestion.getQuestions().getQuestionId());
                    testQuestionDTO.setAnswer(tesQuestion.getAnswer());
                    testQuestionDTO.setTestQuestionId(testQuestionDTO.getTestQuestionId());

                    testQuestionDTOList.add(testQuestionDTO);
                }
                    dto.setTestQuestion(testQuestionDTOList);

                dtos.add(dto);
            }
            return CustomResponse.generateResponse("Data found with total amount :"+ questions.size(), HttpStatus.OK ,dtos);

        }catch (ResponseStatusException exception){
            return ResponseEntity.badRequest().body(
                    ErrorResponse.builder()
                            .message(exception.getReason())
                            .status(exception.getStatus().value())
                            .build()
            );
        }
    }

    @GetMapping("/all")
    public List<QuestionDTO> getAllQuestions() {
        List<Questions> questions = questionService.getAll();
        List<QuestionDTO> dtos = new ArrayList<>();
        for (Questions question : questions) {
            QuestionDTO dto = new QuestionDTO();
            dto.setQuestionId(question.getQuestionId());
            dto.setQuestion(question.getQuestion());
            dto.setSegment(question.getSegment());
            dto.setCreatedAt(question.getCreatedAt());

            List<ChoiceDTO> choiceDTOList = new ArrayList<>();
            for (Choice choice: question.getChoices()
                 ) {
                ChoiceDTO choiceDTO = new ChoiceDTO();
                choiceDTO.setChoiceId(choice.getChoiceId());
                choiceDTO.setChoice(choice.getChoice());
                choiceDTO.setQuestionId(choice.getQuestion().getQuestionId());
                choiceDTO.setCorrect(choice.isCorrect());

                choiceDTOList.add(choiceDTO);
            }
            dto.setChoices(choiceDTOList);

            List<TestQuestionDTO> testQuestionDTOList = new ArrayList<>();
            for (TestQuestion tesQuestion : question.getTestQuestions()
                    ) {
                TestQuestionDTO testQuestionDTO = new TestQuestionDTO();
                testQuestionDTO.setTestId(tesQuestion.getTest().getTestId());
                testQuestionDTO.setQuestionId(tesQuestion.getQuestions().getQuestionId());
                testQuestionDTO.setAnswer(tesQuestion.getAnswer());
                testQuestionDTO.setTestQuestionId(testQuestionDTO.getTestQuestionId());

                testQuestionDTOList.add(testQuestionDTO);
            }
            dto.setTestQuestion(testQuestionDTOList);

            dtos.add(dto);
        }

        return dtos;
    }
}
