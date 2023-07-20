package com.hiringportal.service.user;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.hiringportal.entities.User;
import com.hiringportal.repository.UserRepository;
import com.hiringportal.service.ValidationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ValidationService validationService;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Integer id) {
        return userRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with Id " + id + " not found")
        );
    }

    @Override
    public User save(User entity) {
        validationService.validate(entity);

        return userRepository.save(entity);
    }

    @Override
    public User update(User entity) {
        validationService.validate(entity);

        return userRepository.save(entity);
    }

    @Override
    public Boolean delete(Integer id) {
        userRepository.deleteById(id);
        return userRepository.findById(id)
            .isEmpty();
    }
    
}
