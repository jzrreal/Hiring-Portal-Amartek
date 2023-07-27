package com.hiringportal.controller.master;

import com.hiringportal.dto.CustomResponse;
import com.hiringportal.dto.ErrorResponse;
import com.hiringportal.entities.QuestionLevel;
import com.hiringportal.service.questionLevel.QuestionLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/api/question-levels")
public class QuestionLevelController {
    @Autowired
    private QuestionLevelService questionLevelService;

    @GetMapping
    public ResponseEntity<Object> get(){
        try{
            var questionLevels = questionLevelService.getAll();
            return CustomResponse.generateResponse("Data found with total amount :"+ questionLevels.size(),HttpStatus.OK ,questionLevels);

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
            var questionLevel = questionLevelService.getById(id);
            return CustomResponse.generateResponse("Data found :",HttpStatus.OK ,questionLevel);
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
    public ResponseEntity<Object> post(@Valid @RequestBody QuestionLevel questionLevel, BindingResult bindingResult) {
        try {
            var entity = questionLevelService.save(questionLevel);
            return CustomResponse.generateResponse("Success save data :",HttpStatus.OK ,entity);
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
    public ResponseEntity<Object> update (@Valid @RequestBody QuestionLevel questionLevel, @PathVariable Integer id, BindingResult bindingResult) {
        try {
            questionLevel.setQuestionLevelId(id);
            var entity = questionLevelService.update(questionLevel);
            return CustomResponse.generateResponse("Success update data :",HttpStatus.OK ,entity);
        } catch (ResponseStatusException exception){

            return ResponseEntity.badRequest().body(
                    ErrorResponse.builder()
                            .message(exception.getReason())
                            .status(exception.getStatus().value())
                            .build()
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id){
        try {
            questionLevelService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("success delete data");
        } catch (ResponseStatusException exception){

            return ResponseEntity.badRequest().body(
                    ErrorResponse.builder()
                            .message(exception.getReason())
                            .status(exception.getStatus().value())
                            .build()
            );
        }
    }
}
