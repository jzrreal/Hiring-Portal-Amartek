package com.hiringportal.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hiringportal.dto.CustomResponse;
import com.hiringportal.entities.Role;
import com.hiringportal.service.role.RoleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("api/roles")
public class RoleController {
    private final RoleService roleService;

    // Get all
    @GetMapping("")
    public ResponseEntity<Object> get() {
        List<Role> roles = roleService.getAll();
        return CustomResponse.generateResponse("Data found with total amount : " + roles.size(), HttpStatus.OK, roles);
    }

    // get by id
    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable(required = true) Integer id) {
        Role role = roleService.getById(id);
        return CustomResponse.generateResponse("Data found", HttpStatus.OK, role);
    }

    // add role
    @PostMapping("")
    public ResponseEntity<Object> post(@RequestBody Role role) {
        roleService.save(role);
        return CustomResponse.generateResponse("Success save data", HttpStatus.OK);
    }

    // update
    @PutMapping("/{id}")
    public ResponseEntity<Object> put(
            @PathVariable(required = true) Integer id,
            @RequestBody Role role) {
        role.setId(id);
        roleService.update(role);
        return CustomResponse.generateResponse("Success update data", HttpStatus.OK);
    }

    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(required = true) Integer id) {
        roleService.delete(id);
        return CustomResponse.generateResponse("Success delete data", HttpStatus.OK);
    }
}
