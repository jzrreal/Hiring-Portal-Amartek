package com.hiringportal.service.profile;

import com.hiringportal.dto.DetailCandidateProfileResponse;
import com.hiringportal.entities.CandidateProfile;
import com.hiringportal.entities.EducationHistory;
import com.hiringportal.enums.EducationLevel;
import com.hiringportal.repository.CandidateProfileRepository;
import com.hiringportal.repository.EducationHistoryRepository;
import com.hiringportal.utils.DateUtil;
import com.hiringportal.utils.WordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService{
    private final CandidateProfileRepository candidateProfileRepository;
    private final EducationHistoryRepository educationHistoryRepository;
    @Override
    public DetailCandidateProfileResponse getDetailCandidateProfileById(Integer candidateProfileId) {
        CandidateProfile candidateProfile = candidateProfileRepository.findById(candidateProfileId).orElse(null);
        Map<EducationLevel, EducationHistory> educationHistoryMap =
                educationHistoryRepository.findAllByCandidateProfile_Id(candidateProfileId)
                        .stream().collect(Collectors.toMap(EducationHistory::getLevel, value -> value));
        return DetailCandidateProfileResponse.builder()
                .id(candidateProfileId)
                .email(candidateProfile.getUser().getEmail())
                .fullName(candidateProfile.getUser().getFullName())
                .phone(candidateProfile.getPhone())
                .summary(candidateProfile.getSummary())
                .birthDate(candidateProfile.getBirthDate())
                .age(DateUtil.getAge(candidateProfile.getBirthDate()))
                .gender(WordUtil.capitalizeEachLetter(candidateProfile.getUser().getGender().toString().toLowerCase()))
                .lastEducation(WordUtil.getLastEducation(educationHistoryMap))
                .build();
    }
}
