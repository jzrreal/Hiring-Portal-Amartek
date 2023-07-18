package com.hiringportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hiringportal.entities.CandidateProfile;

public interface CandidateProfileRepository extends JpaRepository<CandidateProfile, Integer> {
    
}
