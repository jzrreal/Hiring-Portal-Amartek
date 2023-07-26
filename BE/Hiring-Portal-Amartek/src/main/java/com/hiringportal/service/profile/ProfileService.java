package com.hiringportal.service.profile;

import com.hiringportal.dto.DetailCandidateProfileResponse;

public interface ProfileService {
    DetailCandidateProfileResponse getDetailCandidateProfileById(Integer candidateProfileId);
}
