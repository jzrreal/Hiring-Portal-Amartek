package com.hiringportal.service.candidateProfile;

import com.hiringportal.dto.CandidateProfileRequest;
import com.hiringportal.dto.CandidateProfileResponse;
import com.hiringportal.entities.CandidateProfile;

import java.util.List;

public interface CandidateProfileService {
    List<CandidateProfileResponse> getAll();
    CandidateProfileResponse getById(Integer id);
    CandidateProfile save(CandidateProfileRequest request);
    CandidateProfile update(CandidateProfileRequest request);
    void delete(Integer id);
}
