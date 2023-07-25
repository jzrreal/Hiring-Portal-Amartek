package com.hiringportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hiringportal.entities.EducationHistory;

import java.util.List;

@Repository
public interface EducationHistoryRepository extends JpaRepository<EducationHistory, Integer> {
    List<EducationHistory> findByCandidateProfileId(Integer candidateId);
}
