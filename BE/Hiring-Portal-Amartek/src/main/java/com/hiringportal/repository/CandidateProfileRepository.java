package com.hiringportal.repository;

import com.hiringportal.dto.CandidateProfileResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hiringportal.entities.CandidateProfile;

import java.util.List;
import java.util.Optional;

public interface CandidateProfileRepository extends JpaRepository<CandidateProfile, Integer> {
    @Query(value = """
            select new com.hiringportal.dto.CandidateProfileResponse(u.email, u.fullName, c.phone, c.summary, c.birthDate)
            from CandidateProfile c
            join User u on c.user.id = u.id
            """)
    List<CandidateProfileResponse> getAllCandidateProfile();
    @Query(value = """
            select new com.hiringportal.dto.CandidateProfileResponse(u.email, u.fullName, c.phone, c.summary, c.birthDate)
            from CandidateProfile c
            join User u on c.user.id = u.id where c.id = :id
            """)
    Optional<CandidateProfileResponse> getCandidateProfileById(Integer id);

    Boolean existsByPhone(String phone);
    Optional<CandidateProfile> findCandidateProfileByToken(String token);
    Optional<CandidateProfile> findCandidateProfileByUser_Email(String email);
}
