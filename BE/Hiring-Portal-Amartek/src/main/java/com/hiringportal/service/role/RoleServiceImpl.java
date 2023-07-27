package com.hiringportal.service.role;

import com.hiringportal.entities.Role;
import com.hiringportal.repository.RoleRepository;
import com.hiringportal.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{
    private final RoleRepository roleRepository;
    private final ValidationService validationService;
    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role getById(Integer id) {
        return roleRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role with Id " + id + " not found")
        );
    }

    @Override
    public Role save(Role entity) {
        validationService.validate(entity);

        return roleRepository.save(entity);
    }

    @Override
    public Role update(Role entity) {
        validationService.validate(entity);

        return roleRepository.save(entity);    }

    @Override
    public Boolean delete(Integer id) {
        roleRepository.deleteById(id);
        return roleRepository.findById(id).isEmpty();
    }
}
