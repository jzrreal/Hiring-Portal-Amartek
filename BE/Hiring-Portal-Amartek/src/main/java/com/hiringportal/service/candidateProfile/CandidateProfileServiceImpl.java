package com.hiringportal.service.candidateProfile;

import com.hiringportal.dto.CandidateProfileRequest;
import com.hiringportal.dto.CandidateProfileResponse;
import com.hiringportal.entities.CandidateProfile;
import com.hiringportal.entities.User;
import com.hiringportal.repository.CandidateProfileRepository;
import com.hiringportal.repository.UserRepository;
import com.hiringportal.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CandidateProfileServiceImpl implements CandidateProfileService{
    private final CandidateProfileRepository candidateProfileRepository;
    private final UserRepository userRepository;
    private final ValidationService validationService;
    @Override
    public List<CandidateProfileResponse> getAll() {
        return candidateProfileRepository.getAllCandidateProfile();
    }

    @Override
    public CandidateProfileResponse getById(Integer id) {
        return candidateProfileRepository.getCandidateProfileById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidate Profile with Id " + id + " not found")
        );
    }

    @Override
    public CandidateProfile save(CandidateProfileRequest request) {
        validationService.validate(request);

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Your Data Not Valid"));

        CandidateProfile candidateProfile = CandidateProfile.builder()
                .phone(request.getPhone())
                .birthDate(request.getBirthDate())
                .token(UUID.randomUUID().toString())
                .summary(request.getSummary())
                .user(user)
                .build();

        return candidateProfileRepository.save(candidateProfile);
    }

    @Override
    public CandidateProfile update(CandidateProfileRequest request) {
        validationService.validate(request);

        CandidateProfile candidateProfile = candidateProfileRepository.findById(request.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidate Profile with Id " + request.getId() + " not found"));

        candidateProfile.setPhone(request.getPhone());
        candidateProfile.setBirthDate(request.getBirthDate());
        candidateProfile.setSummary(request.getSummary());

        return candidateProfileRepository.save(candidateProfile);
    }

    @Override
    public void delete(Integer id) {
        candidateProfileRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidate Profile with Id " + id + " not found"));

        candidateProfileRepository.deleteById(id);
    }
}
