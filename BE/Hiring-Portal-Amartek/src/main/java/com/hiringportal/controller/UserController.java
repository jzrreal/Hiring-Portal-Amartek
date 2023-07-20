package com.hiringportal.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hiringportal.dto.CustomResponse;
import com.hiringportal.dto.UserRequest;
import com.hiringportal.dto.UserResponse;
import com.hiringportal.entities.User;
import com.hiringportal.service.role.RoleService;
import com.hiringportal.service.user.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    // get
    @GetMapping("")
    public ResponseEntity<Object> get() {
        List<User> users = userService.getAll();
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : users) {
            UserResponse userResponse = UserResponse
                .builder()
                .user_id(user.getId())
                .email(user.getEmail())
                .full_name(user.getFullName())
                .role_name(roleService.getById(user.getRole().getId()).getName())
                .build();
                userResponses.add(userResponse);
        }
        return CustomResponse.generateResponse("Data found with total amount : " + users.size(), HttpStatus.OK, userResponses);
    }
    // get id
    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable(required = true) Integer id) {
        User user = userService.getById(id);
        UserResponse userResponse = UserResponse
            .builder()
            .user_id(id)
            .email(user.getEmail())
            .full_name(user.getFullName())
            .role_name(roleService.getById(user.getRole().getId()).getName())
            .build();
        return CustomResponse.generateResponse("Data found",HttpStatus.OK, userResponse);
    }
    // post
    @PostMapping("")
    public ResponseEntity<Object> post(@RequestBody UserRequest userRequest) {
        User user = User.builder()
            .email(userRequest.getEmail())
            .password(userRequest.getPassword())
            .fullName(userRequest.getFull_name())
            .role(roleService.getById(userRequest.getRole_id()))
            .build();
        userService.save(user);
        return CustomResponse.generateResponse("Success save data",HttpStatus.OK);
    }
    // update
    @PutMapping("/{id}")
    public ResponseEntity<Object> put(
        @PathVariable(required = true) Integer id,
        @RequestBody UserRequest userRequest
    ) {
        User user = User.builder()
            .id(id)
            .email(userRequest.getEmail())
            .password(userRequest.getPassword())
            .fullName(userRequest.getFull_name())
            .role(roleService.getById(userRequest.getRole_id()))
            .build();
        userService.save(user);
        return CustomResponse.generateResponse("Success update data",HttpStatus.OK);
    }
    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(required = true) Integer id) {
        userService.delete(id);
        return CustomResponse.generateResponse("Success delete data",HttpStatus.OK);
    }
}
