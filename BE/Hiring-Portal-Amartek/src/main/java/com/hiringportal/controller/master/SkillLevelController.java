package com.hiringportal.controller.master;

import com.hiringportal.dto.CustomResponse;
import com.hiringportal.entities.SkillLevel;
import com.hiringportal.service.skillLevel.SkillLevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("api/skill-levels")
@RequiredArgsConstructor
public class SkillLevelController {

    private final SkillLevelService skillLevelService;

    @GetMapping
    public ResponseEntity<Object> getAllSkillLevel() {
        List<SkillLevel> response = skillLevelService.getAll();

        return CustomResponse.generateResponse(
                "Data found with total amount : " + response.size(),
                HttpStatus.OK,
                response
        );
    }

    @GetMapping(value = "{skillLevelId}")
    public ResponseEntity<Object> getSkillLevelById(@PathVariable(name = "skillLevelId") Integer skillLevelId){
        SkillLevel response = skillLevelService.getById(skillLevelId);

        return CustomResponse.generateResponse(
                "Data found",
                HttpStatus.OK,
                response
        );
    }

    @PostMapping
    public ResponseEntity<Object> postSkillLevel(@RequestBody SkillLevel request){
        SkillLevel response = skillLevelService.save(request);

        return CustomResponse.generateResponse(
                "Success save data",
                HttpStatus.OK,
                response
        );
    }

    @PutMapping(value = "{skillLevelId}")
    public ResponseEntity<Object> updateSkillLevel(
            @PathVariable(name = "skillLevelId") Integer skillLevelId,
            @RequestBody SkillLevel request
    ){
        request.setId(skillLevelId);

        SkillLevel response = skillLevelService.update(request);

        return CustomResponse.generateResponse(
          "Success update data",
          HttpStatus.OK,
          response
        );
    }

    @DeleteMapping(value = "{skillLevelId}")
    public ResponseEntity<Object> deleteSkillLevel(@PathVariable(name = "skillLevelId") Integer skillLevelId){
        skillLevelService.delete(skillLevelId);

        return CustomResponse.generateResponse(
                "Success delete data",
                HttpStatus.OK
        );
    }
}
