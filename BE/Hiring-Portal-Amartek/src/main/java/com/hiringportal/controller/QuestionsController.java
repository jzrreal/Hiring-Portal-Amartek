package com.hiringportal.controller;

import com.hiringportal.dto.*;
import com.hiringportal.entities.Choice;
import com.hiringportal.entities.QuestionLevel;
import com.hiringportal.entities.Questions;
import com.hiringportal.entities.TestQuestion;
import com.hiringportal.enums.Segment;
import com.hiringportal.service.choice.ChoiceService;
import com.hiringportal.service.question.QuestionService;
import com.hiringportal.service.questionLevel.QuestionLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionsController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ChoiceService choiceService;

    @Autowired
    private QuestionLevelService questionLevelService;

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

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable(required = true) Integer id){
        try{
            var  question  = questionService.getById(id);
            List<QuestionDTO> dtos = new ArrayList<>();
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

            return CustomResponse.generateResponse("Data found ", HttpStatus.OK ,dtos);

        }catch (ResponseStatusException exception){
            return ResponseEntity.badRequest().body(
                    ErrorResponse.builder()
                            .message(exception.getReason())
                            .status(exception.getStatus().value())
                            .build()
            );
        }
    }

    @PostMapping
    public ResponseEntity<Object> post(@Valid @RequestBody AddQuestionDTO addQuestionDTO, BindingResult bindingResult) {
        try {
            Questions question = new Questions();
            question.setQuestion(addQuestionDTO.getQuestion());
            question.setSegment(Segment.valueOf(addQuestionDTO.getSegment()));
            // Set current date and time as createdAt
            LocalDateTime currentDateTime = LocalDateTime.now();
            question.setCreatedAt(java.sql.Timestamp.valueOf(currentDateTime));
            // Set QuestionLevel based on questionLevelId (assuming have the necessary service to retrieve it)
            QuestionLevel questionLevel = questionLevelService.getById(addQuestionDTO.getQuestionLevelId());
            question.setQuestionLevel(questionLevel);
            // save dulu
            questionService.save(question);
            // Create and save Choices
            List<Choice> choices = new ArrayList<>();
            for (InsertChoiceQuestionDTO insertChoice : addQuestionDTO.getChoices()) {
                Choice choice = new Choice();
                choice.setChoice(insertChoice.getChoice());
                choice.setCorrect(insertChoice.getCorrect());
                choice.setQuestion(question);
                // Set the correct choice based on key choice
//                boolean isCorrect = choice.isCorrect(); // Implement logic correct
//                choice.setCorrect(isCorrect);
                // Save each choice
                choiceService.save(choice);
                choices.add(choice);
            }
//            this bcause cnt get respon objct in list
//            question.setChoices(choices);

            return CustomResponse.generateResponse("Success save data :",HttpStatus.OK ,question);
        } catch (ResponseStatusException exception){
            return ResponseEntity.badRequest().body(
                    ErrorResponse.builder()
                            .message(exception.getReason())
                            .status(exception.getStatus().value())
                            .build()
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update (@Valid @RequestBody AddQuestionDTO addQuestionDTO,@PathVariable Integer id, BindingResult bindingResult) {
        try {
            Questions question = new Questions();
            addQuestionDTO.setQuestionId(id);
            question.setQuestion(addQuestionDTO.getQuestion());
            question.setSegment(Segment.valueOf(addQuestionDTO.getSegment()));
            // Set current date and time as createdAt
            LocalDateTime currentDateTime = LocalDateTime.now();
            question.setCreatedAt(java.sql.Timestamp.valueOf(currentDateTime));
            // Set QuestionLevel based on questionLevelId (assuming have the necessary service to retrieve it)
            QuestionLevel questionLevel = questionLevelService.getById(addQuestionDTO.getQuestionLevelId());
            question.setQuestionLevel(questionLevel);
            // save dulu
            questionService.update(question);
            // Create and save Choices
            List<Choice> choices = new ArrayList<>();
            for (InsertChoiceQuestionDTO insertChoice : addQuestionDTO.getChoices()) {
                Choice choice = new Choice();
                choice.setChoice(insertChoice.getChoice());
                choice.setCorrect(insertChoice.getCorrect());
                choice.setQuestion(question);
                // Save each choice
                choiceService.update(choice);
                choices.add(choice);
            }
//            this bcause cnt get respon objct in list
//            question.setChoices(choices);

            return CustomResponse.generateResponse("Success save data :",HttpStatus.OK ,question);
        } catch (ResponseStatusException exception){
            return ResponseEntity.badRequest().body(
                    ErrorResponse.builder()
                            .message(exception.getReason())
                            .status(exception.getStatus().value())
                            .build()
            );
        }
    }


    @PutMapping("edit/{questionId}")
    public ResponseEntity<String> editQuestionById(@PathVariable Integer questionId, @RequestBody EditQuestionDTO editQuestionDTO) {
        try {
            // Check if the question exists
            Questions question = questionService.getById(questionId);
            if (question == null) {
                return new ResponseEntity<>("Question not found.", HttpStatus.NOT_FOUND);
            }

            // Update the question fields with data from EditQuestionDTO
            question.setQuestion(editQuestionDTO.getQuestion());
            question.setSegment(Segment.valueOf(editQuestionDTO.getSegment()));

            // Set current date and time as updatedAt
            LocalDateTime currentDateTime = LocalDateTime.now();
            question.setCreatedAt(java.sql.Timestamp.valueOf(currentDateTime));

            // Set QuestionLevel based on questionLevelId (assuming have the necessary service to retrieve it)
            QuestionLevel questionLevel = questionLevelService.getById(editQuestionDTO.getQuestionLevelId());
            question.setQuestionLevel(questionLevel);

            // Save the updated question
            questionService.save(question);

            return new ResponseEntity<>("Question updated successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update question.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<String> deleteQuestionById(@PathVariable Integer questionId) {
        try {
            // Check if the question exists
            Questions question = questionService.getById(questionId);
            if (question == null) {
                return new ResponseEntity<>("Question not found.", HttpStatus.NOT_FOUND);
            }

            // Delete choices associated with the question
            List<Choice> choices = question.getChoices();
            for (Choice choice : choices) {
                choiceService.delete(choice.getChoiceId());
            }

            // Delete the question
            questionService.delete(questionId);

            return new ResponseEntity<>("Question and associated choices deleted successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete question.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
